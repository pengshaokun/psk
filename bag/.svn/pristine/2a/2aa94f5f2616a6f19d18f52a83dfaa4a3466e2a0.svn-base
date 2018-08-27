import './themes/easyui.css';
import './themes/color.css';
import './themes/icon.css';
import './themes/common.css';
import $ from '../libs/jquery.vendor.js';
import '../libs/jquery.form.js';
import '../libs/underscore-vendor.js';
import '../libs/jquery.easyui.min.js';
import '../libs/locale/easyui-lang-zh_CN.js';
import config from './config.js';

$(document).ready(function () {
    document.onkeydown = function (e) {
        var event = e || window.event;
        var code = event.keyCode || event.which || event.charCode;
        if (code == 13) {
            login();
        }
    }

    function clear() {
        $('#form1').form('clear');
    }

    function login() {
        if ($("input[name='userName']").val() == "" || $("input[name='passWord']").val() == "") {
            $("#showMsg").html("请输账号或密码！");
            $("input[name='userName']").focus();
        } else {
            var data = $('#form1').formJson();
            $.ajax({
                type: "post",
                url: config.rootPath + 'web/user/signIn',
                async:true,
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                beforeSend: function (request) {
                },
                error: function (request) {      // 设置表单提交出错
                    $("#showMsg").html(request);  //登录错误提示信息
                },
                success: function (rst) {
                    if (rst.code == 1) {
                        window.location.href = "../index.html";
                    } else {
                        $("#showMsg").html(rst.msg);
                    }
                }
            });
        }
    }


    $("#btnLogin").unbind('.toolbar').bind('click.toolbar', function () {
        login();
    });
    $("#btnClear").unbind('.toolbar').bind('click.toolbar', function () {
        clear();
    });

});
