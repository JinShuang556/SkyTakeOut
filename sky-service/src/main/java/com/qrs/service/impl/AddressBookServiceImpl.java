package com.qrs.service.impl;

import com.qrs.context.BaseContext;
import com.qrs.dto.AddressBookDTO;
import com.qrs.entity.AddressBook;
import com.qrs.mapper.AddressBookMapper;
import com.qrs.service.AddressBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressBookServiceImpl implements AddressBookService {

    private final AddressBookMapper addressBookMapper;

    @Override
    public List<AddressBook> getList() {
        return addressBookMapper.getList();
    }

    @Override
    public void insert(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        addressBook.setUserId(BaseContext.getCurrentId());
        //默认不是默认地址
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    @Override
    public AddressBook getAddressBookById(Long id) {
        return addressBookMapper.getAddressBookById(id);
    }

    @Override
    public void delete(Long id) {
        addressBookMapper.deleteById(id);
    }

    @Override
    public void update(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        addressBookMapper.updateById(addressBook);
    }

    @Override
    public AddressBook getDefault() {
        return addressBookMapper.getDefault(BaseContext.getCurrentId());
    }

    @Transactional
    @Override
    public void setDefault(AddressBook addressBook) {
        //先把所有的设置为0(非默认)
        addressBookMapper.updateDefaultByUserId(BaseContext.getCurrentId());
        // 设置为默认地址（1表示默认，0表示非默认）
        addressBook.setIsDefault(1);
        // 更新数据库中的地址信息
        addressBookMapper.updateById(addressBook);
    }
}
