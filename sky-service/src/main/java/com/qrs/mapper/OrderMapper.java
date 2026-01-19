package com.qrs.mapper;

import com.qrs.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单信息
     * @param orders 订单信息
     */
    void insert(Orders orders);
}
