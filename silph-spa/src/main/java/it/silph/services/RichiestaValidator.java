<<<<<<< HEAD:silph-spa/src/main/java/it/silph/services/RichiestaValidator.java
package it.silph.services;

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
=======
package it.silph.services;

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
>>>>>>> parent of 1a0d2ec... Merge branch 'master' of https://github.com/Carlo13gen/silph-spa:silph-spa/src/main/java/it/silph/services/RichiestaValidator.java
