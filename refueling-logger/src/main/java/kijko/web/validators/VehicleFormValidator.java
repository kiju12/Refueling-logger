package kijko.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kijko.web.models.forms.VehicleForm;

@Component
public class VehicleFormValidator implements Validator {

	private static final String TYPE_ERROR_MSG = "Obiekt nie jest typu VehicleForm";
	private static final String NULL_ERROR_MSG = "Żadno z pól nie może mieć wartość null";
	private static final String LENGTH_ERROR_MSG = "Marka i model min. 2 i max. 32 znaki.";
	private static final String METER_NEGATIVE_ERROR_MSG = "Stan licznika nie moze byc mniejszy od 0.";
	private static final String YEAR_NEGATIVE_ERROR_MSG = "Rok produkcji nie moze być mniejszy od 0";

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(VehicleForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		VehicleForm vehForm = null;
		if (supports(target.getClass()))
			vehForm = (VehicleForm) target;
		else {
			errors.reject("type_error", TYPE_ERROR_MSG);
			return;
		}
		
		if (anyFieldIsNull(vehForm)) {
			errors.reject("null_error", NULL_ERROR_MSG);
			return;
		}
		

		checkFieldsLength(vehForm, errors);
		checkNotNegativeFields(vehForm, errors);
	}

	private boolean anyFieldIsNull(VehicleForm vehForm) {
		return vehForm.getMark() == null 
				|| vehForm.getModel() == null 
				|| vehForm.getMeterStatus() == null
				|| vehForm.getVehicleType() == null
				|| vehForm.getYearOfProduction() == null;			
	}
	

	private void checkFieldsLength(VehicleForm vehForm, Errors errors) {
		int markLength = vehForm.getMark().length();
		int modelLength = vehForm.getModel().length();
		
		if(markLength < 2 || modelLength < 2)
			errors.reject("too_short_error", LENGTH_ERROR_MSG);
		
		if (markLength > 32 || modelLength > 32)
			errors.reject("too_long_error", LENGTH_ERROR_MSG);
	}

	private void checkNotNegativeFields(VehicleForm vehForm, Errors errors) {
		if (vehForm.getMeterStatus() < 0)
			errors.reject("negative_number_error", METER_NEGATIVE_ERROR_MSG);
		
		if (vehForm.getYearOfProduction() < 0)
			errors.reject("negative_number_error", YEAR_NEGATIVE_ERROR_MSG);
	}
}
