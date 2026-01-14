package com.qrs.service.impl;

import com.qrs.context.BaseContext;
import com.qrs.dto.AddressBookDTO;
import com.qrs.entity.AddressBook;
import com.qrs.mapper.AddressBookMapper;
import com.qrs.service.AddressBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
