package kijko.web.validators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kijko.web.models.forms.RefuelingForm;

@Component
public class RefuelingFormValidator implements Validator {

	private static final String TYPE_ERROR_MSG = "Zły typ";
	private static final String NULL_ERROR_MSG = "Pola nie mogą mieć wartości null";
	private static final String PRICE_ERROR_MSG = "Cena nie może być mniejsza od 0";
	private static final String METER_STATUS_ERROR_MSG = "Licznik przy tankowaniu nie może być mniejszy niż poprzednio.";
	private static final String LITRES_ERROR_MSG = "Ilość paliwa musi być większa od 0.";

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RefuelingForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RefuelingForm<?> reForm = null;
		if (supports(target.getClass()))
			reForm = (RefuelingForm<?>) target;
		else {
			errors.reject("type_error", TYPE_ERROR_MSG);
			return;
		}
		
		if (anyFieldIsNull(reForm)) {
			errors.reject("null_error", NULL_ERROR_MSG);
			return;
		}
		
		checkNumberFields(reForm, errors);
		checkDateFormat(reForm, errors);
		checkTimeFormat(reForm, errors);
	}
	
	private void checkTimeFormat(RefuelingForm<?> reForm, Errors errors) {
		try {
			LocalTime.parse(reForm.getTimeInString(), DateTimeFormatter.ofPattern("kk:mm"));
		} catch (DateTimeParseException exception) {
			errors.reject("wrong_time_format");
		}
	}

	private void checkDateFormat(RefuelingForm<?> reForm, Errors errors) {
		try {
			LocalDate.parse(reForm.getDateInString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		} catch (DateTimeParseException exception) {
			errors.reject("wrong_date_format");
		}
	}

	private void checkNumberFields(RefuelingForm<?> reForm, Errors errors) {
		double priceForLiter = reForm.getPriceForLiterInThisTime();
		if (priceForLiter <= 0)
			errors.reject("price_negative_or_zero_error", PRICE_ERROR_MSG);
		
		Long meterStatusAfterRefueling = reForm.getMeterStatusInThisTime();
		Long meterStatusAfterLastRefueling = reForm.getVehicle().getMeterStatus();
		if (meterStatusAfterRefueling < meterStatusAfterLastRefueling)
			errors.reject("meter_status_less_than_vehicle_error", METER_STATUS_ERROR_MSG);
		
		double litres = reForm.getLitres();
		if (litres <= 0) 
			errors.reject("litres_zero_of_negative_error", LITRES_ERROR_MSG);
	}
	
	private boolean anyFieldIsNull(RefuelingForm<?> reForm) {
		return reForm.getDateInString() == null 
				|| reForm.getFuelType() == null
				|| reForm.getMeterStatusInThisTime() == null
				|| reForm.getPerson() == null
				|| reForm.getTimeInString() == null
				|| reForm.getVehicle() == null;
	}

}
