package com.qrs.service;

import com.qrs.dto.DishDTO;
import com.qrs.dto.DishPageDTO;
import com.qrs.vo.PageVO;

import java.util.List;

public interface DishService {

    /**
     * 分页查询菜品
     * @param dishPageDTO 分页参数
     * @return 分页结果
     */
    PageVO page(DishPageDTO dishPageDTO);

    /**
     * 新增菜品并保存口味
     * @param dishDTO 菜品信息
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 批量删除菜品并删除口味
     * @param ids 菜品ID列表
     */
    void removeWithFlavor(List<Long> ids);
}
