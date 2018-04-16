package com.taotaox.manager.service;

import com.taotaox.common.exception.BizException;
import com.taotaox.common.exception.ErrorMsg;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yachao on 18/1/21.
 */
public interface PictureService {

    ErrorMsg uploadPicture(MultipartFile uploadFile) throws BizException;
}
