package com.qrs.controller.user;

import com.qrs.entity.Dish;
import com.qrs.result.Result;
import com.qrs.service.DishService;
import com.qrs.vo.DishWithFlavorVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("userDishController")
@RequestMapping("/user/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    /**
     * 根据分类id查询菜品和菜品口味
     * @param categoryId 分类id
     * @return 菜品列表
     */
    @GetMapping("/list")
    public Result<List<DishWithFlavorVO>> selectDishByCategoryId(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);
        List<DishWithFlavorVO> dishes = dishService.selectDishWithFlavorByCategoryId(categoryId);
        return Result.success(dishes);
    }
}
