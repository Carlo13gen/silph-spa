package it.silph.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.silph.model.dipendente.Dipendente;

@Component
public class DipendenteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Dipendente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "username", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "required");
		
	}

}
