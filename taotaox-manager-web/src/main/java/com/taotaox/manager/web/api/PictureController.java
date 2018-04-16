package com.taotaox.manager.web.api;

import com.taotaox.common.bo.JsonEntity;
import com.taotaox.common.exception.BizException;
import com.taotaox.common.exception.ErrorMsg;
import com.taotaox.common.util.web.ResponseHelper;
import com.taotaox.manager.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yachao on 18/1/21.
 */
@Api(value = "Taotaox Api")
@RestController
@RequestMapping("/public/api")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @ApiOperation(value = "Upload Picture", notes = "Upload Picture to Image Server", tags = "Picture", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(paramType = "form", name = "uploadFile", value = "file object", dataType = "MultipartFile", required = true)
    @RequestMapping(value = "/pic/action/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonEntity<?> pictureUpload(MultipartFile uploadFile) throws BizException {
        ErrorMsg errorMsg = pictureService.uploadPicture(uploadFile);
        return ResponseHelper.withMessage(errorMsg);
    }
}
