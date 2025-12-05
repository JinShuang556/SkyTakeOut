package com.qrs.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 订单实体类，用于存储订单相关信息
 */
@Data
public class Orders {
    private Long id; // 订单ID，唯一标识一条订单记录
    private String number; // 订单号，用于唯一标识订单
    private Integer status; // 订单状态，1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款
    private Integer userId; // 下单用户ID，关联到用户表的主键
    private Integer addressBookId; // 地址ID，关联到地址表的主键
    private LocalDate orderTime; // 下单时间
    private LocalDate checkoutTime; // 结账时间
    private Integer payMethod; // 支付方式 1微信,2支付宝
    private Integer payStatus; // 支付状态 0未支付 1已支付 2退款
    private Double amount; // 订单金额
    private String remark; // 备注
    private String phone; // 联系电话
    private String address; // 配送地址
    private String userName; // 用户名
    private String consignee; // 收货人
    private String cancelReason; // 订单取消原因
    private String rejectReason; // 订单拒绝原因
    private LocalDate cancelTime; // 订单取消时间
    private LocalDate estimateDeliveryTime; // 预计送达时间
    private Integer deliveryStatus; // 配送状态 1立即送出  0选择具体时间
    private LocalDate deliveryTime; // 送达时间
    private Integer packAmount; // 打包费
    private Integer tablewareNumber; // 餐具数量
    private Integer tablewareStatus; // 餐具数量状态 1按餐量提供  0选择具体数量
}
