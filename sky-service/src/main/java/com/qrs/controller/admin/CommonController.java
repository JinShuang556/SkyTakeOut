package com.qrs.controller.admin;

import com.qrs.result.Result;
import com.qrs.utils.AliOssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/common")
@RequiredArgsConstructor
public class CommonController {


    private final AliOssUtil aliOssUtil;

    /**
     * 上传文件
     * @param file 文件
     * @return 上传结果
     */
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) {
        log.info("上传文件:{}", file.getOriginalFilename());
        //1.获取原文件名
        String originalFilename = file.getOriginalFilename();
        //2.获取后缀：
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //3.生成新的文件名
        String newFileName = UUID.randomUUID().toString() + suffix;
        //4.上传文件
        try {
            String filePath = aliOssUtil.upload(file.getBytes(), newFileName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("上传文件失败:{}", e.getMessage());
        }
        return Result.error("上传文件失败");
    }
}
