package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.dto.SetmealPageDTO;
import com.qrs.mapper.SetmealMapper;
import com.qrs.service.SetmealService;
import com.qrs.vo.PageVO;
import com.qrs.vo.SetmealPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetmealServiceImpl implements SetmealService {

    private final SetmealMapper setmealMapper;

    @Override
    public PageVO page(SetmealPageDTO setmealPageDTO) {
        PageHelper.startPage(setmealPageDTO.getPage(), setmealPageDTO.getPageSize());
        Page<SetmealPageVO> page = setmealMapper.page(setmealPageDTO);
        return new PageVO(page.getTotal(), page.getResult());
    }
}
