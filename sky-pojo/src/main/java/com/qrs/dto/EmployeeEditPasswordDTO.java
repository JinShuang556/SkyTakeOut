package com.qrs.dto;

import lombok.Data;

@Data
public class EmployeeEditPasswordDTO {
    private Long empId;
    private String newPassword;
    private String oldPassword;
}
