package com.projeto.flexmeeting.domain.web.validator;

import java.time.LocalTime;

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
		
		Reuniao r = (Reuniao) object;
		
		LocalTime inicio = r.getHoraInicio();
		
		if (r.getHoraFim() != null) {
			if (r.getHoraFim().isBefore(inicio)) {
				errors.rejectValue("horaFim", "PosteriorHoraInicio.reuniao.horaFim");
			}
		}
	}

}
