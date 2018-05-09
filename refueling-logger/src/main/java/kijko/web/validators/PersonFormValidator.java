package kijko.web.validators;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kijko.web.models.Person;
import kijko.web.models.forms.PersonForm;
import kijko.web.services.PersonService;

@Component
public class PersonFormValidator implements Validator {
	
	@Autowired
	private PersonService personService;
	
	private static final String TYPE_ERROR_MSG = "Obiekt nie jest typu PersonForm.";
	private static final String NULL_ERROR_MSG = "Imię lub nazwisko nie może mieć wartości null";
	private static final String LENGTH_ERROR_MSG = "Imię lub nazwisko min. 3 i max. 32 znaki.";
	private static final String EXISTS_ERROR_MSG = "Istnieje już osoba o tym imieniu i nazwisku.";
	private static final String HAS_NUMBERS_MSG = "Imię i Nazwisko nie może zawierac liczb.";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(PersonForm.class);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		PersonForm personForm = null;
		if (supports(target.getClass()))
			personForm = (PersonForm) target;
		else {
			errors.reject("type_error", TYPE_ERROR_MSG);
			return;
		}

		if (isFirstOrLastNameNull(personForm)) {
			errors.reject("null_error", NULL_ERROR_MSG);
			return;
		}

		checkForNumbers(personForm, errors);
		checkFieldsLength(personForm, errors);
		checkExists(personForm, errors);
	}

	private boolean isFirstOrLastNameNull(PersonForm personForm) {
		return personForm.getFirstName() == null || personForm.getLastName() == null;
	}

	
	private void checkForNumbers(PersonForm form, Errors errors) {
		String firstName = form.getFirstName();
		String lastName = form.getLastName();
		
		if (hasNumbers(firstName) || hasNumbers(lastName))
			errors.reject("has_numbers_error", HAS_NUMBERS_MSG);
	}
	
	private boolean hasNumbers(String name) {
		return name.matches(".*\\d+.*");
	}
	
	private void checkFieldsLength(PersonForm form, Errors errors) {
		int firstNameLength = form.getFirstName().length();
		int lastNameLength = form.getLastName().length();

		if (firstNameLength < 3 || lastNameLength < 3)
			errors.reject("too_short_error", LENGTH_ERROR_MSG);

		if (firstNameLength > 32 || lastNameLength > 32)
			errors.reject("too_long_error", LENGTH_ERROR_MSG);

	}

	private void checkExists(PersonForm form, Errors errors) {
		final String firstNameFromForm = form.getFirstName();
		final String lastNameFromForm = form.getLastName();

		Predicate<Person> existsCheckFun = eachPerson -> {
			return eachPerson.getFirstName().equalsIgnoreCase(firstNameFromForm)
					&& eachPerson.getLastName().equalsIgnoreCase(lastNameFromForm);
		};

		boolean personExists = personService.getAllPersons().stream().anyMatch(existsCheckFun);

		if (personExists)
			errors.reject("exists_error", EXISTS_ERROR_MSG);
	}
}
