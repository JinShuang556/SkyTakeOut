package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.annotation.AutoFill;
import com.qrs.dto.SetmealPageDTO;
import com.qrs.entity.Setmeal;
import com.qrs.enumeration.OperationType;
import com.qrs.vo.SetmealPageVO;
import com.qrs.vo.SetmealWithSetmealDishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {
    /**
     * 套餐分页查询
     * @param setmealPageDTO 分页参数
     * @return 分页结果
     */
    Page<SetmealPageVO> page(SetmealPageDTO setmealPageDTO);

    /**
     * 新增套餐
     * @param setmeal 套餐
     */
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 批量删除套餐
     * @param ids 套餐id
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐和套餐菜品信息
     * @param id 套餐id
     * @return
     */
    SetmealWithSetmealDishVO selectSetmealWithSetmealDishById(Long id);

    /**
     * 根据id更新套餐信息
     * @param setmeal 套餐信息
     */
    @AutoFill(OperationType.UPDATE)
    void updateSetmealById(Setmeal setmeal);

    /**
     * 检查套餐名是否存在
     * @param newName 新套餐名
     * @return 1 存在 0 不存在
     */
    @Select("select count(*) from setmeal where name = #{newName}")
    Integer checkNameInSetmeal(String newName);
}
