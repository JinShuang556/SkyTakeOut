package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.context.BaseContext;
import com.qrs.dto.CategoryDTO;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.entity.Category;
import com.qrs.mapper.CategoryMapper;
import com.qrs.service.CategoryService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public PageVO page(CategoryPageDTO categoryPageDTO) {
        PageHelper.startPage(categoryPageDTO.getPage(),categoryPageDTO.getPageSize());
        Page<Category> p = categoryMapper.page(categoryPageDTO);
        return PageVO.builder()
                .total(p.getTotal())
                .records(p.getResult())
                .build();
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setStatus(1);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.insert(category);
    }
}
