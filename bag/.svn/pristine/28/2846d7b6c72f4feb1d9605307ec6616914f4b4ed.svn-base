import tpl from './index.html';
import modal from './modal.html';

function user() {

    var defaultRole = undefined;
    var defaultData={};
    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/user/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'accountId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'account',
                    title: '账号',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 100
                },
                {
                    field: 'name',
                    title: '姓名',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 100
                },
                {
                    field: 'mobileNumber',
                    title: '手机号码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 100
                },
                {
                    field: 'email',
                    title: '电子邮箱',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 180
                },
                {
                    field: 'identityCardNo',
                    title: '身份证号码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 180
                },
                {
                    field: 'adminFlag',
                    title: '管理员',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 80,
                    formatter: function (val, row, index) {
                        switch (val) {
                            case 0:
                                return '<span>否</span>';
                            case 1:
                                return '<span style="color: #00ee00">是</span>';
                        }
                    }
                },
                {
                    field: 'enableFlag',
                    title: '启用',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 60,
                    hidden: true,
                    formatter: function (val, row, index) {
                        switch (val) {
                            case 0:
                                return '<span>否</span>';
                            case 1:
                                return '<span style="color: #00ee00">是</span>';
                        }
                    }
                },
                {field: 'comment', title: '备注', sortable: false, align: 'left', halign: 'center', width: 200},
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 160,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-reset">重置密码</a>');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-edit">修改</a>');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-del">删除</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                edit(row);
            },
            onLoadSuccess: function () {
                $('.handle-reset').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            resetPassword(row);
                        }
                    });
                });
                $('.handle-edit').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            edit(row);
                        }
                    });
                });
                $('.handle-del').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            remove(row);
                        }
                    });
                });
            },
            loadFilter(data){
                return data;
            }
        });


        $('#roles').datagrid({
            fit: true,
            rownumbers: true,
            selectOnCheck: false,
            checkOnSelect: false,
            singleSelect: true,
            method: 'get',
            idField: 'roleId',
            pagination: false,
            fitColumns: true,
            columns: [[
                {
                    field: 'ck',
                    checkbox: true
                },
                {
                    field: 'roleName',
                    title: '角色名称',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'comment',
                    title: '备注',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    width: 200
                },
                {
                    field: 'defaultFlag',
                    title: '默认角色',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 60,
                    formatter: function (val, row, index) {
                        if (val == 1) {
                            defaultRole = row;
                            return '<input class="defaultFlag" data-index="' + index + '" type="checkbox" checked="checked" />';
                        }
                        else {
                            return '<input class="defaultFlag" data-index="' + index + '" type="checkbox" />';
                        }
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $.each(data.rows, function (i, r) {
                    if (r.checked) {
                        $('#roles').datagrid('checkRow', i);
                    }
                });
                $('.defaultFlag').each(function (index, domEle) {
                    $(domEle).click(function () {
                            if ($(this).is(':checked')) {
                                $('.defaultFlag').each(function (i, e) {
                                    $(e).prop({checked: false});
                                });
                                var index = $(this).attr('data-index');
                                var row = $('#roles').datagrid('getRows')[index];
                                var rows = $('#roles').datagrid('getChecked');
                                $.each(rows, function (i, v) {
                                    if (v.roleId == row.roleId) {
                                        $(domEle).prop({checked: true});
                                        defaultRole = $.extend({}, row, {defaultFlag: 1});
                                    }
                                });
                            }
                        }
                    );
                });
            },
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            loadFilter(data) {
                return data.rows;
            }
        });
        var opts = $('#roles').datagrid('options');
        opts.url = config.rootPath + 'web/role/list/account';
    }

    //初始化表单
    function initForm() {
        $('#form1').form({
            onLoadSuccess: function (data) {
                $('#roles').datagrid('uncheckAll').datagrid('reload', {accountId: data.accountId});
            }
        });
    }

    //初始化对话框
    function initDialog() {
        // $("#dlg").css({"background":"yellow"});
        // $("#dlg").addClass("pop-up-box");
        $('#dlg').dialog({
            // width: '666px',
            // height: '608px',
            // resizable:true,
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '34%',
            height: '68%',
            content: modal,
            draggable: "true",
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            resizable: true,
            buttons: '#dlg-buttons'
        });
        //  密码校验
        $('#password').textbox({
            validType: 'compare',
            rules: {
                compare: {
                    validator: function (value) {
                        return /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/i.test(value);
                    },
                    message: "密码必须由6到18位数字和字母组合"
                }
            }
        })
        //  确认密码校验
        $('#confirmPassword').textbox({
            required: true,
            validType: 'compare',
            rules: {
                compare: {
                    validator: function (value) {
                        return $('#password').val() === value;
                    },
                    message: "密码与确认密码不等"
                }
            }
        });
        //  手机号校验
        $('#mobileNumber').numberbox({
            required: true,
            prompt: '请输入手机号',
            validType: {
                length: [11,11],
                remote: config.rootPath + 'web/user/exist/mobile'
            },
            rules: {
                length: {
                    validator: function (value, range) {
                        var len = $.trim(value).length;
                        return len == range[0];
                    }, message: "输入内容长度必须是{0}位"
                },
                remote: {
                    validator: function (value, url) {
                        var rslt = true;
                        if (defaultData.mobileNumber != value) {
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
        // 邮箱校验 ^
        $('#email').textbox({
            required: true,
            validType: 'email',
            rules: {
                email: {
                    validator: function (value) {
                        var reg = new RegExp("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
                        return reg.test(value);
                    },
                    message: "邮箱格式有误！"
                }
            }
        });
        //  身份证校验
        $('#identityCardNo').textbox({
            required: true,
            validType: 'IDCard',
            rules: {
                IDCard: {
                    validator: function (value) {
                        var reg = /^[1-9]\d{5}([1-2]\d{3})((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9xX]$/;
                        return reg.test(value);
                    },
                    message: "身份证号码有误！"
                }
            }
        });
    }

    //打开对话框
    // 在原来的方法function openDialog(title) 添加参数 addOreditor 用来判断是添加 还是编辑
    function openDialog(title, addOreditor) {
        $('#dlg').dialog('setTitle', title);
        if(addOreditor){
            $('#accountId').textbox('setValue', '');
            $('#roles').textbox('setValue', '');
            $('#form1').form('clear');
            $('#dlg').dialog('open');
        } else {
            $('#dlg').dialog('open');
        }
    }

    //获取已选择的行
    function getSelectedRow(row) {
        if (!row) row = $('#grid').datagrid('getSelected');
        if (row) {
            return row;
        }
        else {
            $.messager.alert('警告', '未选择记录!', 'warning');
            return null;
        }
    }

    //新增系统用户
    function add() {
        defaultData={};
        $('#form1').form('reset');
        // 去除 之前的 ID(accountId) 防止新增的用户会覆盖 刚编辑过的用户
        $('#accountId').textbox('setValue', '');
        $('#roles').textbox('setValue', '');
        $('#form1').form('clear');
        $('#accountId').val = null;

        $('#password').textbox('enable');
        $('#confirmPassword').textbox('enable');
        $('.password').show();
        defaultRole = undefined;
        $('#roles').datagrid('uncheckAll').datagrid('reload', {});
        openDialog('添加系统用户', true);
    }

    //修改系统用户
    function edit(row) {
        $('#form1').form('reset');
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/user/' + _row.accountId;
            $.get(url, function (result) {
                defaultData = result.data;
                $('#password').textbox('disable');
                $('#confirmPassword').textbox('disable');
                $('.password').hide();
                $('#form1').form('load', result.data);
                $('#fullName').textbox('setValue',result.data.name);
                openDialog('编辑系统角色', false);
            });
        }
    }

    function resetPassword(row) {
        $.get(config.rootPath + 'web/user/reset/pwd/' + row.accountId, function (result) {
            if (result.code == 1) {
                $.messager.alert('提示', '密码重置成功，重置后的密码为:' + result.data, 'info');
            }
            else {
                $.messager.alert('提示', '密码重置失败！', 'info');
            }
        });
    }

    //删除系统用户
    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/user/remove/' + _row.accountId;
                    $.get(url, function (result) {
                        if (result.code == 1) {
                            // $.messager.alert('提示', result.msg, 'info');
                            // 注释删除弹出框，让删除成功后的信息在右下角显示2秒然后消失
                            $.messager.show({
                                title: '删除状态',
                                height: '1px',
                                content: _row.name + '用户已删除成功',
                                timeout: 2000
                            });
                            reload();
                        } else {
                            $.messager.alert('提示', result.msg, 'error');
                        }
                    }).error(function (xhr, errorText, errorType) {
                        $.messager.alert('提示', error.message(xhr), 'error');
                    })
                }
            });
        }
    }

    //保存
    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson('json');
        var roles = $('#roles').datagrid('getChecked');
        var isExistDefault = false;
        if (!data.adminFlag&&roles!=null&&roles.length>0) {
            data.adminFlag = 0;
            if (!defaultRole) {
                $.messager.alert('提示', '请选择默认角色', 'warning');
                return;
            }
        }
        if (defaultRole){
            $.each(roles, function (i, v) {
                if (v.roleId == defaultRole.roleId) {
                    $.extend(v, {defaultFlag: 1});
                } else {
                    $.extend(v, {defaultFlag: 0});
                }
                if (v.defaultFlag == 1) {
                    isExistDefault = true;
                }
            });
        }
        if(!data.adminFlag){
            data.adminFlag=0;
            if (!isExistDefault && roles!=null&&roles.length>0) {
                $.messager.alert('提示', '未选择默认角色!', 'warning');
                $(".defaultFlag").prop("checked",false);
                return;
            }
        }
        data.roles = roles || [];
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/user/save',
            data: JSON.stringify(data),
            success: function (result) {
                // 添加手机号码校验
                var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
                if(!myreg.test(data.mobileNumber)){
                    $.messager.alert('提示', '请输入正确的手机号', 'warning');
                    return;
                }

                if (result.code == 1) {
                    $('#dlg').dialog('close', {
                        close:function(e, ui){
                            console.log("e: ");
                            console.log(e);
                            console.log("ui: ");
                            console.log(ui);
                        }
                    });
                    reload();
                    //  注释掉弹出款，让保存成功后的信息在右下角显示2秒然后消失
                    $.messager.show({
                        title: '保存状态',
                        height: '1px',
                        content: result.msg,
                        timeout: 2000
                    });
                    //$.messager.alert('提示', result.msg, 'info');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }


    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
    }

    //绑定事件
    function handleEvent() {
        $('#btnAdd').linkbutton({
            onClick: function () {
                add();
            }
        });
        $('#btnEdit').linkbutton({
            onClick: function () {
                edit();
            }
        });
        $('#btnRemove').linkbutton({
            onClick: function () {
                remove();
            }
        });
        $('#btnSave').linkbutton({
            onClick: function () {
                save();
            }
        });
        $('#btnCancel').linkbutton({
            onClick: function () {
                $('#dlg').dialog('close');
            }
        });

        $('#btnClear').linkbutton({
            onClick: function () {
                $('#searchForm').form('reset');
            }
        });
        $('#btnSearch').linkbutton({
            onClick: function () {
                var queryParams = $('#searchForm').formJson(true);
                reload(queryParams)
            }
        });
    }

    return {
        tpl,
        init: function () {
            initDialog();
            initGrid();
            initForm();
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default user;

