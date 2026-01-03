package com.qrs.controller.admin;

import com.qrs.dto.DishDTO;
import com.qrs.dto.DishPageDTO;
import com.qrs.entity.Dish;
import com.qrs.result.Result;
import com.qrs.service.DishService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteDishes(List<Long> ids){
        log.info("(批量)删除菜品：{}",ids);
        dishService.removeWithFlavor(ids);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectDishByCategoryId(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);
        List<Dish> dishes = dishService.selectDishByCategoryId(categoryId);
        return Result.success(dishes);
    }
}
