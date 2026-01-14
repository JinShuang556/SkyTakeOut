package com.qrs.mapper;

import com.qrs.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    /**
     * 查询所有地址信息
     * @return 地址信息列表
     */
    @Select("select * from address_book")
    List<AddressBook> getList();

    /**
     * 添加地址信息
     * @param addressBook 地址信息
     */
    @Insert("insert into address_book (user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default)" +
            " value (#{userId},#{consignee},#{sex},#{phone},#{provinceCode},#{provinceName},#{cityCode},#{cityName},#{districtCode},#{districtName},#{detail},#{label},#{isDefault})")
    void insert(AddressBook addressBook);

    /**
     * 根据id查询地址信息
     * @param id 地址id
     * @return 地址信息
     */
    @Select("select * from address_book where id = #{id}")
    AddressBook getAddressBookById(Long id);

    /**
     * 根据id删除地址信息
     * @param id 地址id
     */
    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);

}
