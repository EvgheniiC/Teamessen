package eventim.teamessen.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import eventim.teamessen.entity.Users;
import eventim.teamessen.form.UsersForm;
import eventim.teamessen.repos.UsersRepository;

@Component
public class UsersValidator implements Validator {

    @Autowired
    UsersRepository repository;
    private final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    Pattern pattern;
    Matcher matcher;

    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UsersForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UsersForm userForm = (UsersForm) target;
        System.out.println(userForm.getAvatar());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userForm.confirmPassword");

        if (!userForm.getEmail()
                     .isEmpty()) {
            Users users = repository.findById(userForm.getEmail()
                                                      .toLowerCase())
                                    .orElse(null);
            if (users != null) {
                // Email has been used by another account.
                errors.rejectValue("email", "Duplicate.userForm.login");
            }

            if (!validate(EMAIL_PATTERN, userForm.getEmail())) {
                errors.rejectValue("email", "Match.userForm.email");
            }
        }
        if (!errors.hasErrors()) {
            if (!userForm.getConfirmPassword()
                         .equals(userForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.userForm.confirmPassword");
            }
        }
        if (!userForm.getPassword()
                     .isEmpty() && !validate(PASSWORD_PATTERN, userForm.getPassword())) {
            errors.rejectValue("confirmPassword", "Match.userForm.password");
        }

    }

    private boolean validate(String pat, String str) {
        pattern = Pattern.compile(pat);
        matcher = pattern.matcher(str);
        return matcher.matches();
    }

}


