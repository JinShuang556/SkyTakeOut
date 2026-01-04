package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.dto.SetmealPageDTO;
import com.qrs.dto.SetmealWithSetmealDishDTO;
import com.qrs.entity.Setmeal;
import com.qrs.entity.SetmealDish;
import com.qrs.mapper.SetmealDishMapper;
import com.qrs.mapper.SetmealMapper;
import com.qrs.service.SetmealService;
import com.qrs.vo.PageVO;
import com.qrs.vo.SetmealPageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SetmealServiceImpl implements SetmealService {

    private final SetmealMapper setmealMapper;
    private final SetmealDishMapper setmealDishMapper;

    @Override
    public PageVO page(SetmealPageDTO setmealPageDTO) {
        PageHelper.startPage(setmealPageDTO.getPage(), setmealPageDTO.getPageSize());
        Page<SetmealPageVO> page = setmealMapper.page(setmealPageDTO);
        return new PageVO(page.getTotal(), page.getResult());
    }

    @Transactional
    @Override
    public void addSetmealWithSetmealDish(SetmealWithSetmealDishDTO setmealWithSetmealDishDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealWithSetmealDishDTO, setmeal);
        log.info("新增套餐：{}", setmeal);
        setmealMapper.insert(setmeal);
        List<SetmealDish> setmealDishes = setmealWithSetmealDishDTO.getSetmealDishes();
        if(setmealDishes==null||setmealDishes.isEmpty()){
            throw new RuntimeException("套餐菜品不能为空");
        }
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmeal.getId());
        }
        log.info("新增套餐菜品：{}", setmealDishes);
        setmealDishMapper.insertBatch(setmealDishes);
        log.info("新增套餐成功");
    }

    @Transactional
    @Override
    public void deleteSetmealWithSetmealDish(List<Long> ids) {
        setmealMapper.deleteBatch(ids);
        setmealDishMapper.deleteSetmealDishBySetmealIds(ids);
    }
}
