package com.qrs.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class AliOssUtil {
    private String endpoint;        // 阿里云OSS服务的终端地址
    private String accessKeyId;     // 阿里云OSS的访问密钥ID
    private String accessKeySecret; // 阿里云OSS的访问密钥密码
    private String bucketName;      // 阿里云OSS的存储桶名称

    /**
     * 上传文件到阿里云OSS对象存储服务
     * @param bytes 文件的字节数组
     * @param objectName OSS中存储的对象名称（文件名）
     * @return 文件的访问URL地址
     */
    public String upload(byte[] bytes, String objectName) {
        // 创建OSS客户端实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 将字节数组作为输入流上传到指定bucket的objectName
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            // 处理OSS服务异常
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            // 处理客户端异常
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            // 确保在finally块中关闭OSS客户端，释放资源
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        // 构建文件的访问URL
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)        // 添加bucket名称
                .append(".")               // 添加分隔点
                .append(endpoint)          // 添加endpoint
                .append("/")
                .append(objectName);  // 添加文件名

        // 记录上传成功的日志
        log.info("上传成功，文件路径为：{}", stringBuilder.toString());
        return stringBuilder.toString();
    }


}
