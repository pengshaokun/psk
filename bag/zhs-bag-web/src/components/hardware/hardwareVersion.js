import tpl from './hardwareVersion.html';
import modal from './hardwareVersionModal.html';
import moment from '../../../libs/moment.js';

function hardwareVersion() {
    //硬件类型
    var type = {
        generalBag: '1',
        studentBag: '2'
    };
    var defaultCode = null;

    function initGrid() {

        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/hardware/version/list',
            pagination: false,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'type',
                    title: '硬件类型',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 140,
                    formatter: function (value) {
                        switch (value + '') {
                            case type.generalBag :
                                return '一般箱包';
                            case type.studentBag:
                                return '学生箱包'
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
                    width: 130
                },
                {
                    field: 'versionName',
                    title: '版本名称',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 130
                },
                {
                    field: 'describe',
                    title: '说明',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    width: 260
                },
                {
                    field: 'createTimeStr',
                    title: '创建时间',
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
                    field: 'updateTimeStr',
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
                    field: 'operator',
                    title: '操作人',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 130
                },
                {
                    field: 'url',
                    title: '存储路径',
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
                },
                {
                    field: 'deleteFlag',
                    title: '是否可用',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 130,
                    formatter: function (value) {
                        if (value == 1) {
                            return "否";
                        }
                        if (value == 0) {
                            return "是";
                        }
                        return '';
                    },

                }
            ]],

            // onDblClickRow: function (index, row) {
            //     edit(row);
            // },
            // onLoadSuccess: function () {
            //     $('.handle-edit').each(function (index, domEle) {
            //         $(domEle).linkbutton({
            //             onClick: function () {
            //                 var index = $(domEle).attr('data-index');
            //                 var row = $('#grid').datagrid('getRows')[index];
            //                 edit(row);
            //             }
            //         });
            //     });
            //     $('.handle-del').each(function (index, domEle) {
            //         $(domEle).linkbutton({
            //             onClick: function () {
            //                 var index = $(domEle).attr('data-index');
            //                 var row = $('#grid').datagrid('getRows')[index];
            //                 remove(row);
            //             }
            //         });
            //     });
            // }
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            // loadFilter(data){
            //     return data.data;
            // }
            onLoadSuccess: function (data) {
                // defaultCode = data.versionCode;
                var rows = $('#grid').datagrid('getRows')//获取当前页的数据行
                alert(rows);
                for (var i = 0; i < rows.length; i++) {
                    var name = rows[i]['deleteFlag']
                    if (name == 1) {
                        // rows[i].readonly(true);
                        // alert(rows[i]);
                    }

                }
            }
        });


    }

    function initForm() {
        $('#form1').form({
            onLoadSuccess: function (data) {
                // defaultCode = data.versionCode;
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
        $('#type').combobox({
            valueField: 'value',
            textField: 'label',
            prompt: '请选择硬件类别',
            editable: false,
            required: true,
            data: [
                {
                    label: '一般箱包',
                    value: type.generalBag
                }, {
                    label: '学生箱包',
                    value: type.studentBag
                }],
            select: function (data) {
                $('#type').combobox("setValue", data.value);
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
        $('#id').val('');
        $('#vName').show();
        $('#vNum').show();
        $('#vType').show();
        $('#vDescribed').show();
        $('#vFile').show();
        // $('#downloadUrl').textbox('setValue', '');
        // defaultCode = null;
        openDialog('版本添加');
    }

    //保存信息
    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/hardware/version/save',
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

    //编辑
    function edit(row) {
        $('#form1').form('reset');
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/hardware/version/' + _row.versionId;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                $('#vNum').hide();
                $('#vType').hide();
                $('#vDescribed').hide();
                openDialog('版本编辑');
            });
        }
    }


    //删除信息
    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/hardware/version/remove' + _row.versionId;
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

    }

    function toUpload() {
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
                        $.messager.alert('提示', '上传失败,请稍后重试！', 'error');
                    }
                }
            },
            error: function () {
                $('#url').textbox('setValue', '');
            }
        });
    }

    function check() {
        var type = $("#type").textbox('getText');     //获取文本框的值
        var number = $("#versionNumber").textbox('getText');
        $.ajax({
            url: config.rootPath + 'web/hardware/version/check/code' + "?type=" + type + "&versionNumber=" + number + "&deleteFlag=0",
            async: false,
            cache: false,
            type: "GET",
            success: function (result) {
                if (result.code==0) {
                    $.messager.alert('提示', "新增版本编码不能低于当前最高版本!", 'info');
                }

            }
        })
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
    }

    //
    // //绑定事件
    function handleEvent() {
        $('#btnAdd').linkbutton({
            onClick: function () {
                add();
            }
        });
        $('#file').filebox({
            onChange: function () {
                uploadFile();
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

        $('#versionNumber').textbox({
            inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
                blur: function (event) {
                    check();
                }
            })
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

// }
export default hardwareVersion;