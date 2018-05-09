package kijko.web.validators;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public abstract class ValidatorTest {

	protected BindingResult errorLog;
	
	protected boolean hasError(String code) {
		for (ObjectError error : errorLog.getAllErrors())
			if (error.getCode().equals(code))
				return true;
		
		return false;
	}
	
	protected String generateStringOfLength(int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			str += "a";
		}
		return str;
	}
}
