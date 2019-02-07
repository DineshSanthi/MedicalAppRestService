package com.repo.depo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.repo.depo.model.Application;
//
@Component("beforeCreateApplicationValidator")
public class ApplicationValidator implements Validator {
	 
    @Override
    public boolean supports(Class<?> clazz) {
        return Application.class.equals(clazz);
    }
 
    @Override
    public void validate(Object obj, Errors errors) {
    	Application app = (Application) obj;
        if (checkInputString(app.getAppName())) {
            errors.rejectValue("name", "appName.empty");
        }
    
        if (checkInputString(app.getAppDescription())) {
            errors.rejectValue("Description", "appDescription.empty");
        }
    }
 
    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}