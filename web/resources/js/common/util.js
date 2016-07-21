/**
 * Created by habib on 1/27/15.
 */

var globalDateFormat = 'dd-mm-yyyy';
var okTxt = 'Ok';
var cancelTxt = 'Cancel';
var confirmationTitle = 'Confirmation';
var editTitle = 'Are you sure to edit ?';
var confirmTitle = 'Are you sure ?';
var deleteTitle = 'Are you sure to delete ?';
var rowDisplayGlobal = 20;
var globalRoundDigits = 2;

function confirmDialog(title,bodyTitle,successBtnTxt,cancelBtnTxt,fn){
    bootbox.dialog({
        message: bodyTitle,
        title: title,
        buttons: {
            success: {
                label: successBtnTxt,
                className: "btn-success",
                callback: function() {
                    console.log("great success");
                    fn.call(this);
                }
            },
            danger: {
                label: cancelBtnTxt,
                className: "btn-danger",
                callback: function() {
                    console.log("uh oh, look out!");
                }
            }
        }
    });
}

oCache = {
    iCacheLower: -1
};

function fnSetKey( aoData, sKey, mValue )
{
    for ( var i=0, iLen=aoData.length ; i<iLen ; i++ )
    {
        if ( aoData[i].name == sKey )
        {
            aoData[i].value = mValue;
        }
    }
}

function fnGetKey( aoData, sKey )
{
    for ( var i=0, iLen=aoData.length ; i<iLen ; i++ )
    {
        if ( aoData[i].name == sKey )
        {
            return aoData[i].value;
        }
    }
    return null;
}

function fnDataTablesPipeline ( sSource, aoData, fnCallback ) {
    var iPipe = 3; /* Ajust the pipe size */

    var bNeedServer = false;
    var sEcho = fnGetKey(aoData, "sEcho");
    var iRequestStart = fnGetKey(aoData, "iDisplayStart");
    var iRequestLength = fnGetKey(aoData, "iDisplayLength");
    var iRequestEnd = iRequestStart + iRequestLength;
    oCache.iDisplayStart = iRequestStart;

    /* outside pipeline? */
    if ( oCache.iCacheLower < 0 || iRequestStart < oCache.iCacheLower || iRequestEnd > oCache.iCacheUpper )
    {
        bNeedServer = true;
    }

    /* sorting etc changed? */
    if ( oCache.lastRequest && !bNeedServer )
    {
        for( var i=0, iLen=aoData.length ; i<iLen ; i++ )
        {
            if ( aoData[i].name != "iDisplayStart" && aoData[i].name != "iDisplayLength" && aoData[i].name != "sEcho" )
            {
                if ( aoData[i].value != oCache.lastRequest[i].value )
                {
                    bNeedServer = true;
                    break;
                }
            }
        }
    }

    /* Store the request for checking next time around */
    oCache.lastRequest = aoData.slice();

    if ( bNeedServer )
    {
        if ( iRequestStart < oCache.iCacheLower )
        {
            iRequestStart = iRequestStart - (iRequestLength*(iPipe-1));
            if ( iRequestStart < 0 )
            {
                iRequestStart = 0;
            }
        }

        oCache.iCacheLower = iRequestStart;
        oCache.iCacheUpper = iRequestStart + (iRequestLength * iPipe);
        oCache.iDisplayLength = fnGetKey( aoData, "iDisplayLength" );
        fnSetKey( aoData, "iDisplayStart", iRequestStart );
        fnSetKey( aoData, "iDisplayLength", iRequestLength*iPipe );

        $.getJSON( sSource, aoData, function (json) {
            /* Callback processing */

            oCache.lastJson = jQuery.extend(true, {}, json);

            if ( oCache.iCacheLower != oCache.iDisplayStart )
            {
                json.aaData.splice( 0, oCache.iDisplayStart-oCache.iCacheLower );
            }
            json.aaData.splice( oCache.iDisplayLength, json.aaData.length );

            fnCallback(json)
        } );
    }
    else
    {
        json = jQuery.extend(true, {}, oCache.lastJson);
        json.sEcho = sEcho; /* Update the echo for each response */
        json.aaData.splice( 0, iRequestStart-oCache.iCacheLower );
        json.aaData.splice( iRequestLength, json.aaData.length );
        fnCallback(json);
        return;
    }
}

function commonDataTableInit(tableIdOrCss,url,columns){

    oTable = $(tableIdOrCss).dataTable({
        "aLengthMenu": [[5,10,20,60], [5,10,20,60]],
        "iDisplayLength": rowDisplayGlobal,
        "bProcessing": true,
        "bServerSide": true,
        "bJQueryUI": true,
        "bAutoWidth": true,
        "sPaginationType": "simple_numbers", // you can also give here 'simple','simple_numbers','full','full_numbers'
        "oLanguage": {
            "sSearch": "Search:"
        },
        "sAjaxSource": url,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns":columns
    });
}

function makeAjaxUrlForTypeAhead(url, colIndex, txtToSearch,length){
    return url+"?iSortCOL="+colIndex+"&sSearch="+txtToSearch+"&iDisplayLength="+length;
}

function makeAutoComplete(inputIdOrClass,dataList, matchingColName, displayKey) {

    $(inputIdOrClass).typeahead({
            hint: false,
            highlight: true,
            limitTo:3,
            minLength: 1

        },
        {
            displayKey:  function(data){
                if("1" == displayKey){
                    //return data.productName+""+data.uomName+" "+data.typeName;
                    return data.productName;
                 }else if("2" == displayKey){
                    //return data.productName+""+data.uomName+" "+data.typeName;
                    return data.productName+" ( "+data.total_quantity+" ) ";
                 }else{
                    return data[displayKey];
                 }
            },
            //source: substringMatcher(dataList)
            source:  function findMatches(q, cb) {
                var matches, substringRegex;

                // an array that will be populated with substring matches
                matches = [];

                // regex used to determine if a string contains the substring `q`
                substrRegex = new RegExp("^"+q, 'i');

                // iterate through the pool of strings and for any string that
                // contains the substring `q`, add it to the `matches` array
                $.each(dataList, function (i, str) {
                    if (substrRegex.test(str[matchingColName])) {
                        matches.push(str);
                    }
                });
                //console.log("SMNLOG:matches:"+matches);
                cb(matches);
            }
        });
}

/*
function calculateTotal(table, calculateFrom) {
    var total = 0;
    //$(".purchaseLineItemsDiv").find("table").find("tbody").find("tr").each(function(){
    $(table).each(function () {
        console.log("SMNLOG:row::"+$(this).find(calculateFrom).find("label").text());
        total += (+$(this).find(calculateFrom).find("label").text());
        console.log("SMNLOG:" + total);
    });
    return total;

}*/
function getDateForTableView(date){
    var h = +date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    var amOrPm = "AM";

    if(h >= 0 && h<= 11)
        amOrPm = "AM";
    else
        amOrPm = "PM";

    if(h > 12){
        h = h-12;
    }
    return  date.getDate() + "/" +(date.getMonth()+1) + "/" + date.getFullYear()+ " "+h+":"+m+":"+s+" "+amOrPm
}

function getDateAsFormatted(date){
    return  date.getDate() + "/" +(date.getMonth()+1) + "/" + date.getFullYear()
}


function makeTabularAutoComplete(inputIdOrClass,url, callback){
    $(inputIdOrClass).typeahead({
        minLength: 2,
        maxItem: 20,
        order: "asc",
        dynamic: true,
        group: [true, '<table  class="table headerSuggestions table-striped"><thead><tr><td style="width:120px;">PRODUCT NAME</td><td style="width:30px;">MRP</td>'
        +'<td style="width:30px;">QTY.</td><td style="width:100px;">COMPANY</td></tr></thead><tbody>'],
        delay: 0,
        backdrop: {
            "background-color": "#fff"
        },
            template:  '<table  class="table table-striped"><tbody>'
            + '<tr id="{{productId}}">'
            + '<td style="width:120px">'
            + '{{productName}}'
            + '</td>'
            + '<td style="width:30px">'
                + '{{saleRate}}'
            + '</td>'
            + '<td style="width:30px">'
                + '{{totalQuantity}}'
            + '</td>'
             + '<td style="width:100px">'
                + '{{companyName}}'
            + '</td>'
            + '</tr>'
            + '</tbody>'
            + '</table>',
        source: {
            user: {
                display: "productName",
                data: [{
                    "productId": 1,
                    "productName": "test medicine",
                    "saleRate": "0.0",
                    "totalQuantity": "0",
                    "companyName": ""
                }],
                url: [{
                    type: "GET",
                    url: url,

                    data: {
                        sSearch: "{{query}}"
                    },
                    callback: {
                        done: function (data) {
                            for (var i = 0; i < data.data.productList.length; i++) {
                                if (data.data.productList[i].saleRate == 0) {
                                    data.data.productList[i].saleRate = '0';
                                    //console.log("SMNLOG:saleRate =0");
                                }
                                if (data.data.productList[i].totalQuantity == 0) {
                                    data.data.productList[i].totalQuantity = '0';
                                    //console.log("SMNLOG:totalQuantity =0");
                                }

                            }
                            return data;
                        }
                    }
                }, "data.productList"]
            }
        },
        callback: {
            onClick: function (node, a, item, event) {
               // You can do a simple window.location of the item.href
                if(typeof callback === 'function'){
                    callback(item);
                }

            },
            onClickAfter: function (node, a, item, event) {

              console.log("SMNLOG:end----------------");
                if( typeof item.totalQuantity != 'undefined' && item.totalQuantity <= 0 ){
                    alert("Quantity is empty.Please purchase first !");
                    event.preventDefault();
                    $(".productName").val("");
                    $(".productName").focus();
                }else{
                    $(".qty").focus();
                }
            },
            onSendRequest: function (node, query) {
                //console.log('request is sent, perhaps add a loading animation?')
            },
            onReceiveRequest: function (node, query) {
                //console.log('request is received, stop the loading animation?')
            }
        },
        debug: true
    });

/*    $(inputIdOrClass).typeahead({
        minLength: 1,
        order: "asc",
        group: [true, '<table  class="table table-striped"><thead><tr><td style="width:150px;">Product name</td><td style="width:60px;">Sale Rate</td>'
        +'<td style="width:60px;">Qty.</td></tr></thead><tbody>'],
        groupMaxItem: 6,
        hint: true,
        dynamic: true,
        delay: 1,
        template:  '<table  class="table table-striped"><tbody>'
        + '<tr id="{{productId}}">'
        + '<td style="width:150px">'
        + '{{productName}}'
        + '</td>'
        + '<td style="width:60px">'
        //+ '{{sale_rate}}'
        + '</td>'
        + '<td style="width:60px">'
        //+ '{{total_quantity}}'
        + '</td>'
        + '</tr>'
        + '</tbody>'
        + '</table>',
        source: {
            productList: {
                display: "username",
                href: "https://www.github.com/{{username}}",
                data: [{
                    "productId": 415849,
                    "productName": "an inserted user that is not inside the database",
                    "sale_rate": "https://avatars3.githubusercontent.com/u/415849",
                    "total_quantity":  " <small style='color: #777'>(contributor)</small>"
                }],
                url: [{
                    type: "GET",
                    url: url,

                    data: {
                        sSearch: "{{query}}"
                    },
                    callback: {
                        done: function (data) {
                            return data;
                        }
                    }
                }, "data.productList"]

            }
        },
         callback: {
                onClick: function (node, a, item, event) {

                    // You can do a simple window.location of the item.href
                    alert(JSON.stringify(item));

                },
                onSendRequest: function (node, query) {
                    console.log('request is sent, perhaps add a loading animation?')
                },
                onReceiveRequest: function (node, query) {
                    console.log('request is received, stop the loading animation?')
                }
            },
        debug: true
    });
    $(".typeahead-filter-button").remove();*/

}

function makeTabularAutoCompleteForPurchase(inputIdOrClass,url, callback){
    $(inputIdOrClass).typeahead({
        minLength: 2,
        maxItem: 20,
        order: "asc",
        dynamic: true,
        group: [true, '<table  class="table headerSuggestions table-striped"><thead><tr><td style="width:120px;">PRODUCT NAME</td><td style="width:30px;">MRP</td>'
        +'<td style="width:30px;">QTY.</td><td style="width:100px;">COMPANY</td></tr></thead><tbody>'],
        delay: 0,
        backdrop: {
            "background-color": "#fff"
        },
        template:  '<table  class="table table-striped"><tbody>'
        + '<tr id="{{productId}}">'
        + '<td style="width:120px">'
        + '{{productName}}'
        + '</td>'
        + '<td style="width:30px">'
        + '{{saleRate}}'
        + '</td>'
        + '<td style="width:30px">'
        + '{{totalQuantity}}'
        + '</td>'
        + '<td style="width:100px">'
        + '{{companyName}}'
        + '</td>'
        + '</tr>'
        + '</tbody>'
        + '</table>',
        source: {
            user: {
                display: "productName",
                data: [{
                    "productId": 1,
                    "productName": "test medicine",
                    "saleRate": "0.0",
                    "totalQuantity": "0",
                    "companyName": ""
                }],
                url: [{
                    type: "GET",
                    url: url,

                    data: {
                        sSearch: "{{query}}"
                    },
                    callback: {
                        done: function (data) {
                            for (var i = 0; i < data.data.productList.length; i++) {
                                if (data.data.productList[i].saleRate == 0) {
                                    data.data.productList[i].saleRate = '0';
                                    //console.log("SMNLOG:saleRate =0");
                                }
                                if (data.data.productList[i].totalQuantity == 0) {
                                    data.data.productList[i].totalQuantity = '0';
                                    //console.log("SMNLOG:totalQuantity =0");
                                }

                            }
                            return data;
                        }
                    }
                }, "data.productList"]
            }
        },
        callback: {
            onClick: function (node, a, item, event) {
                // You can do a simple window.location of the item.href
                if(typeof callback === 'function'){
                    callback(item);
                }

            },
            onClickAfter: function (node, a, item, event) {

                console.log("SMNLOG:end----------------");
                    event.preventDefault();
                    console.log("SMNLOG:******* item ****"+JSON.stringify(item));
                    $(".purchaseRateInput").val(+item.purchaseRate);
                    $(".saleRateInput").val(+item.saleRate);
                    $(".purchaseRateInput").focus();
            },
            onSendRequest: function (node, query) {
                //console.log('request is sent, perhaps add a loading animation?')
            },
            onReceiveRequest: function (node, query) {
                //console.log('request is received, stop the loading animation?')
            }
        },
        debug: true
    });

    /*    $(inputIdOrClass).typeahead({
     minLength: 1,
     order: "asc",
     group: [true, '<table  class="table table-striped"><thead><tr><td style="width:150px;">Product name</td><td style="width:60px;">Sale Rate</td>'
     +'<td style="width:60px;">Qty.</td></tr></thead><tbody>'],
     groupMaxItem: 6,
     hint: true,
     dynamic: true,
     delay: 1,
     template:  '<table  class="table table-striped"><tbody>'
     + '<tr id="{{productId}}">'
     + '<td style="width:150px">'
     + '{{productName}}'
     + '</td>'
     + '<td style="width:60px">'
     //+ '{{sale_rate}}'
     + '</td>'
     + '<td style="width:60px">'
     //+ '{{total_quantity}}'
     + '</td>'
     + '</tr>'
     + '</tbody>'
     + '</table>',
     source: {
     productList: {
     display: "username",
     href: "https://www.github.com/{{username}}",
     data: [{
     "productId": 415849,
     "productName": "an inserted user that is not inside the database",
     "sale_rate": "https://avatars3.githubusercontent.com/u/415849",
     "total_quantity":  " <small style='color: #777'>(contributor)</small>"
     }],
     url: [{
     type: "GET",
     url: url,

     data: {
     sSearch: "{{query}}"
     },
     callback: {
     done: function (data) {
     return data;
     }
     }
     }, "data.productList"]

     }
     },
     callback: {
     onClick: function (node, a, item, event) {

     // You can do a simple window.location of the item.href
     alert(JSON.stringify(item));

     },
     onSendRequest: function (node, query) {
     console.log('request is sent, perhaps add a loading animation?')
     },
     onReceiveRequest: function (node, query) {
     console.log('request is received, stop the loading animation?')
     }
     },
     debug: true
     });
     $(".typeahead-filter-button").remove();*/

}
function getRoundNDigits(number, nDigits){
    return number.toFixed(nDigits);
}