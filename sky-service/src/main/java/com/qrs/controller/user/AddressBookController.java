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

    /**
     * 根据id查询地址信息
     * @param id 地址id
     * @return 地址信息
     */
    @GetMapping("/{id}")
    public Result<AddressBook> selectById(@PathVariable Long id){
        log.info("根据Id查询地址信息:{}", id);
        AddressBook addressBook = addressBookService.getAddressBookById(id);
        return Result.success(addressBook);
    }

    /**
     * 删除地址信息
     * @param id 地址id
     * @return 删除结果
     */
    @DeleteMapping("/")
    public Result delete(Long id){
        log.info("删除地址信息:{}", id);
        addressBookService.delete(id);
        return Result.success();
    }

    /**
     * 修改地址信息
     * @param addressBookDTO 地址信息
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody AddressBookDTO addressBookDTO){
        log.info("修改地址信息:{}", addressBookDTO);
        addressBookService.update(addressBookDTO);
        return Result.success();
    }

    /**
     * 获取默认地址
     * @return 默认地址
     */
    @GetMapping("/default")
    public Result<AddressBook> getDefault(){
        log.info("获取默认地址");
        AddressBook addressBook = addressBookService.getDefault();
        if(addressBook == null){
            return Result.error("未找到默认地址");
        }
        return Result.success(addressBook);
    }

    /**
     * 设置默认地址
     * @param addressBook 地址
     * @return 设置结果
     */
    @PutMapping("/default")
    public Result setDefault(@RequestBody AddressBook addressBook){
        log.info("设置默认地址:{}", addressBook.getId());
        addressBookService.setDefault(addressBook);
        return Result.success();
    }

}
