package com.repo.depo.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidateName, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!value.matches("[a-zA-Z][a-zA-Z0-9_]*"))
		{
			return false;	
		}
		return true;
	}

	@Override
	public void initialize(ValidateName constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}
