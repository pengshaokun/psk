import tpl from './userDeviceDetail.html';
import moment from '../../../libs/moment.js';

function userDeviceDetail() {
    //初始化列表
    function initGrid(param) {

        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/user/device/page/list?userId='+param,
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
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'deviceName',
                    title: '箱包名称',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'productName',
                    title: '产品名称',
                    sortable: false,
                    align: 'center',
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
                    field: 'defaultFlag',
                    title: '默认箱包',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 120,
                    formatter:function (val) {
                        if(val==1){
                            return "是";
                        }else {
                            return "不是";
                        }
                    }
                },
                {
                    field: 'authType',
                    title: '授权箱包',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 120,
                    formatter:function (val) {
                        if(val==1){
                            return "是";
                        }else {
                            return "不是";
                        }
                    }
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
                    fixed: true,
                    width: 200,
                    formatter: function formatDates(value, row, index) {
                        if (!_.isEmpty(String(value))) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
            ]],
            onLoadSuccess: function () {
            },
            loadFilter(data){
                return data;
            }
        });
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
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
        $('#btnReturn').linkbutton({
            onClick: function () {
                router.go(-1);
            }
        });
    }

    return {
        tpl,
        init: function (param) {
            initGrid(param);
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default userDeviceDetail;

