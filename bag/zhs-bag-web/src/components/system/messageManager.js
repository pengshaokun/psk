/**
 * Created by fuhaibo on 2018/7/3.
 */
import tpl from './messageManager.html';
import moment from '../../../libs/moment.js';

function messageManager() {
    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'web/message/query',
            pagination: true,
            pageSize: 10,
            pageNumber: 1,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'typeName',
                    title: '消息类型',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 180
                },
                {
                    field: 'title',
                    title: '消息标题',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 180
                },
                {
                    field: 'content',
                    title: '消息内容',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 180
                },
                {
                    field: 'createtime',
                    title: '消息创建时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 180,
                    formatter: function formatDates(value, row, index) {
                        if (!_.isEmpty(String(value))) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
                {
                    field: 'createName',
                    title: '消息创建人',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 250
                },
                {
                    field: 'phone',
                    title: '手机号码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 250
                }
            ]],
            onDblClickRow: function () {

            },
            onLoadSuccess: function (row, data) {
                console.log(row);
                console.log(data);
            }
        });
    }

    //初始化表单
    function initForm() {
        $('#form1').form({
            onLoadSuccess: function (data) {
                console.log("data===");
                console.log(data)
            }
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
                var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
                if(_.isEmpty(queryParams.phone)){
                    $.messager.alert('提示', '查询的手机号不能为空', 'warning');
                    return;
                }
                if (!myreg.test(queryParams.phone)) {
                    $.messager.alert('提示', '请输入正确的手机号', 'warning');
                    return;
                }
                reload(queryParams);
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
export default messageManager;

