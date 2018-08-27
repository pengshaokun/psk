import tpl from './serverInfo.html';
import modal from './serverInfoModal.html';
function serverInfo() {
    function initGrid() {
        $('#grid').datagrid({
            fit:true,
            rownumbers:true,
            checkOnSelect:true,
            singleSelect:true,
            method: 'get',
            url: config.rootPath + 'web/server/info/list',
            pagination: false,
            //pageSize: config.pageSize,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'domainName',
                    title: '域名',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150
                },
                {
                    field: 'ipAddress',
                    title: 'IP地址',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150
                },
                {
                    field: 'dataPort',
                    title: '数据端口',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150
                },
                {
                    field: 'heartPort',
                    title: '心跳端口',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150
                },
                {
                    field: 'heartRate',
                    title: '心跳频率',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150
                },
                {
                    field: 'softwareVersion',
                    title: '软件版本',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 120,
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

    function initDialog() {
        $('#dlg').dialog({
            // width: '540px',
            // height: '450px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '30%',
            height: '41%',
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
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

    function add() {
        $('#form1').form("reset");
        $('#id').numberbox('setValue',0);
        openDialog('服务器信息');
    }

    function edit(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var id = _row.id;
            var url = config.rootPath + 'web/server/info/' + id;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
            });
            openDialog('服务器信息');
        }
    }

    //保存
    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/server/info/save',
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

    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/server/info/remove?id=' + _row.id;
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

    return{
        tpl,
        init:function () {
            handleEvent();
            initGrid();
            initDialog();
        },
        onBeforeLoad:function(){
            return true;
        }
    }

}

export default serverInfo;