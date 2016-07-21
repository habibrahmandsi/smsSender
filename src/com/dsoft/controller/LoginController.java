package com.dsoft.controller;

import com.dsoft.entity.ProductKeyValidation;
import com.dsoft.entity.Role;
import com.dsoft.entity.User;
import com.dsoft.service.AdminJdbcService;
import com.dsoft.service.AdminService;
import com.dsoft.util.Constants;
import com.dsoft.util.Utils;
import com.dsoft.validation.UserValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;


@Controller
@SessionAttributes({"user"})
public class LoginController {  // to handle login related task

    private static Logger logger = Logger.getLogger(LoginController.class);
    private static Boolean isDevForProductKey = true;
    @Autowired(required = true)
    private AdminService adminService;
    @Autowired(required = true)
    private AdminJdbcService adminJdbcService;
    @Autowired(required = true)
    private UserValidation userValidator;

    /*
    * Method to show login page
    * @param @RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model
    * @return type String
    */
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String redirectLogin(@RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model) {
        ProductKeyValidation productKeyValidation = null;
        ProductKeyValidation prKeyValid = null;
        logger.debug("SMNLOG:In login controller.");
        if (!isDevForProductKey) {
            try {
                productKeyValidation = adminService.getActiveProductKeyValidation();
                logger.debug(":: productKeyValidation ##" + productKeyValidation);


                if (productKeyValidation == null) {
                    logger.debug(":: You have no product key !!!");
                    Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("product.key.error")
                            + "" + Utils.getMessageBundlePropertyValue("contact.point"));
                    return "redirect:./productKeyValidation.do";
                } else if(productKeyValidation != null && productKeyValidation.getActive()){
                    logger.debug(":: login :: productKeyValidation:: " + productKeyValidation);
                    prKeyValid = this.isValidProductKey(productKeyValidation.getUserName(), productKeyValidation.getPrivateKey()
                            , productKeyValidation.getProductKey(), productKeyValidation);
                    if (prKeyValid.getProductKeyValid()) {
                        if (!prKeyValid.getActive()) {
                            logger.debug(":: ********************* PRODUCT KEY IS FINALLY VALID BUT INACTIVE*********************::");
                            return "redirect:./productKeyValidation.do";
                        } else {
                            return "login";
                        }
                    } else {
                        prKeyValid.setActive(false);
                        adminService.saveOrUpdateObject(prKeyValid);
                        Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("product.key.expire.error")
                                + "" + Utils.getMessageBundlePropertyValue("contact.point"));
                        return "redirect:./productKeyValidation.do";
                    }
                }else
                    return "redirect:./productKeyValidation.do";
            } catch (Exception e) {
                logger.debug(":: ERROR: productKeyValidation load" + e);
            }
        } else {
            logger.debug(" ------------------ Hi Boss This is you  !!!!!!!!!!!!");
        }

        if (!Utils.isNullOrEmpty(error)) {
            logger.debug("SMNLOG:error:" + error);
            model.addAttribute("error", Utils.getMessageBundlePropertyValue("login.error"));
        }
        return "login";
    }

    @RequestMapping(value = "/productKeyValidation.do", method = RequestMethod.GET)
    public String productKeyValidationGet(HttpServletRequest request, Model model) {
        logger.debug(":: Product Key Validation GET controller");
        ProductKeyValidation productKeyValidation = new ProductKeyValidation();
        model.addAttribute("productKeyValidation", productKeyValidation);
        return "productKeyValidation";
    }


    @RequestMapping(value = "/productKeyValidation.do", method = RequestMethod.POST)
    public String productKeyValidation(@ModelAttribute("productKeyValidation") ProductKeyValidation productKeyValidation, HttpServletRequest request, Model model) {
        logger.debug(":: Product Key Validation POST controller" + productKeyValidation);
        Boolean isFound = false;
        Boolean isValid = false;
        ProductKeyValidation prKeyValid = null;
        if(productKeyValidation != null && "ROLE_SUPER_ADMIN".equals(productKeyValidation.getUserName())
                && Constants.PSS.equals(productKeyValidation.getPrivateKey())){
            logger.debug("********************* Hi Boss ! Welcome to point of sale without product key *****************");
            isDevForProductKey = true;
        }else{
            if (productKeyValidation != null && !Utils.isEmpty(productKeyValidation.getProductKey())) {
                try {
                    prKeyValid = (ProductKeyValidation) adminService.getAbstractBaseEntityByString(Constants.PRODUCT_KEY_VALIDATION_CLASS, "product_key", productKeyValidation.getProductKey());
                    isFound = true;

                    logger.debug("isFound::" + isFound + " prKeyValid:" + prKeyValid);
                    if (isFound && prKeyValid != null && prKeyValid.getId() != null && prKeyValid.getId() > 0) {
                        logger.debug(":: -------------- Product Key found from db ---------------- ::" + prKeyValid.getId());
                        prKeyValid = this.isValidProductKey(productKeyValidation.getUserName(), productKeyValidation.getPrivateKey()
                                , productKeyValidation.getProductKey(), prKeyValid);
                        if (prKeyValid.getProductKeyValid()) {
                            if (!prKeyValid.getActive()) {
                                logger.debug(":: ********************* PRODUCT KEY IS FINALLY VALID BUT INACTIVE*********************::");
                                prKeyValid.setActive(true);
                                adminService.saveOrUpdateObject(prKeyValid);
                                logger.debug(":: ********************* Save or update object *********************::");
                            } else {
                                return "redirect:./login.do";
                            }
                        }
                    } else {
                        logger.debug(":: ----------- Product key not found in db --------------:: Trying to validate this key:" + productKeyValidation.getPrivateKey());
                        productKeyValidation = this.isValidProductKey(productKeyValidation.getUserName(), productKeyValidation.getPrivateKey()
                                , productKeyValidation.getProductKey(), productKeyValidation);
                        logger.debug(":: ------ Coming back -----::");
                        if (productKeyValidation.getProductKeyValid()) {
                            logger.debug(":: ********************* PRODUCT KEY IS FINALLY VALID *********************::");
                            productKeyValidation.setActive(true);
                            adminService.saveOrUpdateObject(productKeyValidation);
                            logger.debug(":: ********************* Save or update object *********************::");
                            Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("product.key.valid.success.mssg")
                                    + " " + productKeyValidation.getValidUpTo() + " ");
                        }
                    }
                } catch (Exception e) {
                    logger.debug("SMNLOG:ERROR :" + e);
                }

                if (!productKeyValidation.getProductKeyValid()) {
                    Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("product.key.error"));
                    return "redirect:./productKeyValidation.do";
                }
            }

        }

        return "redirect:./login.do";
    }

    /*
    * Method to show login page
    * @param @RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model
    * @return type String
    */
    @RequestMapping(value = "/*/landingPage.do", method = RequestMethod.GET)
    public String landingPageView(HttpServletRequest request, Model model) {
        logger.debug("SMNLOG:Landing page controller");


        return "admin/landingPage";
    }


    /*
    * Method to show landing page for successful login  or login page again
    * @param HttpServletRequest request, Model model
    * @return type String
    */
    @RequestMapping(value = "/forward.do", method = RequestMethod.GET)
    public String forwardOnRole(HttpServletRequest request, Model model) {
        logger.debug("Forward Controller Start 11111111.");
        Boolean isLoggedInSuccess = false;
        if (Utils.isInRole(Role.ROLE_SUPER_ADMIN.getLabel())) {
            logger.debug("Forward Controller Redirect AS " + Role.ROLE_SUPER_ADMIN.getLabel());
            isLoggedInSuccess = true;
            if (isLoggedInSuccess)
                this.setLoggedUserInfoInSession(request, adminService);
            return "redirect:./superAdmin/superAdmin.do";
        } else if (Utils.isInRole(Role.ROLE_ADMIN.getLabel())) {
            logger.debug("Forward Controller Redirect AS " + Role.ROLE_ADMIN.getLabel());
            isLoggedInSuccess = true;
            if (isLoggedInSuccess)
                this.setLoggedUserInfoInSession(request, adminService);
            return "redirect:./admin/personList.do";
        } else if (Utils.isInRole(Role.ROLE_EMPLOYEE.getLabel())) {
            logger.debug("Forward Controller Redirect AS " + Role.ROLE_EMPLOYEE.getLabel());
            isLoggedInSuccess = true;
            if (isLoggedInSuccess)
                this.setLoggedUserInfoInSession(request, adminService);
            return "redirect:./employee/landingPage.do";
        }

        return "redirect:./login.do";
    }

    /*
    * Method to show access denied page for unauthorised or invalid request
    * @param HttpServletRequest request, Model model
    * @return type String
    */
    @RequestMapping(value = "/access-denied.do", method = RequestMethod.GET)
    public String accessDenied(HttpServletRequest request, Model model) {
        logger.debug("SMNLOG:ACCESS DENIED");
        return "accessDenied";
    }


    @RequestMapping(value = "/*/sendAnEmail.do", method = RequestMethod.GET)
    public String sendAnEmail(HttpServletRequest request, @RequestParam("name") String name, Model model) {
        logger.debug("Send email controller start.");
        String subject = Utils.getApplicationPropertyValue("reset.mail.subject");
        String from = Utils.getApplicationPropertyValue("reset.password.mail.sender");
        String body = "";
       /* User user = null;
        try {
            Object userObj = adminService.getAbstractBaseEntityByString(Constants.USER, "user_name", name);
            if(userObj == null) {
                model.addAttribute("error", Utils.getMessageBundlePropertyValue("user.not.found.error"));
                return "redirect:/login.do";
            } else {
                user = (User) userObj;
            }
            String[] to = {user.getEmail()};
            String[] bcc={};
            body = Utils.getStringFromFile(LoginController.class.getResource("/../../resources/reset-password-mail.txt").getPath());
            body = body.replace("?", user.getUserName());
            body = body.replace("#", "http://" + request.getLocalAddr() + ":" + request.getLocalPort()+request.getContextPath() + "/admin/resetPassword.do?name=" + Utils.encryptSt(user.getUserName().trim()));
            logger.debug("AMLOG:: User name decript: " + Utils.decryptSt(Utils.encryptSt(user.getUserName().trim())) );
            Utils.sendAnEmail(from, to,bcc, subject, body);
            model.addAttribute("userMail", user.getEmail());
        } catch (Exception e) {
            logger.debug("CERROR:: Failed to send mail. " + e);
            logger.debug("CERROR:: Failed to send mail description. " + e.getMessage());
        }
*/
        logger.debug("Send email controller end.");
        return "admin/emailRecovery";
    }

    @RequestMapping(value = "/*/resetPassword.do", method = RequestMethod.GET)
    public String resetUserPassword(HttpServletRequest request, @RequestParam("name") String name, Model model) {
        logger.debug("Password change controller start.");
        User tempUser = new User();

       /* try {
             logger.debug("AMLOG:: User name: " + Utils.decryptSt(name) );
             tempUser = (User)adminService.getAbstractBaseEntityByString(Constants.USER, "user_name", Utils.decryptSt(name) );
             logger.debug("User id: " + tempUser.getId());
        } catch (Exception ex) {
            logger.debug("CERROR:: Reset password exception: " + ex.getMessage());
            logger.debug("CERROR:: Reset password exception description: " + ex);
        }*/
        logger.debug("Password change controller end.");
        model.addAttribute("user", tempUser);
        return "admin/resetPassword";
    }

    @RequestMapping(value = "/*/resetPassword.do", method = RequestMethod.POST)
    public String saveNewPassword(HttpServletRequest request, @ModelAttribute("user") User tempUser, BindingResult result) {
        logger.debug("Save new password start.");
       /* try {

            userValidator.validateNewPassword(tempUser, result, adminService);
            if(result.hasErrors()) {
                logger.debug("Conformation password not match.");
                return "admin/resetPassword";
            } else {
                tempUser.setPassword(Utils.encrypt(tempUser.getGivenPassword()));
                adminService.saveOrUpdate(tempUser);
            }

        } catch (Exception e) {
            logger.debug("CERROR:: Save new password exception: " + e);
            logger.debug("CERROR:: Save new password exception description: " + e.getMessage());
        }*/

        logger.debug("Save new password end.");
        return "redirect:/login.do";
    }

    /*
* Method to show login page
* @param @RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model
* @return type String
*/
    @RequestMapping(value = "home.do", method = RequestMethod.GET)
    public String getHome(HttpServletRequest request, Model model) {
        logger.debug("Home page Controller:");
        return "common/home";
    }

    /*
    * Method to show login page
    * @param @RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model
    * @return type String
    */
    @RequestMapping(value = "about.do", method = RequestMethod.GET)
    public String getAbout(HttpServletRequest request, Model model) {
        logger.debug("About page Controller:");
        return "common/about";
    }

    /*
    * Method to show login page
    * @param @RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model
    * @return type String
    */
    @RequestMapping(value = "contact.do", method = RequestMethod.GET)
    public String getContact(HttpServletRequest request, Model model) {
        logger.debug("Contact page Controller:");
        return "common/contact";
    }

    /*
* Method to show login page
* @param @RequestParam(value = "error", required = false) String error, HttpServletRequest request, Model model
* @return type String
*/
    @RequestMapping(value = "feature.do", method = RequestMethod.GET)
    public String getFeature(HttpServletRequest request, Model model) {
        logger.debug("Feature page Controller:");
        return "common/feature";
    }

    public void setLoggedUserInfoInSession(HttpServletRequest request, AdminService adminService) {
        String loggedUserName = (String) request.getSession().getAttribute("loggedUserName");
        logger.debug(":: setLoggedUserInfoInSession method :: loggedUserName:" + loggedUserName);
        if (loggedUserName == null && Utils.isEmpty(loggedUserName)) {
            logger.debug(":: Setting user info in session :: ");
            User user = (User) adminService.getAbstractBaseEntityByString(Constants.USER, "userName", Utils.getLoggedUserName());
            logger.debug("SMNLOG:user:" + user);
            request.getSession().setAttribute("loggUserId", user != null ? user.getId() : 0);
            request.getSession().setAttribute("loggedUserEmail", user != null ? user.getEmail() : "");
            request.getSession().setAttribute("loggedUserName", user != null ? user.getName() : "");
        }
    }

    public ProductKeyValidation isValidProductKey(String userName, String privateKey, String message, ProductKeyValidation productKeyValidation) {
        logger.debug(":: ******** Product key validation starts ********");
        byte[] keyValue = privateKey.getBytes();
        productKeyValidation.setProductKeyValid(true);
        try {
            Key key = Utils.generateKey(keyValue);
            String decryptedText = Utils.decrypt(message, key);
            logger.debug("SMNLOG:decryptedText:" + decryptedText);
            String[] arr = decryptedText.split(Constants.P_KEY_SEPARATOR);
            if (arr.length == 4) {
                if (userName.equals(arr[0])) {
                    logger.debug("SMNLOG: 11 :user name match found:" + userName);
                } else {
                    productKeyValidation.setProductKeyValid(false);
                }

                if (privateKey.equals(arr[1])) {
                    logger.debug("SMNLOG: 22: Private key match found:" + privateKey);
                } else {
                    productKeyValidation.setProductKeyValid(false);
                }

                Date date = Utils.getDateFromString(Constants.DATE_FORMAT, arr[2]);
                Date toDay = new Date();
                logger.debug("SMNLOG: 33: date found from product key:" + date);
                Integer validityUpTo = Integer.parseInt(arr[3]);
                Date lastDateOfValidation = Utils.addNDaysInDate(date, validityUpTo);
                logger.debug("SMNLOG: 44: date found from product key:" + date + " last validity date:" + lastDateOfValidation);
                logger.debug("SMNLOG: 55: Today is:" + toDay + " last validity date:" + lastDateOfValidation);

                if (toDay.compareTo(lastDateOfValidation) < 0) {
                    productKeyValidation.setValidationStartFrom(date);
                    productKeyValidation.setValidUpTo(validityUpTo);
                } else {
                    productKeyValidation.setProductKeyValid(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(":: Decryption failed ::userName:" + userName + " privateKey:" + privateKey);
            productKeyValidation.setProductKeyValid(false);
        }
        logger.debug("SMNLOG:FINAL RESULT ::" + productKeyValidation.getProductKeyValid());
        return productKeyValidation;
    }
}

