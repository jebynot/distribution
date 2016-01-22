package com.vsc.validation.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vsc.validation.contraints.impl.StateReserveValidationImpl;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=StateReserveValidationImpl.class)
public @interface StateReserveValidation {
	String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String statecode();
    String statename();
}



