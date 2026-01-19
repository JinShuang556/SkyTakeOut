package com.qrs.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单实体类，用于存储订单相关信息
 */
@Data
public class Orders {

    public static final Integer ORDER_STATUS_WAIT_PAY = 1; // 待付款
    public static final Integer ORDER_STATUS_WAIT_ORDER = 2; // 待接单
    public static final Integer ORDER_STATUS_HAVE_BEEN_RECEIVED = 3; // 已接单
    public static final Integer ORDER_STATUS_DELIVERING = 4; // 派送中
    public static final Integer ORDER_STATUS_COMPLETED = 5; // 已完成
    public static final Integer ORDER_STATUS_CANCELLED = 6; // 已取消
    public static final Integer ORDER_STATUS_REFUND = 7; // 退款

    public static final Integer ORDER_PAY_STATUS_WAIT_PAY = 0; // 待付款
    public static final Integer ORDER_PAY_STATUS_PAID = 1; // 已支付
    public static final Integer ORDER_PAY_STATUS_REFUNDED = 2; // 退款


    private Long id; // 订单ID，唯一标识一条订单记录
    private String number; // 订单号，用于唯一标识订单
    private Integer status; // 订单状态，1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款
    private Long userId; // 下单用户ID，关联到用户表的主键
    private Long addressBookId; // 地址ID，关联到地址表的主键
    private LocalDateTime orderTime; // 下单时间
    private LocalDateTime checkoutTime; // 结账时间
    private Integer payMethod; // 支付方式 1微信,2支付宝
    private Integer payStatus; // 支付状态 0未支付 1已支付 2退款
    private Double amount; // 订单金额
    private String remark; // 备注
    private String phone; // 联系电话
    private String address; // 配送地址
    private String userName; // 用户名
    private String consignee; // 收货人
    private String cancelReason; // 订单取消原因
    private String rejectionReason; // 订单拒绝原因
    private LocalDateTime cancelTime; // 订单取消时间
    private LocalDateTime estimatedDeliveryTime; // 预计送达时间
    private Integer deliveryStatus; // 配送状态 1立即送出  0选择具体时间
    private LocalDateTime deliveryTime; // 送达时间
    private Double packAmount; // 打包费
    private Integer tablewareNumber; // 餐具数量
    private Integer tablewareStatus; // 餐具数量状态 1按餐量提供  0选择具体数量
}
