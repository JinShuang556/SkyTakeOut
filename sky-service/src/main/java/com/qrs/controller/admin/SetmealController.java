package com.qrs.controller.admin;

import com.qrs.dto.SetmealWithSetmealDishDTO;
import com.qrs.dto.SetmealPageDTO;
import com.qrs.result.Result;
import com.qrs.service.SetmealService;
import com.qrs.vo.PageVO;
import com.qrs.vo.SetmealWithSetmealDishVO;
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
        log.info("套餐分页查询：{}",setmealPageDTO);
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
        log.info("新增套餐：{}", setmealWithSetmealDishDTO);
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
        log.info("批量删除套餐：{}", ids);
        setmealService.deleteSetmealWithSetmealDish(ids);
        return Result.success();
    }

    /**
     * 根据id查询套餐和关联的菜品
     * @param id 套餐id
     * @return 套餐和关联的菜品
     */
    @GetMapping("/{id}")
    public Result<SetmealWithSetmealDishVO> selectSetmealWithSetmealDishById(@PathVariable Long id){
        log.info("根据id查询套餐：{}", id);
        SetmealWithSetmealDishVO setmealWithSetmealDishVO = setmealService.selectSetmealWithSetmealDishById(id);
        return Result.success(setmealWithSetmealDishVO);
    }

    /**
     * 修改套餐
     * @param setmealWithSetmealDishDTO 套餐和菜品
     * @return 修改结果
     */
    @PutMapping
    public Result updateSetmealWithSetmealDish(@RequestBody SetmealWithSetmealDishDTO setmealWithSetmealDishDTO){
        log.info("修改套餐：{}", setmealWithSetmealDishDTO);
        setmealService.updateSetmealWithSetmealDish(setmealWithSetmealDishDTO);
        return Result.success();
    }

    /**
     * 修改套餐状态
     * @return 修改结果
     */
    @PostMapping("/status/{status}")
    public Result updateStatus(Long id, @PathVariable Integer status){
        log.info("修改套餐状态：{},{}",id,status);
        setmealService.updateStatus(id, status);
        return Result.success();
    }

}
