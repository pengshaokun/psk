
import tpl from './appVersion.html';
import modal from './appVersionModal.html';
import moment from '../../../libs/moment.js';

function appVersion() {
    //系统类型
    var systemCategory = {
        android: '0',
        ios: '1'
    };
    var defaultCode = null;

    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/appVersion/list',
            pagination: false,
            idField: 'versionId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'systemCategory',
                    title: '系统类别',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 140,
                    formatter: function (value) {
                        switch (value + '') {
                            case systemCategory.android :
                                return '安卓';
                            case systemCategory.ios:
                                return 'ios'
                            default:
                                return '未识别'
                        }
                    }
                },
                {
                    field: 'versionNumber',
                    title: '版本号',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 160
                },
                {
                    field: 'versionCode',
                    title: '版本编码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 80
                },
                {
                    field: 'downloadUrl',
                    title: '下载地址',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    width: 260
                },
                {
                    field: 'updateTime',
                    title: '更新时间',
                    sortable: false,
                    align: 'left',
                    fixed: true,
                    halign: 'center',
                    width: 160,
                    formatter: function (value) {
                        if (value) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
                {
                    field: 'remark',
                    title: '备注',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    width: 250
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 160,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-edit">编辑</a>');
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
            }
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            // loadFilter(data){
            //     return data.data;
            // }
        });
    }

    function initForm() {
        $('#form1').form({
            onLoadSuccess: function (data) {
                defaultCode = data.versionCode;
            }
        });
    }

    //初始化对话框
    function initDialog() {
        $('#dlg').dialog({
            // width: '546px',
            // height: '530px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '32%',
            height: '59%',
            content: modal,
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });
    }

    function initCombo() {
        $('#systemCategory').combobox({
            valueField: 'value',
            textField: 'label',
            prompt: '请选择系统类别',
            editable: false,
            required: true,
            data: [
                {
                    label: '安卓',
                    value: systemCategory.android
                }, {
                    label: 'ios',
                    value: systemCategory.ios
                }],
            select: function (data) {
                $('#systemCategory').combobox("setValue", data.value);
            }
        });
        //验证版本编码
        $('#versionCode').numberbox({
            min: 1,
            required: true,
            prompt: '请输入版本编码',
            validType: {
                remote: config.rootPath + 'web/appVersion/check/code'
            },
            rules: {
                remote: {
                    validator: function (value, url) {
                        var rslt = true;
                        if (defaultCode != value) {
                            var defaultCategory = $('#systemCategory').combobox('getValue');
                            $.ajax({
                                url: url + "?systemCategory=" + defaultCategory + '&versionCode=' + value+'&customerType=0',
                                async: false,
                                cache: false,
                                type: "GET",
                                success: function (result) {
                                    rslt = result == false;
                                }
                            });
                        }
                        return rslt;
                    }, message: "新增版本编码不能低于当前最高版本编码！"
                }
            }
        });
    }

    //打开对话框
    function openDialog(title) {
        $('#dlg').dialog('setTitle', title);
        $('#dlg').dialog('open');
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


    //添加
    function add() {
        $('#form1').form('reset');
        $('#versionId').val('');
        $('#fileTr').show();
        $('#sendFlagTr').show();
        $('#downloadUrl').textbox('setValue', '');
        defaultCode = null;
        openDialog('版本添加');
    }


    //编辑
    function edit(row) {
        $('#form1').form('reset');
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/appVersion/' + _row.versionId;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                $('#fileTr').hide();
                $('#sendFlagTr').hide();
                openDialog('版本编辑');
            });
        }
    }

    //保存信息
    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/appVersion/save',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.code == 1) {
                    $('#dlg').dialog('close');
                    reload();
                    //  注释掉弹出款，让保存成功后的信息在右下角显示2秒然后消失
                    $.messager.show({
                        title: '保存状态',
                        height: '1px',
                        content: result.msg,
                        timeout: 2000
                    });
                    // $.messager.alert('提示', result.msg, 'info');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }


    //删除信息
    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/appVersion/remove/' + _row.versionId;
                    $.get(url, function (result) {
                        if (result.code == 1) {
                            //  注释掉弹出款，让保存成功后的信息在右下角显示2秒然后消失
                            $.messager.show({
                                title: '保存状态',
                                height: '1px',
                                content: result.msg,
                                timeout: 2000
                            });
                           // $.messager.alert('提示', result.msg, 'info');
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

    //上传文件
    function uploadFile() {
        var fileList = $("#form1")[0];
        var formData = new FormData(fileList);
        formData.append('file', formData.get('' + file + ''));
        $.ajax({
            url: config.rootPath + 'web/file/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returnInfo) {
                //上传成功后将控件内容清空，并显示上传成功信息
                if (returnInfo) {
                    if (returnInfo.code == 1) {
                        $('#downloadUrl').textbox('setValue', '');
                        $('#downloadUrl').textbox('setValue', returnInfo.data.filePath)
                    } else {
                        $('#downloadUrl').textbox('setValue', '');
                        $.messager.alert('提示', '安装包上传失败,请稍后重试！', 'error');
                    }
                }
            },
            error: function () {
                $('#downloadUrl').textbox('setValue', '');
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
        $('#btnSave').linkbutton({
            onClick: function () {
                save();
            }
        });
        $('#btnRemove').linkbutton({
            onClick: function () {
                remove();
            }
        });
        $('#btnCancel').linkbutton({
            onClick: function () {
                $('#dlg').dialog('close');
            }
        });
        $('#file').filebox({
            onChange: function () {
                uploadFile();
            }
        });
    }

    return {
        tpl,
        init: function () {
            initGrid();
            initDialog();
            initForm();
            handleEvent();
            initCombo();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default appVersion;

