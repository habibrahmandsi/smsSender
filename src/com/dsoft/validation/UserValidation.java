package com.dsoft.validation;

import com.dsoft.entity.User;
import com.dsoft.service.AdminService;
import com.dsoft.util.Utils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component("userValidator")
public class UserValidation /*implements Validator*/ {
    private static Logger logger = Logger.getLogger(UserValidation.class);

    public void validate(Object target, Errors errors, AdminService adminService) {
        User user = (User) target;


/*        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
                "userName",
                "User Name must not be Empty.");*/

       /* if (Utils.isEmpty(user.getUserName())) {
            errors.rejectValue("userName",
                    "userName",
                    "User Name must not be Empty.");
        } else if (!(user.getId() > 0) && adminService.isUserNameExist(user.getUserName())) {
            errors.rejectValue("userName",
                    "userName",
                    "User Not Unique.");

        } else if ((Utils.isEmpty(user.getGivenPassword()))) {
            errors.rejectValue("password",
                    "password",
                    "Password Must Not be Empty.");
        } else if (!(user.getGivenPassword()).equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword",
                    "confirmPassword",
                    "Password Does Not match.");
        }else if (user.getUserTypeId() == 0) {
            errors.rejectValue("userTypeId",
                    "userTypeId",
                    "Select a valid employee name.");
        }*/
    }
    public void validateNewPassword(Object target, Errors errors, AdminService adminService) {
        User user = (User) target;

        if (Utils.isEmpty(user.getUserName())) {
            errors.rejectValue("userName",
                    "userName",
                    "User Name must not be Empty.");
        } /*else if (!(user.getId() > 0) && adminService.isUserNameExist(user.getUserName())) {
            errors.rejectValue("userName",
                    "userName",
                    "User Not Unique.");
        }else if (!(user.getGivenPassword()).equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword",
                    "confirmPassword",
                    "Password Does Not match.");

        }*/
    }
    public void validatePassword(Object target, Errors errors, AdminService adminService) {
        User user = (User) target;

        if (Utils.isEmpty(user.getUserName())) {
            errors.rejectValue("userName",
                    "userName",
                    "User Name must not be Empty.");
        } /*else if (!(user.getId() > 0) && adminService.isUserNameExist(user.getUserName())) {
            errors.rejectValue("userName",
                    "userName",
                    "User Not Unique.");
        }else if (!(user.getGivenPassword()).equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword",
                    "confirmPassword",
                    "Password Does Not match.");

        }*/
    }

}
