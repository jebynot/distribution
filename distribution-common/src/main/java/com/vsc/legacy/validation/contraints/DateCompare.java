package com.vsc.legacy.validation.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vsc.legacy.validation.contraints.impl.DateCompareImpl;


@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DateCompareImpl.class)
public @interface DateCompare {

	 String message() default "";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
    
     String fromDatePropertyName();
	
     String toDatePropertyName();

	
}
