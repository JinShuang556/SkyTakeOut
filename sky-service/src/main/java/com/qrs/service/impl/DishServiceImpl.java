package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.dto.DishDTO;
import com.qrs.dto.DishPageDTO;
import com.qrs.entity.Dish;
import com.qrs.entity.DishFlavor;
import com.qrs.mapper.DishFlavorMapper;
import com.qrs.mapper.DishMapper;
import com.qrs.service.DishService;
import com.qrs.vo.DishVO;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;
    private final DishFlavorMapper dishFlavorMapper;


    @Override
    public PageVO page(DishPageDTO dishPageDTO) {
        PageHelper.startPage(dishPageDTO.getPage(),dishPageDTO.getPageSize());
        Page<DishVO> p = dishMapper.page(dishPageDTO);
        return PageVO.builder()
                .total(p.getTotal())
                .records(p.getResult())
                .build();
    }

    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        //1.添加菜品
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.addDish(dish);
        //2.添加菜品口味
        List<DishFlavor> flavors = dishDTO.getFlavors();

        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach(flavor -> {
                //获得菜品id
                flavor.setDishId(dish.getId());
                //单个插入：
//                dishFlavorMapper.addDishFlavor(flavor);
            });
            //批量插入：
            dishFlavorMapper.insertBatch(flavors);
        }
    }
}
