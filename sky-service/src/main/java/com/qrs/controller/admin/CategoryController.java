package com.qrs.controller.admin;

import com.qrs.dto.CategoryDTO;
import com.qrs.dto.CategoryPageDTO;
import com.qrs.result.Result;
import com.qrs.service.CategoryService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
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
    @PostMapping
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("添加分类:{}",categoryDTO);
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 删除分类
     * @param id 分类id
     * @return 删除结果
     */
    @DeleteMapping
    public Result deleteCategory(Long id){
        log.info("删除分类:{}",id);
        categoryService.deleteCategory(id);
        return Result.success();
    }

    /**
     * 修改分类状态
     * @param status 分类状态
     * @param id 分类id
     * @return 修改结果
     */
    @PostMapping("status/{status}")
    public Result StartORStop(@PathVariable Integer status, Long id) {
        log.info("修改分类状态:{},id:{}",status,id);
        categoryService.updateStatus(status,id);
        return Result.success();
    }
}
