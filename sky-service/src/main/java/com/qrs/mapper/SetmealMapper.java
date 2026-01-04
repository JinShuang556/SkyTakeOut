package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.annotation.AutoFill;
import com.qrs.dto.SetmealPageDTO;
import com.qrs.entity.Setmeal;
import com.qrs.enumeration.OperationType;
import com.qrs.vo.SetmealPageVO;
import org.apache.ibatis.annotations.Mapper;

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
}
