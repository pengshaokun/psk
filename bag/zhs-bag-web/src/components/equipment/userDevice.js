import tpl from './userDevice.html';
function userDevice() {
    //初始化列表
    function initGrid() {

        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/user/platform/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'accountId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'fullName',
                    title: '姓名',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'nickName',
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
                    width: 100,
                    formatter:function (val) {
                        if(val==1){
                            return "女";
                        }else if(val==2){
                            return "男";
                        }else {
                            return "保密";
                        }
                    }
                },
                {
                    field: 'mobileNumber',
                    title: '手机号码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'email',
                    title: '电子邮箱',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'identityCardNo',
                    title: '身份证号码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 200
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 160,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-device">用户箱包</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                deviceDetail(row);
            },
            onLoadSuccess: function () {
                $('.handle-device').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            deviceDetail(row);
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

    //用户箱包
    function deviceDetail(row) {
        $('#form1').form('reset');
        var _row = getSelectedRow(row);
        if (_row) {
           router.go('userDeviceDetail',_row.userId);
        }
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

export default userDevice;

