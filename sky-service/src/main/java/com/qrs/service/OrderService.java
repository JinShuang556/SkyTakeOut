package com.qrs.service;

import com.qrs.dto.OrderSubmitDTO;
import com.qrs.vo.OrderSubmitVO;

public interface OrderService {

    /**
     * 创建订单
     * @param orderSubmitDTO 订单信息
     * @return 订单信息
     */
    OrderSubmitVO CreateOrder(OrderSubmitDTO orderSubmitDTO);
}
