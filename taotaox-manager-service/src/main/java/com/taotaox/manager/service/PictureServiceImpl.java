package com.taotaox.manager.service;

import com.taotaox.common.exception.BizException;
import com.taotaox.common.exception.ErrorMsg;
import com.taotaox.common.util.web.FtpUtils;
import com.taotaox.common.util.web.IdUtils;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by yachao on 18/1/21.
 */
@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public ErrorMsg uploadPicture(MultipartFile uploadFile) throws BizException {
        try {
            String oldName = uploadFile.getOriginalFilename();
            String newName = IdUtils.getImageName();
            newName += oldName.substring(oldName.lastIndexOf("."));

            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtils.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
                    imagePath, newName, uploadFile.getInputStream());

            if (!result) {
                return ErrorMsg.FILE_UPLOAD_FAIL;
            }
            log.info(ErrorMsg.EVERYTHING_IS_OK.getMessage(), IMAGE_BASE_URL + imagePath + "/" + newName);
            return ErrorMsg.EVERYTHING_IS_OK;
        } catch (IOException e) {
            return ErrorMsg.FILE_UPLOAD_ERROR;
        }
    }
}
