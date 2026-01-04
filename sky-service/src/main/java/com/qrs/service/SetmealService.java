package com.qrs.service;

import com.qrs.dto.SetmealPageDTO;
import com.qrs.vo.PageVO;

public interface SetmealService {
    /**
     * 套餐分页查询
     * @param setmealPageDTO 分页参数
     * @return 分页结果
     */
    PageVO page(SetmealPageDTO setmealPageDTO);
}
