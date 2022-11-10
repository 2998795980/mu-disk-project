package xyz.ziang.mudisk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.ziang.mudisk.common.result.ApiResult;

/**
 * 处理文件Controller
 */
@RequestMapping("handler/file")
@RestController
public class HandlerFileController {

    /**
     * 上传文件
     * <p/>
     * 这个接口不适用大文件上传  大文件需要分片上传
     *
     * @param file 文件对象
     * @return Void
     */
    @PostMapping("/upload")
    public ApiResult<Void> uploadFile(@RequestPart MultipartFile file) {
        // TODO
        return ApiResult.success();
    }


}
