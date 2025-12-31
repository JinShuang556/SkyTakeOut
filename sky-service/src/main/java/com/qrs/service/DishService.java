package com.qrs.service;

import com.qrs.dto.DishPageDTO;
import com.qrs.vo.PageVO;

public interface DishService {


    PageVO page(DishPageDTO dishPageDTO);
}
