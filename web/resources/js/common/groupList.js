/**
 * Created by habib on 1/27/15.
 */

$(document).ready(function() {
    var url = "./getGroups.do";
    var columns = [

        {"sTitle": "Name", "mData": "name", "bSortable": true},
        {"sTitle": "Total Member", "mData": "totalMember", "bSortable": true},
        {"sTitle": "Members", "mData": null,"bSortable": false, "render": function (data) {
            var html = "<table style='font-size: 13px;'>", memberCodes, trData, tdData;
            if(data.member != null && data.member !== 'null')
                //trData = data.member.replace(/;/g, "</br>")
                trData = data.member.split(";");
            for(var i=0; i<trData.length; i++){
                tdData = trData[i].split(columnSeparator);
                html += '<tr><td style="text-align: left;">'+tdData[0]+'</td><td style="text-align: right;padding-left: 15px;">'+tdData[1]+'</td></tr>';
            }
            html +='<table>';
            return html;
        }
        },
        {"sTitle": "", "mData": null,"bSortable": false, "render": function (data) {

            /* Condition to print appropriate icon based on status*/
            var html = '<a class="editTxt" href="./upsertGroup.do?groupId='+data.id+'"><img alt="Edit" title="Edit" src="'+contextPath+'/resources/images/edit.png">Edit</a>'
                +'&nbsp;<a class="deleteTxt" href="./deleteGroup.do?groupId='+data.id+'"><img alt="Delete" title="Delete" src="'+contextPath+'/resources/images/delete2.png">Delete</a>';

            return html;
        }
        }
    ];

    commonDataTableInit("#groupList", url, columns);

    $(".dataTables_filter").find("input").focus();
});



