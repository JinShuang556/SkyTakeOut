package com.qrs.entity;

import lombok.Data;

/**
 * 地址簿实体类
 * 用于存储用户地址信息，包括省市区详情等
 */
@Data  // 使用Lombok注解，自动生成getter、setter等方法
public class AddressBook {
    private Long id;          // 地址簿ID，主键
    private Long userId;       // 用户ID，关联用户表
    private String consignee;     // 收货人姓名
    private String sex;          // 收货人性别
    private String phone;        // 收货人联系电话
    private String provinceCode; // 省级区划编号
    private String provinceName; // 省级名称
    private String cityCode;     // 市级区划编码
    private String cityName;     // 市级名称
    private String districtCode; // 区级区划编码
    private String districtName; // 区级名称
    private String detail;       // 详细地址
    private String label;        // 地址标签，如"家"、"公司"等
    private Integer isDefault;   // 是否默认地址，0-否，1-是
}
