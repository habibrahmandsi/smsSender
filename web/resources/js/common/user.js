/**
 * Created by habib on 1/27/15.
 */

$(document).ready(function() {
    $('#dp1-1').datepicker();
    $('#dp1-2').datepicker();
    /*$(".crossBtn").click(function(){
        $(this).closest("div").find("input").val("");
    })*/

    /* initial form validation declaration */
    console.log('initiliaze validation....')
    function userFormValidation() {
        $("#userForm").bootstrapValidator({

            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: "Not Empty"
                        }
                    }
                },
                userName: {
                    validators: {
                        notEmpty: {
                            message: "Not Empty"
                        }
                    }
                }/*,
                joiningDate: {
                    validators: {
                        notEmpty: {
                            message: "Not Empty"
                        }
                    }
                }*/,
                email: {
                    validators: {
                        emailAddress: {
                            message: 'The value is not a valid email address'
                        }
                    }
                },
                age: {
                    validators: {
                        notEmpty: {
                            message: "Not Empty"
                        },
                        numeric: {
                            message: "must be numeric"
                        }
                    }
                }

            }
        });
    }

    userFormValidation();
});



