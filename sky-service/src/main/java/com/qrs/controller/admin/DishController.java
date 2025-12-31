package com.qrs.controller.admin;

import com.qrs.dto.DishPageDTO;
import com.qrs.entity.Dish;
import com.qrs.result.Result;
import com.qrs.service.DishService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/page")
    public Result<PageVO> page(DishPageDTO dishPageDTO){
        log.info("菜品分页查询：{}",dishPageDTO);
        PageVO pageVO = dishService.page(dishPageDTO);
        return Result.success(pageVO);
    }
}
