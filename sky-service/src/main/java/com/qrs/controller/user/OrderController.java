package com.qrs.controller.user;

import com.qrs.dto.OrderSubmitDTO;
import com.qrs.result.Result;
import com.qrs.service.OrderService;
import com.qrs.vo.OrderSubmitVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    /**
     * 提交订单
     * @param orderSubmitDTO 订单信息
     * @return 订单信息
     */
    @PostMapping("/submit")
    public Result submit(@RequestBody OrderSubmitDTO orderSubmitDTO){
        log.info("提交订单:{}",orderSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.CreateOrder(orderSubmitDTO);
        return Result.success(orderSubmitVO);
    }

}
