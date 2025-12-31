package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.dto.DishPageDTO;
import com.qrs.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {
    Page<DishVO> page(DishPageDTO dishPageDTO);
}
