package com.qrs.aspect;

import com.qrs.annotation.AutoFill;
import com.qrs.constant.AutoFillConstant;
import com.qrs.context.BaseContext;
import com.qrs.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 编写切入点
     */
    @Pointcut("execution(* com.qrs.mapper.*.*(..)) && @annotation(com.qrs.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 编写前置通知
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("公共字段自动填充...");
        //获取方法签名对象：
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //获取方法上的注解：
        AutoFill autoFill = method.getAnnotation(AutoFill.class);
        //获得AutoFill注解中的属性值：
        OperationType operationType = autoFill.value();
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return ;
        }
        //规定第一个为实体对象
        Object entity = args[0];
        //准备参数：
        LocalDateTime now = LocalDateTime.now();
        Long empId = BaseContext.getCurrentId();
        //判断操作类型：
        if(operationType == OperationType.INSERT){
            //为参数赋值：
            //1.先获取方法
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //2.再通过反射调用获取的方法
                setCreateTime.invoke(entity,now);
                setUpdateTime.invoke(entity,now);
                setCreateUser.invoke(entity,empId);
                setUpdateUser.invoke(entity,empId);
            } catch (Exception e) {
                log.error("公共字段自动填充异常：{}",e.getMessage());
            }

        } else if (operationType == OperationType.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //2.再通过反射调用获取的方法
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,empId);
            } catch (Exception e) {
                log.error("公共字段自动填充异常：{}",e.getMessage());
            }
        }


    }

}
