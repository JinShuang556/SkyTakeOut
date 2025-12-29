package com.qrs.service;

import com.qrs.dto.CategoryDTO;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.vo.PageVO;

public interface CategoryService {
    PageVO page(CategoryPageDTO categoryPageDTO);

    void addCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    void updateStatus(Integer status, Long id);
}
