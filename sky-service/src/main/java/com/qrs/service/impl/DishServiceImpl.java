package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.dto.DishPageDTO;
import com.qrs.mapper.DishMapper;
import com.qrs.service.DishService;
import com.qrs.vo.DishVO;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;

    @Override
    public PageVO page(DishPageDTO dishPageDTO) {
        PageHelper.startPage(dishPageDTO.getPage(),dishPageDTO.getPageSize());
        Page<DishVO> p = dishMapper.page(dishPageDTO);
        return PageVO.builder()
                .total(p.getTotal())
                .records(p.getResult())
                .build();
    }
}
