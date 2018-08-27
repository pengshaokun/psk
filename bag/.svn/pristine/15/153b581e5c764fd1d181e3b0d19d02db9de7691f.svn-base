/**
 * Created by Administrator on 2018/7/4.
 */
import tpl from './systemInforms.html';
import modal from './systemInformsModal.html';
import modalS from './systemInformsSendModal.html'
import moment from '../../../libs/moment.js';

function systemInforms() {
    var defaultCode = null;

    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/messageSystem/query',
            idField: 'id',
            pagination: true,
            pageSize: config.pageSize,
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'title',
                    title: '标题',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 160
                },
                {
                    field: 'content',
                    title: '内容',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 160,
                },
                {
                    field: 'createtime',
                    title: '创建时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 260,
                    formatter: function formatDates(value, row, index) {
                        if (!_.isEmpty(String(value))) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 300,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-edit">编辑</a>');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-del">删除</a>');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-send">发送</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                edit(row);
            },
            onLoadSuccess: function () {
                // 每一条信息里的 编辑
                $('.handle-edit').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            edit(row);
                        }
                    });
                });
                // 每一条信息里的 删除
                $('.handle-del').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            remove(row);
                        }
                    });
                });
                // 每一条信息里的 发送
                $('.handle-send').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            send(row);
                        }
                    });
                });
            }
        });
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

    //新增区划代码
    function add(row) {
        $('#id').val('');
        $('#form1').form('reset');
        openDialog('添加系统通知', 1);
    }

    // 编辑系统通知
    function edit(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/messageSystem/' + _row.id;
            $.get(url, function (result) {
                // result.data.createtime = moment(result.data.createtime).format('YYYY-MM-DD HH:mm:ss');
                $('#form1').form('load', result.data);
                openDialog('编辑系统通知', 1);
            });
        }
    }
    // 调用发送通知
    function send(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/messageSystem/' + _row.id;
            $.get(url, function (result) {
                $('#form2').form('load', result.data);
                openDialog('系统通知发送', 2);
            });
        }
    }

    //  选择好系统点击发送  调用发送的接口 然后关闭发送的弹出框
    function sendfinish(){
        if (!$('#form2').form('validate')) return;
        var data = $('#form2').formJson();
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/messageSystem/send_jpush',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.code == 1) {
                    $('#dlgSend').dialog('close');
                    reload();
                    $.messager.alert('提示', result.msg, 'info');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }

    //  保存 更改信息后 点击弹出框的 保存 调用保存的接口
    function save(){
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();
        $.ajax({
            type: 'POST',
            url: data.id ? config.rootPath + 'web/messageSystem/update' : config.rootPath + 'web/messageSystem/add',
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

    //删除系统角色
    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/messageSystem/delete/' + _row.id;
                    $.get(url, function (result) {
                        if (result.code == 1) {
                            // 注释删除弹出框，让删除成功后的信息在右下角显示2秒然后消失
                            $.messager.show({
                                title: '删除状态',
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

    //初始化表单
    function initForm() {
        $('#form1').form({
            onLoadSuccess: function () {
            }
        });
    }

    //初始化对话框
    function initDialog() {
        $('#dlg').dialog({
            /* 宽高为百分比 配合css部分的响应式 */
            width: '34%',
            height: '30%',
            draggable: "true",
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });

        $('#dlgSend').dialog({
            /* 宽高为百分比 配合css部分的响应式 */
            width: '34%',
            height: '20%',
            draggable: "true",
            content: modalS,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlgSend-buttons'
        });
    }

    //打开对话框
    function openDialog(title, windowvalue) {
        if(windowvalue == 1){
            $('#dlg').dialog('setTitle', title);
            $('#dlg').dialog('open');
        }
        if(windowvalue == 2){
            $('#dlgSend').dialog('setTitle', title);
            $('#dlgSend').dialog('open');
        }

    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
    }

    //绑定事件
    function handleEvent() {
        $('#btnSearch').linkbutton({
            onClick: function () {
                var queryParams = $('#searchForm').formJson(true);
                reload(queryParams)
            }
        });

        $('#btnAdd').linkbutton({
            onClick: function() {
                add();
            }
        });

        $('#btnEdit').linkbutton({
            onClick: function() {
                edit();
            }
        });

        $('#btnRemove').linkbutton({
            onClick: function() {
                remove();
            }
        });
        //  更改信息后 保存
        $('#btnSave').linkbutton({
            onClick: function () {
                save()
            }
        });
        // 添加和编辑 弹出窗口的关闭
        $('#btnCancel').linkbutton({
            onClick: function () {
                $('#dlg').dialog('close');
            }
        });

        $('#btnSaveSend').linkbutton({
            onClick: function () {
                sendfinish();
            }
        });

        $('#btnCancelSend').linkbutton({
            onClick: function () {
                $('#dlgSend').dialog('close');
            }
        });
    }

    return {
        tpl,
        init: function () {
            handleEvent();
            initDialog();
            initGrid();
            initForm();

        },
        onBeforeLoad: function () {
            return true;
        }
    };
}
export default systemInforms;