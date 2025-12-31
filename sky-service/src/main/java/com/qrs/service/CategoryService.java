package com.qrs.service;

import com.qrs.dto.CategoryDTO;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.entity.Category;
import com.qrs.vo.PageVO;

import java.util.List;

public interface CategoryService {
    /**
     * 分类分页查询
     * @param categoryPageDTO 分页参数
     * @return 分页结果
     */
    PageVO page(CategoryPageDTO categoryPageDTO);

    /**
     * 新增分类
     * @param categoryDTO 分类参数
     */
    void addCategory(CategoryDTO categoryDTO);

    /**
     * 删除分类
     * @param id 分类id
     */
    void deleteCategory(Long id);

    /**
     * 修改分类状态
     * @param status 分类状态
     * @param id 分类id
     */
    void updateStatus(Integer status, Long id);

    /**
     * 修改分类
     * @param categoryDTO 分类参数
     */
    void updateCategory(CategoryDTO categoryDTO);

    /**
     * 根据类型查询分类
     * @param type 类型
     * @return 分类列表
     */
    List<Category> selectByType(Integer type);
}
