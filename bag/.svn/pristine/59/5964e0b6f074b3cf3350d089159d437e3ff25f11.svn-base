import tpl from './area.html';
import modal from './areaModal.html';

function area() {
    //初始化列表
    function initGrid() {
        $('#grid').treegrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/area/asyn/list',
            pagination: false,
            idField: 'areaId',
            treeField: 'shortName',
            toolbar: '#grid-toolbar',
            fitColumns: true,
            columns: [[
                {
                    field: 'shortName',
                    title: '简称',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 300
                },
                {
                    field: 'areaCode',
                    title: '编码',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'fullName',
                    title: '全称',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 300
                },
                {
                    field: 'sortNo',
                    title: '排序号',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 60
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
                    field: 'customFlag',
                    title: '自定义',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 60
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 160,hidden:true,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-id="' + row.menuId + '" class="handle-add">添加</a>');
                        html.push('<a href="javascript:void(0);" data-id="' + row.menuId + '" class="handle-edit">修改</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                edit(row);
            },
            onLoadSuccess: function () {
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
            }
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            // loadFilter(data){
            //     return data.data;
            // }
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
            width: '666px',
            height: '500px',
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
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

    //新增区划代码
    function add(row) {
        $('#form1').form('reset');
        //$('#form1').form('clear');
        var _row = getSelectedRow(row);
        var data = {areaId: ''};
        if (_row) {
            data.parentId = _row.areaId;
            data.parentLevel = _row.level;
            data.parentPath = _row.path;
        } else {
            data.parentId = 0;
            data.parentLevel = 0;
            data.parentPath = '';
        }
        $('#form1').form('load', data);
        openDialog('添加区划代码');
    }

    //修改区划代码
    function edit(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/area/' + _row.areaId;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                openDialog('编辑系统角色');
            });
        }
    }

    //删除区划代码
    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/area/remove/' + _row.areaId;
                    $.get(url, function (result) {
                        $.messager.alert('提示', '删除成功！', 'info');
                        reload();
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
        if (!$('#form1').form('validate')) return;
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/area/save',
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
            initGrid();
            initForm();
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}
export default area;

