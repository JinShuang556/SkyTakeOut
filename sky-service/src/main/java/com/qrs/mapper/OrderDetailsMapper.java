package com.qrs.mapper;

import com.qrs.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailsMapper {

    /**
     * 批量插入订单详情
     * @param orderDetailsList 订单详情
     */
    void insertBatch(List<OrderDetail> orderDetailsList);
}
