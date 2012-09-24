$(document).ready(function(){
    $.ajaxSetup ({
        cache: false,
        async: false
    });
});

$(document).ajaxComplete(function() {
    
});

function initTinyMCE() {
    tinyMCE.init({
    mode : "textareas",
    theme : "advanced",
    plugins : "emotions,spellchecker,advhr,insertdatetime,preview",
    width: "100%",
    height: "800",



    theme_advanced_buttons1 : "bold,italic,underline,|,justifyleft,justifycenter,justifyright,fontselect,fontsizeselect,formatselect",
    theme_advanced_buttons2 : "cut,copy,paste,|,bullist,numlist,|,outdent,indent,|,undo,redo,|,link,unlink,anchor,image,|,code,preview,|,forecolor,backcolor",
    theme_advanced_buttons3 : "insertdate,inserttime,|,spellchecker,advhr,,removeformat,|,sub,sup,|,charmap",
    theme_advanced_toolbar_location : "top",
    theme_advanced_toolbar_align : "left",
    theme_advanced_statusbar_location : "bottom",
    theme_advanced_resizing : false

    });
}

function cvContent(cvType) {
    $('#cvContent').load('Index?page=' + cvType, function()
    {
        
        $("#cvContent").hide();

        window.setTimeout(function() {
            initTinyMCE();

            $.ajax({
              type: 'POST',
              url: 'http://localhost:8080/Serve/Index?page=load' + cvType,
              async: false,
              success:function(data){
//                  dataFromDB = data;
                  var ed = tinyMCE.get('content');
                  ed.setContent(data);
                  $("#cvContent").fadeIn();
              },
              error:function(request, status, error){
                  alert(request.responseText);
              }
            });
        }, 1500);
    });
}

function appendDetails() {
    var ed = tinyMCE.get('content');

    var store = ed.getContent();
    var userName = document.getElementById('Name').value;
    var dob = document.getElementById('DoB').value;
    var email = document.getElementById('Email').value;
    var number = document.getElementById('PhoneNumber').value;

    var profileDetails = '<p style="text-align: center;"><strong><span style="font-size: medium;">Name:</span></strong> <span style="font-size: medium;">'+userName+'</span></p>';
    profileDetails += '<p style="text-align: center;"><strong><span style="font-size: medium;">Date of Birth:</span></strong> <span style="font-size: medium;">'+dob+'</span></p>';
    profileDetails += '<p style="text-align: center;"><strong><span style="font-size: medium;">E-Mail Address:</span></strong> <span style="font-size: medium;">'+email+'</span></p>';
    profileDetails += '<p style="text-align: center;"><strong><span style="font-size: medium;">Phone Number:</span></strong> <span style="font-size: medium;">'+number+'</span></p>';


    ed.setProgressState(1);
    window.setTimeout(function() {
        ed.setProgressState(0);
        ed.setContent(profileDetails + store);

    }, 1000);
} // close AppendDetails

function ajaxLoad(cvType) {

    if (confirm("Are you sure you wish to Load? Any unsaved progress will be lost."))
    {
        var ed = tinyMCE.get('content');

        // Do you ajax call here, window.setTimeout fakes ajax call

        var dataFromDB = "";

        $.ajax({
          type: 'POST',
          url: 'http://localhost:8080/Serve/Index?page=' + cvType,
          success:function(data){
              dataFromDB = data;
          }
        });

        ed.setProgressState(1); // Show progress
        window.setTimeout(function() {
            ed.setProgressState(0); // Hide progress
            ed.setContent(dataFromDB);
        }, 1500);
    }
} // close AjaxLoad

function ajaxSave(cvType) {
    var ed = tinyMCE.get('content');

    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/Serve/Index?page=' + cvType,
      data: { saveText: ed.getContent()},
      success:function(data){
      }
    });

    ed.setProgressState(1); // Show progress
    window.setTimeout(function() {
        ed.setProgressState(0); // Hide progress
        alert("Progress Saved!");
    }, 1500);
} // close AjaxSave