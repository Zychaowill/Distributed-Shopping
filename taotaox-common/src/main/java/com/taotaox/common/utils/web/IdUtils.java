package com.taotaox.common.utils.web;

import java.util.Random;

/**
 * Created by yachao on 18/1/20.
 */
public class IdUtils {

    public static String getImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);

        String imageName = millis + String.format("%03d", end3);
        return imageName;
    }

    public static long getItemId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end2 = random.nextInt(99);

        String str = millis + String.format("%02d", end2);
        long itemId = new Long(str);
        return itemId;
    }
}
