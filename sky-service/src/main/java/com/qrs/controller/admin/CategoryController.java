package com.qrs.controller.admin;

import com.qrs.dto.CategoryDTO;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.result.Result;
import com.qrs.service.CategoryService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    /**
     * 分类管理分页查询
     * @param categoryPageDTO 分页查询条件
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result page(CategoryPageDTO categoryPageDTO){
        log.info("分类管理分页查询:{}",categoryPageDTO);
        PageVO pageVO = categoryService.page(categoryPageDTO);
        return Result.success(pageVO);
    }

    /**
     * 新增分类
     * @param categoryDTO 分类信息
     * @return 新增结果
     */
    @PostMapping()
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("添加分类:{}",categoryDTO);
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

}
