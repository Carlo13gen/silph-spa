package it.silph.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.silph.model.Fotografo;

@Component
public class FotografoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Fotografo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "cognome", "required");
		
	}

}
