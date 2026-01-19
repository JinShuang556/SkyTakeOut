package com.qrs.service.impl;

import com.qrs.context.BaseContext;
import com.qrs.dto.OrderSubmitDTO;
import com.qrs.entity.AddressBook;
import com.qrs.entity.OrderDetail;
import com.qrs.entity.Orders;
import com.qrs.entity.ShoppingCart;
import com.qrs.mapper.AddressBookMapper;
import com.qrs.mapper.OrderDetailsMapper;
import com.qrs.mapper.OrderMapper;
import com.qrs.mapper.ShoppingCartMapper;
import com.qrs.service.OrderService;
import com.qrs.vo.OrderSubmitVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final AddressBookMapper addressBookMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @Transactional
    @Override
    public OrderSubmitVO CreateOrder(OrderSubmitDTO orderSubmitDTO) {
        //1.检查各种业务异常(地址为空，购物车为空)
        //1.1检查地址是否为空
        AddressBook addressBook = addressBookMapper.getAddressBookById(orderSubmitDTO.getAddressBookId());
        if(addressBook == null){
            throw new RuntimeException("地址为空");
        }

        //1.2检查购物车是否为空
        ShoppingCart shoppingCart = new ShoppingCart();
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.getList(shoppingCart);

        if(shoppingCarts == null || shoppingCarts.isEmpty()){
            throw new RuntimeException("购物车为空");
        }
        //2. 向订单表插入数据
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderSubmitDTO,orders);
        //生成订单号：
        orders.setNumber(String.valueOf(System.currentTimeMillis()));
        //设置订单状态:
        orders.setStatus(1);
        //设置用户id
        orders.setUserId(BaseContext.getCurrentId());
        //设置下单时间
        orders.setOrderTime(LocalDateTime.now());
        //设置联系电话：
        orders.setPhone(addressBook.getPhone());
        //设置地址：
        String address = addressBook.getProvinceName() + addressBook.getCityName() + addressBook.getDistrictName() + addressBook.getDetail();
        orders.setAddress(address);
        //设置收货人：
        orders.setConsignee(addressBook.getConsignee());
        //设置支付状态：(待支付)
        orders.setPayStatus(Orders.ORDER_PAY_STATUS_WAIT_PAY);
        log.info("订单信息:{}",orders);
        orderMapper.insert(orders);
        //3.向订单详明表插入n条数据
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (ShoppingCart cart : shoppingCarts) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setOrderId(orders.getId());
            orderDetails.add(orderDetail);
        }
        orderDetailsMapper.insertBatch(orderDetails);
        //4.清空购物车
        shoppingCartMapper.deleteBatch(userId);
        //5.封装VO
        OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
                .Id(orders.getId())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .orderTime(orders.getOrderTime())
                .build();

        return orderSubmitVO;
    }
}
