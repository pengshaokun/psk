import tpl from './aboutUs.html';
import modal from './aboutUsModal.html';
function aboutUs() {
    function initGrid() {
        $('#grid').datagrid({
            fit:true,
            rownumbers:true,
            checkOnSelect:true,
            singleSelect:true,
            method: 'get',
            url: config.rootPath + 'web/about/list',
            pagination: false,
            //pageSize: config.pageSize,
            idField: 'aboutId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'companyName',
                    title: '公司名称',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'linkPhone',
                    title: '联系电话',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'websiteUrl',
                    title: '公司网站',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'androidDownload',
                    title: '安卓下载地址',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'iosDownload',
                    title: '苹果下载地址',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'androidVersion',
                    title: '安卓版本',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'iosVersion',
                    title: '苹果版本',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
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
            // width: '770px',
            // height: '360px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '58%',
            height: '39%',
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
        $('#aboutId').numberbox('setValue',0);
        openDialog('关于我们');
    }

    //查阅查看操作
    function edit(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var id = _row.aboutId;
            var url = config.rootPath + 'web/about/' + id;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
            });
            openDialog('关于我们');
        }
    }

    //保存
    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/about/save',
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
                    var url = config.rootPath + 'web/about/remove/' + _row.aboutId;
                    $.get(url, function (result) {
                        if (result.code == 1) {
                            // 注释删除弹出框，让删除成功后的信息在右下角显示2秒然后消失
                            $.messager.show({
                                title: '删除状态',
                                height: '1px',
                                content: result.msg,
                                timeout: 2000
                            });
                            //$.messager.alert('提示', result.msg, 'info');
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

export default aboutUs;