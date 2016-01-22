package com.vsc.validation.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vsc.validation.contraints.impl.ReservationValidationImpl;;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=ReservationValidationImpl.class)
public @interface ReservationValidation {
	String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
   
    String unitRateType();
	
   String stayDateRange();
   
   String resTotal();
   
   String guestCounts();
	
}
