package com.qrs.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EmployeeLoginVo类
 * 用于封装员工登录相关信息的数据传输对象
 * 使用了Lombok注解简化代码
 */
@Data // 使用Lombok的@Data注解，自动生成getter、setter、equals、hashCode和toString方法
@Builder // 使用Lombok的@Builder注解，提供Builder模式构建对象
@AllArgsConstructor // 使用Lombok的全参构造方法注解，生成包含所有字段参数的构造方法
@NoArgsConstructor // 使用Lombok的无参构造方法注解，生成无参构造方法
public class EmployeeLoginVO {
    private Long id; //员工id
    private String username; // 用户名
    private String name; // 员工姓名
    private String token; // 登录令牌
}
