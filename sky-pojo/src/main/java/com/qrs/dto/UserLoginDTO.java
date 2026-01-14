package com.qrs.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserLoginDTO implements Serializable {
    private String code; //微信用户授权码
}
