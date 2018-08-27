
import tpl from './fileTemp.html';
import moment from '../../../libs/moment.js';

function fileTemp() {
    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/file/temp/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'fileId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'fileId',
                    title: '文件Id',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 200,
                    hidden: true
                },
                {
                    field: 'filePath',
                    title: '文件路径',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 450
                },
                {
                    field: 'fileType',
                    title: '文件类型',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 100
                },
                {
                    field: 'overdueFlag',
                    title: '过期标记',
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 80,
                    formatter: function (val, row, index) {
                        if (val == 1) {
                            return '是';
                        }
                        return '否';
                    }
                },
                {
                    field: 'createTime',
                    title: '创建时间123',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 250,
                    formatter: function formatDates(value, row, index) {
                        if (!_.isEmpty(String(value))) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
                {
                    field: 'termValidity',
                    title: '有效时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 250,
                    formatter: function formatDates(value, row, index) {
                        if (row.overdueFlag == 1) {
                            if (!_.isEmpty(String(value))) {
                                return value / (1000 * 60 *60 ) + '小时';
                            }
                        }else {
                            return '永久';
                        }

                    }
                },
                {
                    field: 'overdueTime',
                    title: '过期时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 250,
                    formatter: function formatDates(value, row, index) {
                        if (row.overdueFlag == 1) {
                            if (!_.isEmpty(String(row.createTime))) {
                                var _overdueTime=row.createTime+row.termValidity;
                                 return moment(_overdueTime).format('YYYY-MM-DD HH:mm:ss');
                            }
                        }else {
                            return '永久有效';
                        }

                    }
                }
            ]],
            onDblClickRow: function (row) {
            },
            onLoadSuccess: function (row, data) {

            }
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            // loadFilter(data){
            //     return data.data;
            // }
        });
    }

    function clearFile() {
        $.messager.confirm('提示', '你确定要清理所有过期文件吗？', function (r) {
            if (r) {
                $.get(config.rootPath + 'web/file/temp/clear/over/list', function (result) {
                    if (result.code == 1) {
                        $.messager.alert('提示', result.msg, 'info');
                        $('#grid').datagrid('reload');
                    } else {
                        $.messager.alert('提示', result.msg, 'warning');
                    }
                }).error(function (xhr, errorText, errorType) {
                    $.messager.alert('提示', error.message(xhr), 'error');
                });
            }
        });
    }

    function handleEvent() {
        $('#btnSearch').linkbutton({
            onClick: function () {
                var queryParams = $('#searchForm').formJson();console.log(queryParams);
                $('#grid').datagrid('reload', queryParams);
            }
        });
        $('#btnClear').linkbutton({
            onClick: function () {
                $('#searchForm').form('clear');
            }
        });
        $('#btnClearFile').linkbutton({
            onClick: function () {
                clearFile();
            }
        });
    }

    return {
        tpl,
        init: function (params) {
            initGrid();
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    }
}
export default fileTemp;