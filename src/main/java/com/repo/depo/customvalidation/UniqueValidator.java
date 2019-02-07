package com.repo.depo.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueName, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!value.matches("[a-zA-Z][a-zA-Z0-9_]*"))
		{
			return false;	
		}
		return true;
	}

	@Override
	public void initialize(UniqueName constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}
