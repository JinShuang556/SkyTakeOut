package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.annotation.AutoFill;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.entity.Category;
import com.qrs.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 分类管理分页查询
     * @param categoryPageDTO 分页查询条件
     * @return 分类分页结果
     */
    Page<Category> page(CategoryPageDTO categoryPageDTO);

    /**
     * 新增分类
     * @param category 分类信息
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "VALUE (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 根据ID删除分类
     * @param id 分类ID
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据ID更新分类
     * @param category 分类信息
     */
    @AutoFill(OperationType.UPDATE)
    void updateById(Category category);

    /**
     * 根据类型查询分类
     * @param type 类型
     * @return 分类列表
     */
    List<Category> selectByType(Integer type);
}
