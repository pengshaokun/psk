import tpl from './opinion.html';
import moment from '../../../libs/moment.js';
import modal from './opinionModal.html';
function opinion() {
    function initGrid() {
        $('#grid').datagrid({
            fit:true,
            rownumbers:true,
            checkOnSelect:true,
            singleSelect:true,
            method: 'get',
            url: config.rootPath + 'web/opinion/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'opinionId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'fullName',
                    title: '联系人',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'mobile',
                    title: '联系电话',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'createTime',
                    title: '反馈时间',
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
                    field: 'consultStatus',
                    title: '查阅状态',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 100,
                    formatter:function(val){
                        switch (val) {
                            case 0:
                                return '<span style="color: #3c8b3c">未查看</span>';
                            case 1:
                                return '<span style="color: #919191">已查看</span>';
                            default:
                                return '<span style="color: #3c8b3c">未查看</span>';
                        }
                    }
                },
                {
                    field: 'consultTime',
                    title: '查阅时间',
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
                    field: 'options',
                    title: '反馈意见',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    width: 100
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 120,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-view">查看</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                view(row);
            },
            onLoadSuccess: function () {
                $('.handle-view').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            view(row);
                        }
                    });
                });
            }
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            // loadFilter(data){
            //     return data.data;
            // }
        });
        $('#grid').datagrid("options").url = config.rootPath + 'web/opinion/page/list';

    }

    function consultStatus() {
        $('#consultStatus').combobox({
            width:'100px',
            valueField: 'value',
            textField: 'label',
            editable: false,
            onSelect:function (data) {
                var queryParams = {
                    consultStatus:data.value,
                };
                reload(queryParams);
            }
        });
    }

    function initDialog() {
        $('#dlg').dialog({
            // width: '766px',
            // height: '440px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '34%',
            height: '39%',
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
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

    //查阅查看操作
    function view(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var id = _row.opinionId;
            var url = config.rootPath + 'web/opinion/' + id;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                var consult ={
                    opinionId:id
                };
                $.ajax({
                    type: 'POST',
                    url: config.rootPath + 'web/opinion/consult',
                    data: JSON.stringify(consult),
                    success: function (result) {
                        if (result.code == 1) {
                            openDialog('反馈信息');
                        }else {
                            $.messager.alert('提示', result.msg, 'error');
                        }
                    }
                });
            });
            reload();
        }
    }

    function handleEvent() {
        $('#btnClose').linkbutton({
            onClick: function () {
                $('#dlg').dialog('close');
                reload();
            }
        });
    }

    return{
        tpl,
        init:function () {
            handleEvent();
            initGrid();
            consultStatus();
            initDialog();


        },
        onBeforeLoad:function(){
            return true;
        }
    }

}

export default opinion;