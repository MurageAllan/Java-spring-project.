package com.LoveToCode.springmvc.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerCodeCustomValidator implements ConstraintValidator<CustomerCode, String> {

	private String postalCode;

	@Override
	public void initialize(CustomerCode code) {
		postalCode = code.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean result;
		if (value != null) {
			result = value.contains(postalCode);
		}
		else {
			result = true;
		}
		return result;

	}

}
