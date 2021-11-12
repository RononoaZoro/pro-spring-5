package com.apress.prospring5.ch10;

import com.apress.prospring5.ch10.obj.Singer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements
        ConstraintValidator<CheckCountrySinger, Singer> {

    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {
    }

    @Override
    public boolean isValid(Singer singer,
                           ConstraintValidatorContext context) {
        return singer.getGenre() == null ||
                (!singer.isCountrySinger() || (singer.getLastName() != null && singer.getGender() != null));
    }
}
