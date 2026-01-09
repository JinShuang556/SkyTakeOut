package com.qrs.controller.user;

import com.qrs.entity.Category;
import com.qrs.result.Result;
import com.qrs.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("userCategoryController")
@RequestMapping("/user/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 根据类型查询分类
     * @param type 类型
     * @return 分类列表
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        log.info("根据类型查询分类:{}",type);
        List<Category> list = categoryService.selectByType(type);
        return Result.success(list);
    }


}
