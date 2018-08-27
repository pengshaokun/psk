import tpl from './device.html';
import modal from './deviceModel.html';

function device() {
    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/stock/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'stockId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'clientId',
                    title: '设备编号',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'productId',
                    title: '产品ID',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },

                {
                    field: 'productName',
                    title: '设备名称',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'mac',
                    title: 'Mac地址',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'productSpecific',
                    title: '产品规格',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'produceTime',
                    title: '生产时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150,
                    hidden: true,
                },
                {
                    field: 'productPattern',
                    title: '产品型号',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'productBrand',
                    title: '产品品牌',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'productFactory',
                    title: '生产厂家',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'productCategory',
                    title: '产品类型',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'stockState',
                    title: '设备状态',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150,
                    formatter:function (val) {
                        if(val==1){
                            return "入库";
                        }else if(val==2){
                            return "出库";
                        }
                    }

                },
                {
                    field: 'handle',
                    title: '操作',
                    align: 'center',
                    fixed: true,
                    width: 130,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-qrCode">二维码查看</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                qrCodes(row);
            },
            onLoadSuccess: function () {
                $('.handle-qrCode').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            qrCodes(row);
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
            }
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            // loadFilter(data){
            //     return data.data;
            // }
        });
    }

    //初始化对话框
    function initDialog() {
        $('#dlg').dialog({
            width: '400px',
            height: '300px',
            content: modal,
            modal: true,
            closed: true,
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

    //生产二维码
    function qrCodes(row) {
        $('#qrcode').empty();
        var _row = getSelectedRow(row);
        if (_row) {
            $('#qrcode').qrcode({
                render: "table", //table方式
                width: 200, //宽度
                height: 200, //高度
                text: _row.clientId + '&' + _row.mac //任意内容
            });
            openDialog('设备' + _row.clientId + '的二维码')
        }
    }

    function edit(row) {
        $('#form2').form('clear');
        var _row = getSelectedRow(row);
        if (_row) {
            $.get(config.rootPath + 'init/car/parking/get/' + _row.clientId, function (result) {
                if (result.code == 1) {
                    $('#form2').form('load', result.data);
                }
            });
            $('#editDlg').dialog('open');
        }
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
    }

    function save() {
        var data = $('#form2').formJson();
        var _data = {};
        _data.config = data;
        _data.createId = data.createId;
        delete _data.config.createId;
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'init/car/parking/save',
            data: JSON.stringify(_data),
            success: function (result) {
                if (result.code == 1) {
                    $('#editDlg').dialog('close');
                    reload();
                    $.messager.alert('提示', '保存成功！', 'info');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }

    //绑定事件
    function handleEvent() {
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
        $('#btnSave').linkbutton({
            onClick: function () {
                save();
            }
        });
        $('#btnCancel').linkbutton({
            onClick: function () {
                $('#editDlg').dialog('close');
            }
        });
    }

    return {
        tpl,
        init: function () {
            initDialog();
            handleEvent();
            initGrid();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default device;

