import tpl from './menu.html';
import modal from './menuModal.html';

function menu() {
    //初始化列表
    function initGrid() {
        $('#grid').treegrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/menu/asyn/list',
            pagination: false,
            idField: 'menuId',
            treeField: 'menuName',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'menuName',
                    title: '名称',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'menuNo',
                    title: '编号',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 100
                },
                {
                    field: 'caption',
                    title: '标题',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'sortNo',
                    title: '排序',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 60
                },
                {field: 'url', title: '路径', sortable: false, align: 'left', halign: 'center', fixed: true, width: 140},
                {field: 'comment', title: '备注', sortable: false, align: 'left', halign: 'center', width: 200},
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 160,
                    formatter: function (val, row) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-id="' + row.menuId + '" class="handle-add">添加</a>');
                        html.push('<a href="javascript:void(0);" data-id="' + row.menuId + '" class="handle-edit">修改</a>');
                        html.push('<a href="javascript:void(0);" data-id="' + row.menuId + '" class="handle-del">删除</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (row) {
                edit(row);
            },
            onLoadSuccess: function (row, data) {
                $('.handle-add').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var id = $(domEle).attr('data-id');
                            var row = $('#grid').treegrid('find', id);
                            add(row);
                        }
                    });
                });
                $('.handle-edit').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var id = $(domEle).attr('data-id');
                            var row = $('#grid').treegrid('find', id);
                            edit(row);
                        }
                    });
                });
                $('.handle-del').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var id = $(domEle).attr('data-id');
                            var row = $('#grid').treegrid('find', id);
                            remove(row);
                        }
                    });
                });
            },
             loadFilter(data){
                return data.rows;
             }
        });
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
            // width: '666px',
            // height: '500px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: "34%",
            height: "50%",
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });

        $('#menuName').textbox({
            required: true,
            validType: 'menuNo',
            rules: {
                menuNo: {
                    validator: function (value) {
                        var reg = /\w+/;
                        return reg.test(value);
                    },
                    message: "该输入项为必填项"
                }
            }
        });

        $('#sortNo').textbox({
            required: true,
            validType: 'sortNo',
            rules: {
                sortNo: {
                    validator: function (value) {
                        var reg = /\d+/;
                        return reg.test(value);
                    },
                    message: "该必填项只能为数字"
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
        if (!row) row = $('#grid').treegrid('getSelected');
        if (row) {
            return row;
        }
        else {
            $.messager.alert('警告', '未选择记录!', 'warning');
            return null;
        }
    }

    //新增系统菜单
    function add(row) {
        $('#form1').form('reset');
        var data = {menuId: ''};
        if (row) {
            data.parentId = row.menuId;
            data.parentLevel = row.level;
            data.parentPath = row.path;
        }
        else {
            data.parentId = 0;
            data.parentLevel = null;
            data.parentPath = '';
        }
        $('#form1').form('load', data);
        openDialog('添加系统菜单');
    }

    //修改系统菜单
    function edit(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/menu/' + _row.menuId;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                var parentId = $('#hidParentId').val();
                if(parentId == 0){
                    $('#hidParentPath').val('');
                    $('#hidParentLevel').val('');
                }
                openDialog('编辑系统角色');
            });
        }
    }

    //删除系统菜单
    function remove(row) {
        var _row = getSelectedRow(row);
        if(_row)
        {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/menu/remove/' + _row.menuId;
                    $.get(url, function (result) {
                        if (result.code == 1) {
                            $.messager.alert('提示', result.msg, 'info');
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
        var data = $('#form1').formJson();
        if(!$('#form1').form('validate')) return;
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/menu/save',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.code == 1) {
                    $('#dlg').dialog('close');
                    reload();
                    $.messager.alert('提示', result.msg, 'info');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').treegrid('reload', queryParams);
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
    }

    return {
        tpl,
        init: function () {
            initDialog();
            initForm();
            initGrid();
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}
export default menu;

