package com.qrs.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSubmitVO {
    private Long Id; //订单id
    private Double orderAmount; //订单金额
    private String orderNumber; //订单号
    private LocalDateTime orderTime; //下单时间
}
