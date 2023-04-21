package com.LoveToCode.springmvc.entity.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CustomerCodeCustomValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface CustomerCode {

	public String message() default "must contain code";

	public String value() default "code";

	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};

}
