package com.apress.prospring5.ch10;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("singerValidator")
public class SingerValidator implements Validator {

//    supports（）方法 指示验证器是否支持验证传入的类类型
    @Override
    public boolean supports(Class<?> clazz) {
        return Singer.class.equals(clazz);
    }

//     validate（）方法对传入的对象进行验证
    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "firstName", "firstName.empty");
    }
}
