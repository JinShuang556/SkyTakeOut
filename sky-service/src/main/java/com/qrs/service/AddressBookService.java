package com.qrs.service;

import com.qrs.dto.AddressBookDTO;
import com.qrs.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    /**
     * 查询当前登录用户的所有地址信息
     * @return 地址簿列表
     */
    List<AddressBook> getList();

    /**
     * 添加收货地址信息
     * @param addressBookDTO 地址信息
     */
    void insert(AddressBookDTO addressBookDTO);
}
