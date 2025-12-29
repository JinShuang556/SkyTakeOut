package com.qrs.controller.admin;

import com.qrs.dto.CategoryPageDTO;
import com.qrs.result.Result;
import com.qrs.service.CategoryService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    /**
     * 分类管理分页查询
     * @return
     */
    @GetMapping("/page")
    public Result page(CategoryPageDTO categoryPageDTO){
        log.info("分类管理分页查询:{}",categoryPageDTO);
        PageVO pageVO = categoryService.page(categoryPageDTO);
        return Result.success(pageVO);
    }

}
