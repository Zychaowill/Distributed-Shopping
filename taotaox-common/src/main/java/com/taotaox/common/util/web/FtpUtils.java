package com.taotaox.common.util.web;

import com.taotaox.common.exception.BizException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Objects;

/**
 * Created by yachao on 18/1/21.
 */
public class FtpUtils {

    public static boolean uploadFile(String host, int port, String username, String password, String basePath, String
            filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        try {
            int reply;
            ftp.connect(host, port);
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (ObjectUtils.isEmpty(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            throw new BizException(e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }

    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                       String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        try {
            int reply;
            ftp.connect(host, port);
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile f : fs) {
                if (Objects.equals(f.getName(), fileName)) {
                    File localFile = new File(localPath + "/" + f.getName());

                    OutputStream out = new FileOutputStream(localFile);
                    ftp.retrieveFile(f.getName(), out);
                    out.close();
                    break;
                }
            }
            ftp.logout();
            result = true;
        } catch (IOException e) {
            throw new BizException(e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }
}
