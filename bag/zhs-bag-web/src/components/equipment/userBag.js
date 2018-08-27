import tpl from './userBag.html';
import moment from '../../../libs/moment.js';

function userDeviceDetail() {
    //初始化列表
    function initGrid() {

        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/user/device/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'clientId',
                    title: '设备ID',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'deviceName',
                    title: '箱包名称',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'productName',
                    title: '产品名称',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200,
                },
                {
                    field: 'productCode',
                    title: '产品编码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'stockId',
                    title: '库存编码',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'mac',
                    title: 'mac地址',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 200,
                },
                {
                    field: 'createTime',
                    title: '添加时间',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    width: 200,
                    formatter: function formatDates(value, row, index) {
                        if (!_.isEmpty(String(value))) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 160,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-edit">箱包数据</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                bagData(row);
            },
            onLoadSuccess: function (index, row) {
                $('.handle-edit').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            bagData(row);
                        }
                    });
                });
            },
            loadFilter(data){
                return data.data;
            }
        });
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
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

    function bagData(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            router.go('userBagData',_row.clientId);

        }
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
    }

    return {
        tpl,
        init: function () {
            initGrid();
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default userDeviceDetail;

