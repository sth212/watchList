package com.example.demo.Validation;

import com.example.demo.models.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem>{

    @Override
    public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {

        if(value.getRating()=="")return true;
        return !(Double.valueOf(value.getRating()) >= 8 &&  "L".equals(value.getPriority()));
    }
}