package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    Page<Category> page(CategoryPageDTO categoryPageDTO);

    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "VALUE (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);
}
