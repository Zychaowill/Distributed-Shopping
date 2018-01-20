package com.taotaox.common.utils.web;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by yachao on 18/1/20.
 */
public class ExceptionUtil {

    public static String getStackTrace(Throwable t) {
        StringWriter strWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(strWriter);

        try {
            t.printStackTrace(writer);
            return strWriter.toString();
        } finally {
            writer.close();
        }
    }
}
