package com.qrs.service;

import com.qrs.dto.DishDTO;
import com.qrs.dto.DishPageDTO;
import com.qrs.vo.PageVO;

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
}
