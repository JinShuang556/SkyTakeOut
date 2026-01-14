package com.qrs.controller.user;

import com.qrs.dto.AddressBookDTO;
import com.qrs.entity.AddressBook;
import com.qrs.result.Result;
import com.qrs.service.AddressBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@RequiredArgsConstructor
@Slf4j
public class AddressBookController {

    private final AddressBookService addressBookService;

    /**
     * 查询当前登录用户的所有地址信息
     * @return 地址簿列表
     */
    @GetMapping("/list")
    public Result<List<AddressBook>> list(){
        log.info("查询当前登录用户的所有地址信息");
        List<AddressBook> addressBookList = addressBookService.getList();
        return Result.success(addressBookList);
    }

    /**
     * 添加地址信息
     * @param addressBookDTO 地址信息
     * @return 添加结果
     */
    @PostMapping
    public Result add(@RequestBody AddressBookDTO addressBookDTO){
        log.info("添加地址信息:{}", addressBookDTO);
        addressBookService.insert(addressBookDTO);
        return Result.success();
    }
}
