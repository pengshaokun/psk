import tpl from './userBagData.html';
import moment from '../../../libs/moment.js';
function userBagData() {
    var params;
    function bagDataStyle(val) {
        if (val == "10" || val == "11" || val == "12" || val == "13") {
            return 'background-color:red;color:white';
        }
    }

    function initGrid() {
        $('#grid').datagrid({
            fit:true,
            rownumbers:true,
            checkOnSelect:true,
            singleSelect:true,
            method: 'get',
            url: config.rootPath + 'web/bag/data/page/list?clientId='+params,
            pagination: true,
            pageSize: config.pageSize,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    fixed: true,
                    field: 'clientId',
                    title: '箱包ID',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 180
                },
                {
                    fixed: true,
                    field: 'deviceName',
                    title: '箱包名称',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 130
                },
                {
                    fixed: true,
                    field: 'productName',
                    title: '产品名称',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed: true,
                    field: 'ev',
                    title: '电池电量',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120,
                    styler: function (value, row, index) {
                        return bagDataStyle(row.evFlag);
                    }
                },
                {
                    fixed: true,
                    field: 'va',
                    title: '电池电压',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 120,
                    styler: function (value, row, index) {
                        return bagDataStyle(row.vaFlag);
                    }
                },
                {
                    fixed: true,
                    field: 'el',
                    title: '电池电流',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120,
                    styler: function (value, row, index) {
                        return bagDataStyle(row.elFlag);
                    }
                },
                {
                    fixed: true,
                    field: 'te',
                    title: '电池温度',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120,
                    styler: function (value, row, index) {
                        return bagDataStyle(row.teFlag);
                    }
                },

                {
                    fixed: true,
                    field: 'lon',
                    title: '经度',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed: true,
                    field: 'lat',
                    title: '纬度',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed: true,
                    field: 'createTime',
                    title: '上传时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 150,
                    formatter: function formatDates(value, row, index) {
                        if (!_.isEmpty(String(value))) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
            ]],
            onDblClickRow: function (index, row) {
            },
            onLoadSuccess: function () {
            },
            loadFilter(data){
                return data.data;
            }
        });
    }

    function initTime() {
        $('#txtStartTime').datebox({
            width: 120,
            prompt: '请选择开始时间'
        });
        $('#txtEndTime').datebox({
            width: 120,
            prompt: '请选择结束时间'
        });
    }

    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
    }

    function handleEvent() {
        $('#btnClear').linkbutton({
            onClick: function () {
                $('#searchForm').form('reset');
            }
        });
        $('#btnReturn').linkbutton({
            onClick: function () {
                router.go(-1);
            }
        });

        $('#btnSearch').linkbutton({
            onClick: function () {

                var queryParams = $('#searchForm').formJson(true);
                var startTime = queryParams.startTime;
                var endTime = queryParams.endTime;
                var _queryParams = {};
                var isExist = false;
                if (!_.isEmpty(startTime)) {
                    isExist = true;
                    startTime = moment(startTime).format('X') * 1000;
                }
                var _isExist = false;
                if (!_.isEmpty(endTime)) {
                    _isExist = true;
                    endTime = moment(endTime).format('X') * 1000;
                }
                if (isExist && _isExist) {
                    if (endTime <= startTime) {
                        $.messager.alert('提示', '开始时间不能大于结束时间！', 'warning');
                        return;
                    }
                }
                _queryParams.startTime = startTime;
                _queryParams.endTime = endTime;
                reload(_queryParams)
            }
        });
    }

    return{
        tpl,
        init:function (param) {
            params = param;
            initTime();
            initGrid(param);
            handleEvent();
        },
        onBeforeLoad:function(){
            return true;
        }
    }

}

export default userBagData;