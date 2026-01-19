package com.qrs.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单提交数据传输对象
 * 用于封装订单提交相关的数据，包括地址信息、金额、配送状态等
 */
@Data
public class OrderSubmitDTO {
    private Long addressBookId; // 地址簿ID，用于确定订单配送地址
    private Integer payMethod; // 支付方式，如：1-微信，2-支付宝等
    private Double amount; // 订单总金额
    private String remark; // 订单备注信息
    private LocalDateTime estimatedDeliveryTime; // 预计送达时间
    private Integer deliveryStatus; // 配送状态： 1立即送出 0选择具体时间
    private Double packAmount; // 打包费
    private Integer tablewareNumber; // 餐具数量
    private Integer tablewareStatus; // 餐具数量状态 1按餐量提供 0选择具体数量
}
