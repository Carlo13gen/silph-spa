package it.silph.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.silph.model.Richiesta;

@Component
public class RichiestaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Richiesta.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeCliente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognomeCliente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailCliente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foto", "required");
	}

}