/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    console.log("ready");
    $("#submitButton").click(function(event) {
     console.log("submitButton");
        // Stop default form Submit.
        event.preventDefault();
 
        // Call Ajax Submit.
    
        ajaxSubmitForm();
        $('#cancelModalCenter').modal('hide');
    });
 
});
 
function ajaxSubmitForm() {
 
    // Get form
    var form = $('#fileUploadForm')[0];
 
    var data = new FormData(form);
 
 
    $("#submitButton").prop("disabled", true);
 
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "upload",
        data: data,
 
        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 10000000,
        success: function(data, textStatus, jqXHR) {
 
            $("#result").html(data);
            $("#avatar").attr("src",data);
            $("#filePath").val(data);
            $("#hAvatart").val(data);
            console.log("SUCCESS : ", data);
            $("#submitButton").prop("disabled", false);
            $('#fileUploadForm')[0].reset();
        },
        error: function(jqXHR, textStatus, errorThrown) {  
 
            $("#result").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#submitButton").prop("disabled", false);
 
        }
    });
 
}
