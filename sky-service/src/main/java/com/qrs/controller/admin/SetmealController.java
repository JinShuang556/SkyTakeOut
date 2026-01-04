package com.qrs.controller.admin;

import com.qrs.dto.SetmealPageDTO;
import com.qrs.result.Result;
import com.qrs.service.SetmealService;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
@RequiredArgsConstructor
public class SetmealController {

    private final SetmealService setmealService;
    /**
     * 套餐分页查询
     * @param setmealPageDTO 分页参数
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<PageVO> page(SetmealPageDTO setmealPageDTO){
        log.info("套餐分页查询{}",setmealPageDTO);
        PageVO pageVO = setmealService.page(setmealPageDTO);
        return Result.success(pageVO);
    }

}
