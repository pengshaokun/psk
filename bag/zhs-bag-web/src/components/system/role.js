import tpl from './role.html';
import modal from './roleModal.html';

function role() {
    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/role/page/list',
            idField: 'roleId',
            pagination: true,
            pageSize: config.pageSize,
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
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
                    field: 'enableFlag',
                    title: '启用',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 38,
                    hidden: true,
                    formatter: function (val, row, index) {
                        switch (val)
                        {
                            case 0:
                                return '<span>否</span>';
                            case 1:
                                return '<span style="color: #00ee00">是</span>';
                        }
                    }
                },
                {
                    field: 'roleCode',
                    title: '角色编码',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {field: 'comment', title: '备注', sortable: false, align: 'left', halign: 'center', width: 200},
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 100,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
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
        });
    }

    //初始化表单
    function initForm() {
        $('#form1').form({
            onLoadSuccess: function (data) {
                var opts = $('#menu').tree('options');
                opts.url = config.rootPath + 'web/role/menu/tree?roleId=' + data.roleId;
                $('#menu').tree('reload');
                var _opts = $('#resource').tree('options');
                _opts.url = config.rootPath + 'web/role/resource/tree?roleId=' + data.roleId;
                $('#resource').tree('reload');
            }
        });
    }

    //初始化对话框
    function initDialog() {
        $('#dlg').dialog({
            // width: '666px',
            // height: '500px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '34%',
            height: '50%',
            draggable: "true",
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });
        $('#menu').tree({
            checkbox: true,
            method: 'get'
        });
        $('#resource').tree({
            checkbox: true,
            method: 'get'
        });
    }

    //打开对话框
    function openDialog(title) {
        $('#dlg').dialog('setTitle', title);
        $('#dlg').dialog('open');
    }

    //新增系统角色
    function add() {
        $('#roleId').val('');
        $('#form1').form('reset');
        var opts = $('#menu').tree('options');
        opts.url = config.rootPath + 'web/role/menu/tree';
        $('#menu').tree('reload');
        var _opts = $('#resource').tree('options');
        _opts.url = config.rootPath + 'web/role/resource/tree';
        $('#resource').tree('reload');
        openDialog('添加系统角色');
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

    //修改系统角色
    function edit(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/role/' + _row.roleId;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                openDialog('编辑系统角色');
            });
        }
    }

    //删除系统角色
    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/role/remove/' + _row.roleId;
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

    //保存
    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();
        if(!data.enableFlag){
            data.enableFlag = 0;
        }
        data.menus = [];
        data.resources = [];
        var menus = $('#menu').tree('getChecked', ['checked', 'indeterminate']);
        var resources = $('#resource').tree('getChecked');
        $.each(menus, function (i, v) {
            data.menus.push($.extend(v.attributes, {checked: v.checked ? 1 : 0}));
        })
        $.each(resources, function (i, v) {
            data.resources.push(v.attributes);
        })
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/role/save',
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
export default role;

