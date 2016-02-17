package com.vsc.legacy.validation.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vsc.legacy.validation.contraints.impl.GuestCountValidationImpl;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=GuestCountValidationImpl.class)
public @interface GuestCountValidation {

	 String message() default "";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
    
     String count();
	
     String ageQualifyingCode();
	
}