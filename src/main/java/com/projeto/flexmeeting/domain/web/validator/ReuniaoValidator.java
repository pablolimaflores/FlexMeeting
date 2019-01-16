package com.projeto.flexmeeting.domain.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.projeto.flexmeeting.domain.entity.Reuniao;

public class ReuniaoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Reuniao.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
//		Reuniao f = (Reuniao) object;
//		
//		LocalDate entrada = f.getDataEntrada();
//		
//		if (f.getDataSaida() != null) {
//			if (f.getDataSaida().isBefore(entrada)) {
//				errors.rejectValue("dataSaida", "PosteriorDataEntrada.reuniao.dataSaida");
//			}
//		}
	}

}
