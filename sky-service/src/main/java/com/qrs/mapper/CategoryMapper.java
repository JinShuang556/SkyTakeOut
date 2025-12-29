package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    Page<Category> page(CategoryPageDTO categoryPageDTO);
}
