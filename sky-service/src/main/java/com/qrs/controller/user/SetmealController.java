package com.qrs.controller.user;


import com.qrs.entity.Setmeal;
import com.qrs.result.Result;
import com.qrs.service.SetmealService;
import com.qrs.vo.DishItemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@RequiredArgsConstructor
public class SetmealController {

    private final SetmealService setmealService;

    /**
     * 根据分类id查询套餐
     * @param categoryId 分类id
     * @return 套餐列表
     */
    @GetMapping("/list")
    public Result<List<Setmeal>> list(Long categoryId){
        log.info("根据分类id查询套餐:{}",categoryId);
        List<Setmeal> setmealList = setmealService.getSetmealsBycategoryId(categoryId);
        return Result.success(setmealList);
    }

    /**
     * 根据套餐id查询包含的菜品
     * @param id 套餐id
     * @return 菜品列表
     */
    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> selectDishesById(@PathVariable Long id){
        log.info("根据套餐id查询菜品:{}",id);
        List<DishItemVO> dishItemVOList = setmealService.selectDishesById(id);
        return Result.success(dishItemVOList);
    }


}
