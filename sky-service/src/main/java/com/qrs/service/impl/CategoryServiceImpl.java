package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.entity.Category;
import com.qrs.mapper.CategoryMapper;
import com.qrs.service.CategoryService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
