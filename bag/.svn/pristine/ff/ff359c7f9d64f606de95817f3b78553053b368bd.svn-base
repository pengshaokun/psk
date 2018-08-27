
import tpl from './thirdPartyInfo.html';
import moment from '../../../libs/moment.js';

function thirdPartyInfo() {
    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/third/party/page/list',
            pagination: true,
            pageSize: 50,
            idField: 'openId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'iconUrl',
                    title: '头像',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 80,
                    formatter: function (value) {
                        if (!_.isEmpty(value)) {
                            return '<img style="width: 50px;height: 50px;" src="' + value + '">';
                        }
                    }
                },
                {
                    field: 'name',
                    title: '昵称',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'gender',
                    title: '性别',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 80
                },
                {
                    field: 'empowerType',
                    title: '授权类别',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 80,
                    formatter: function (value) {
                        if (value == 0) {
                            return 'QQ';
                        } else if (value == 1) {
                            return '微信';
                        } else if (value == 2) {
                            return '小程序';
                        }
                        return '';
                    }
                },
                {
                    field: 'openId',
                    title: '开放id',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 250
                },
                {
                    field: 'unionId',
                    title: '联合id',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 250
                },
                {
                    field: 'accessToken',
                    title: 'token',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    hidden: true,
                    width: 230
                },
                {
                    field: 'city',
                    title: '城市',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'prvinice',
                    title: '省份',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'country',
                    title: '国家',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'createTime',
                    title: '创建时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 140,
                    formatter: function (value) {
                        if (value) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
            },
            onLoadSuccess: function () {
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
        $('#txtStartTime').datebox({
            width: 120,
            prompt: '请选择开始时间'
        });
        $('#txtEndTime').datebox({
            width: 120,
            prompt: '请选择结束时间'
        });
        $('#txtEmpowerType').combobox({
            width: 120,
            prompt: '请选择授权类别',
            valueField: 'value',
            textField: 'text',
            value:'',
            data:[
                {
                    value:'',
                    text:'全部'
                },
                {
                    value:'0',
                    text:'QQ'
                },
                {
                    value:1,
                    text:'微信'
                }
            ]
        });
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
                _queryParams.name = queryParams.name;
                _queryParams.empowerType = queryParams.empowerType;
                reload(_queryParams);
            }
        });
        $('#btnClear').linkbutton({
            onClick: function () {
                $('#searchForm').form('clear')
            }
        });
    }

    return {
        tpl,
        init: function () {
            initGrid();
            initForm();
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}
export default thirdPartyInfo;

