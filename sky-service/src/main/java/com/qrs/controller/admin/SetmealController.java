package com.qrs.controller.admin;

import com.qrs.dto.SetmealWithSetmealDishDTO;
import com.qrs.dto.SetmealPageDTO;
import com.qrs.result.Result;
import com.qrs.service.SetmealService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
@RequiredArgsConstructor
public class SetmealController {

    private final SetmealService setmealService;
    /**
     * 套餐分页查询
     * @param setmealPageDTO 分页参数
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<PageVO> page(SetmealPageDTO setmealPageDTO){
        log.info("套餐分页查询{}",setmealPageDTO);
        PageVO pageVO = setmealService.page(setmealPageDTO);
        return Result.success(pageVO);
    }

    /**
     * 新增套餐
     * @param setmealWithSetmealDishDTO 套餐和菜品
     * @return 新增结果
     */
    @PostMapping
    public Result addSetmealWithSetmealDish(@RequestBody SetmealWithSetmealDishDTO setmealWithSetmealDishDTO){
        log.info("新增套餐{}", setmealWithSetmealDishDTO);
        setmealService.addSetmealWithSetmealDish(setmealWithSetmealDishDTO);
        return Result.success();
    }

    /**
     * 批量删除套餐和关联的菜品
     * @param ids 套餐id集合
     * @return 删除结果
     */
    @DeleteMapping
    public Result deleteSetmealWithSetmealDish(@RequestParam List<Long> ids){
        log.info("批量删除套餐{}", ids);
        setmealService.deleteSetmealWithSetmealDish(ids);
        return Result.success();
    }

}
