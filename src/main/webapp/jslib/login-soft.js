var Login = function () {

	var handleLogin = function() {
		jQuery('.login-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	            	userName: {
	                    required: true
	                },
	                userPwd: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	            	userName: {
	                    required: "用户名不能为空."
	                },
	                userPwd: {
	                    required: "密码不能为空."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit 
	            	$('.errorTip', $('.login-form')).html("您的输入有误.");
	                $('.alert-danger', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.form-group').addClass('has-error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	            	toLogin();
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
//	                    $('.login-form').submit();
	                    toLogin();
	                }
	                return false;
	            }
	        });
	}

    
    return {
        //main function to initiate the module
        init: function () {
        	
            handleLogin();
        }

    };

}();

function toLogin(){
	var userName = $("#username").val();
	var userPwd = $("#password").val();
	$.ajax({
        type: "POST",
        cache: false,
        async:false,
        url: APPpath+"/dologin",
//         data: "userName="+userName+"&userPwd="+userPwd,
        data: $(".login-form").serialize(),
        async: false,
        success: function(res) 
        {
            if(res!="-1"){
            	location.href=APPpath+"/edition/index";
            }else{
            	$('.errorTip', $('.login-form')).html("账号名或密码不正确.");
            	$('.login-form').find('.alert-danger').show();
            }
        }
    });
	return false;
}