/**
 * Created by habib on 1/27/15.
 */

$(document).ready(function() {
    var url = "./getUsers.do";
    var columns =  [

        {"sTitle": "Name", "mData": "name", "bSortable": true},
        {"sTitle": "User Name", "mData": "user_name", "bSortable": true},
        {"sTitle": "Password", "mData": "password", "bSortable": true},
        {"sTitle": "Father Name", "mData": "father_name", "bSortable": true},
        {"sTitle": "Mother Name", "mData": "mother_name", "bSortable": true},
        {"sTitle": "Sex", "mData": "sex", "bSortable": true},
        {"sTitle": "Age", "mData": "age", "bSortable": true},
        {"sTitle": "Date Of Birth", "mData": null, "bSortable": true, "render": function (data) {
            var date= new Date(data.date_of_birth);
            return  getDateForTableView(date)

        }
        },
        {"sTitle": "Max Discount %", "mData": "max_discount_percent", "bSortable": true},
        //{"sTitle": "National Id", "mData": "national_id_no", "bSortable": true},
        {"sTitle": "", "mData": null,"bSortable": false, "render": function (data) {

            /* Condition to print appropriate icon based on status*/
            var html = '<a class="editTxt" href="./upsertUser.do?userId='+data.id+'"><img alt="Edit" title="Edit" src="'+contextPath+'/resources/images/edit.png">Edit</a>'
                +'&nbsp;<a class="deleteTxt" href="./deleteUser.do?userId='+data.id+'"><img alt="Delete" title="Delete" src="'+contextPath+'/resources/images/delete2.png">Delete</a>';

            return html;
        }
        }
    ];

    commonDataTableInit("#userList", url, columns);
    $(".dataTables_filter").find("input").focus();
});



