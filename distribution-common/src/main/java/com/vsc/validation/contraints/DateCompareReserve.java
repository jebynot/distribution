package com.vsc.validation.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vsc.validation.contraints.impl.DateCompareReserveImpl;


@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DateCompareReserveImpl.class)
public @interface DateCompareReserve {
	
	 String message() default "";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
    
     String startDatePropertyName();
	
     String endDatePropertyName();
}
