/**
 * Created by fuhaibo on 2017/5/15.
 */
import './themes/easyui.css';
import './themes/color.css';
import './themes/icon.css';
import './themes/common.css';
import $ from '../libs/jquery.vendor.js';
import '../libs/jquery.form.js';
import _ from '../libs/underscore-vendor.js';
import '../libs/jquery.easyui.min.js';
import '../libs/jquery.combotabs.js';
import '../libs/locale/easyui-lang-zh_CN.js';
import  '../libs/datagrid-detailview.js';
import  '../libs/qrcode.js';
import router from './router.js';
import './config.js';
import passwordTpl from './components/system/password.html';
import selfTpl from './components/system/self.html';
import moment from '../libs/moment.js';



$(document).ready(function () {
    $.ajaxSetup({
        timeout: 300000,
        dataType: 'json',
        contentType: 'application/json;charset=utf-8',
        complete: function (xhr, statusText) {
            if (xhr.status == 401 || xhr.status == 0) {
                window.location.href = '../login.html';
            }
            if (this.url.indexOf('save') > -1) {
                $('#btnSave').linkbutton('enable');
            }
        },
        beforeSend: function (xhr, settings) {
            if (this.url.indexOf('save') > -1) {
                $('#btnSave').linkbutton('disable');
            }
        },
        //请求失败遇到异常触发
        error: function (xhr, status, e) {
             if (xhr.status == 401 || xhr.status == 0) {
             window.location.href = "./login.html";
             }
        },
    });

    $.get(config.rootPath + 'web/user/login/info', function (result) {
        if (result.code != 1 || result.data==null) {
            window.location.href = "../login.html";
        }else {
            config.loginInfo = result.data;
            init();
        }
    });

    function init() {
        $('#root').show();
        $('#root').layout({
            border: false,
            fit: true
        });

        $('#headerSet').menubutton({menu: '#headerMenu'});
        $('#headerUserInfo').click(function () {
            $('<div id="headerDlg"></div>').dialog({
                title: '个人信息',
                // width: 800,
                // height: 510,
                width: "50%",
                height: "37%",
                content: selfTpl,
                iconCls: 'icon-save',
                modal: true,
                closed: true,
                buttons: [{
                    text: '保存',
                    handler: function () {
                        if (!$('#headerForm').form('validate')) return;
                        var data = $('#headerForm').formJson();
                        if(!_.isEmpty(data.birthDate)){
                            data.birthDate = moment(data.birthDate, 'YYYY-MM-DD').format('x');
                        }
                        data.adminFlag = config.loginInfo.adminFlag;
                        $.ajax({
                            type: 'POST',
                            url: config.rootPath + 'web/user/save',
                            data: JSON.stringify(data),
                            success: function (result) {
                                if (result.code == 1) {
                                    $('#headerDlg').dialog('destroy');
                                    // window.location.href = '../login.html';  // 注释这行 让地址栏不发生变化 从而不退出，不会回到登陆页面
                                    $.messager.alert('提示', result.msg, 'info');
                                } else {
                                    $.messager.alert('提示', result.msg, 'error');
                                }
                            }
                        });
                    }
                }, {
                    text: '取消',
                    handler: function () {
                        $('#headerDlg').dialog('destroy');
                    }
                }],
                onClose: function () {
                    $(this).dialog('destroy');
                }
            });
            //初始化页面
            $("#birthDate").datebox({editable: false});
            $('#headerForm').form('load', config.loginInfo);
            $('#selfIdentityCardNo').textbox({
                validType: {
                    length:[18,18],
                },
                rules: {
                    length: {
                        validator: function (value, range) {
                            var reg = /^[1-9]\d{5}([1-2]\d{3})((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9xX]$/;
                            return reg.test(value);

                            // var len = $.trim(value).length;
                            // return len == range[0];
                        },
                        // message: "请输入{0}位身份证号码"
                        message: "请输入正确的{0}位身份证号码"
                    }
                }
            });
            $('#selfMobileNumber').numberbox({
                validType: {
                    length: [11,11],
                    phone:null,
                    remote: config.rootPath + 'web/user/exist/mobile'
                },
                rules: {
                    length: {
                        validator: function (value, range) {
                            var len = $.trim(value).length;
                            return len == range[0];
                        }, message: "输入内容长度必须是{0}位"
                    },
                    phone:{
                        validator: function (value) {
                            var rslt = /^1[0-9]{10}$/.test(value);
                            return rslt;
                        },
                        message: '手机号不合法'
                    },
                    remote: {
                        validator: function (value, url) {
                            var rslt = true;
                            if (config.loginInfo.mobileNumber != value) {
                                $.ajax({
                                    url: url+"/"+value,
                                    async: false,
                                    cache: false,
                                    type: "GET",
                                    success: function (result) {
                                        rslt=result==false ;
                                    }
                                });
                            }
                            return rslt;
                        }, message: "该手机号已被使用！"
                    }
                }
            });
            $('#selfMobileNumber').numberbox('setValue',config.loginInfo.mobileNumber);
            $('#selfIdentityCardNo').textbox('setValue',config.loginInfo.identityCardNo);
            $('#headerDlg').dialog('open');
        });
        $('#headerChangePassword').click(function () {
            openDialog('密码修改', '26%', '29%', passwordTpl, config.rootPath + 'web/user/change/pwd', function () {
                initPassword();
            });
        });
        $('#headerSignOut').click(function () {
            $.get(config.rootPath + 'web/user/sign/out', function (result) {
                window.location.href = "../login.html";
            });
        });

        $('#nav-tree').tree({
            data: config.loginInfo.menus,
            animate: true,
            onClick: function (node) {
                if (_.isEmpty(node.attributes.url)) {
                    if (node.state == 'closed') {
                        $('#nav-tree').tree('expand', node.target);
                    }
                    else {
                        $('#nav-tree').tree('collapse', node.target);
                    }
                }
                else {
                    router.clear();
                    router.go(node.attributes.url, node);
                }
            }
        });
    }

    function initPassword() {
        // 对原密码进行校验
        $('#oldPassword').textbox({
            required: true,
            validType: 'password',
            rules: {
                password: {
                    validator: function (value) {
                        return /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/i.test(value);
                    },
                    message: "密码必须由6到18位数字和字母组合"
                },
            }
        });
        $('#headerConfirmPassword').textbox({
            // width: "60%",
            required: true,
            validType: 'compare',
            rules: {
                compare: {
                    validator: function (value) {
                        return value == $('#headerNewPassword').textbox('getValue');
                    },
                    message: "两次输入的密码不一致！"
                }
            }
        });
        $('#headerNewPassword').textbox({
            required: true,
            validType: 'password',
            rules: {
                password: {
                    validator: function (value) {
                        return /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/i.test(value);
                    },
                    message: "密码必须由6到18位数字和字母组合"
                },
            }
        });
    }

    function submit(url) {
        if (!$('#headerForm').form('validate')) return;
        var data = $('#headerForm').formJson();
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(data),
            success: function (result) {
                if (result.code == 1) {
                    $('#headerDlg').dialog('destroy');
                    window.location.href = '../login.html';
                    $.messager.alert('提示', result.msg, 'info');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }

    function openDialog(title, width, height, tpl, url, init) {
        $('<div id="headerDlg"></div>').dialog({
            title: title,
            width: width,
            height: height,
            content: tpl,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    submit(url);
                }
            }, {
                text: '取消',
                handler: function () {
                    $('#headerDlg').dialog('destroy');
                }
            }],
            onClose: function () {
                $(this).dialog('destroy');
            }
        });
        if ($.isFunction(init)) {
            init();
        }
        $('#headerDlg').dialog('open');
    }
});