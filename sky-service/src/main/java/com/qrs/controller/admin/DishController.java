package com.qrs.controller.admin;

import com.qrs.dto.DishDTO;
import com.qrs.dto.DishPageDTO;
import com.qrs.dto.DishUpdateDTO;
import com.qrs.entity.Dish;
import com.qrs.result.Result;
import com.qrs.service.DishService;
import com.qrs.vo.DishWithFlavorVO;
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

    /**
     * 菜品分页查询
     * @param dishPageDTO 分页参数
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<PageVO> page(DishPageDTO dishPageDTO){
        log.info("菜品分页查询：{}",dishPageDTO);
        PageVO pageVO = dishService.page(dishPageDTO);
        return Result.success(pageVO);
    }

    /**
     * 新增菜品
     * @param dishDTO 菜品信息
     * @return 成功或失败
     */
    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 根据id删除菜品和菜品口味
     * @param ids 菜品id
     * @return 成功或失败
     */
    @DeleteMapping
    public Result deleteDishes(@RequestParam List<Long> ids){
        log.info("(批量)删除菜品：{}",ids);
        dishService.removeWithFlavor(ids);
        return Result.success();
    }

    /**
     * 根据套餐id查询菜品
     * @param categoryId 分类id
     * @return 菜品列表
     */
    @GetMapping("/list")
    public Result<List<Dish>> selectDishByCategoryId(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);
        List<Dish> dishes = dishService.selectDishByCategoryId(categoryId);
        return Result.success(dishes);
    }

    /**
     * 根据id查询菜品、菜品口味和套餐名称
     * @param id 菜品id
     * @return 菜品、菜品口味和套餐名称
     */
    @GetMapping("/{id}")
    public Result<DishWithFlavorVO> selectDishWithFlavorById(@PathVariable Long id){
        log.info("根据id查询菜品、菜品口味和套餐名称：{}",id);
        DishWithFlavorVO dishWithFlavorVO = dishService.selectDishWithFlavorById(id);
        return Result.success(dishWithFlavorVO);
    }

    /**
     * 修改菜品和菜品口味
     * @param dishUpdateDTO 菜品信息
     * @return 修改结果
     */
    @PutMapping
    public Result updateDishWithFlavor(@RequestBody DishUpdateDTO dishUpdateDTO){
        log.info("修改菜品和菜品口味：{}",dishUpdateDTO);
        dishService.updateDishWithFlavor(dishUpdateDTO);
        return Result.success();
    }

    /**
     * 修改菜品状态
     * @param id 菜品id
     * @param status 菜品状态
     * @return 成功或失败
     */
    @PostMapping("/status/{status}")
    public Result DishStatusChange(Long id,@PathVariable Integer status){
        log.info("修改菜品状态：{},{}",id,status);
        dishService.DishStatusChange(id,status);
        return Result.success();
    }


}
