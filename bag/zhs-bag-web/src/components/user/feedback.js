/**
 * Created by luochaojun on 2017/10/11.
 */
import tpl from './feedback.html';
import moment from '../../../libs/moment.js';
import modal from './feedbackModal.html';
function feedback() {
    var lookFlag = {
        WAITVIEWED:0,//待查阅
        VIEWED:1//已查阅
    }
    //反馈类别
    var categoryEnum = {
        FAULTREPAIR:0,//故障报修
        SYSTEMERROR:1,//系统错误
        OTHER:9//其它意见
    }
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
            url: config.rootPath + 'user/feedback/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'feedbackId',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'createName',
                    title: '反馈人',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 120
                },
                {
                    field: 'category',
                    title: '反馈类别',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 120,
                    formatter:function (value) {
                        switch(value){
                            case categoryEnum.FAULTREPAIR:
                                return '故障报修';
                            case categoryEnum.SYSTEMERROR:
                                return '系统错误';
                            case categoryEnum.OTHER:
                                return '其它';
                            default:
                                return '其它';
                        }
                    }
                },
                {
                    field: 'lookFlag',
                    title: '查阅状态',
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 80,
                    formatter: function (val) {
                        switch (val) {
                            case lookFlag.WAITVIEWED:
                                return '<span style="color: #3c8b3c">未查看</span>';
                            case lookFlag.VIEWED:
                                return '<span style="color: #919191">已查看</span>';
                            default:
                                return '<span style="color: #3c8b3c">未查看</span>';
                        }
                    }
                },
                {
                    field: 'createTime',
                    title: '提交时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 160,
                    formatter: function formatDates(value, row, index) {
                        if (!_.isEmpty(String(value))) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
                {
                    field: 'feedback',
                    title: '反馈内容',
                    align: 'left',
                    halign: 'center',
                    width: 200
                },
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 80,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-read">查看</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (row) {
                view(row);
            },
            onLoadSuccess: function (row, data) {
                $('.handle-read').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            view(row);
                        }
                    });
                });
            }
        });
    }

    function initLookFlag() {
        $('#lookFlag').combobox({
            width:'100px',
            valueField: 'value',
            textField: 'label',
            editable: false,
            onSelect:function (data) {
                var queryParams = {
                    lookFlag:data.value,
                };
                reload(queryParams);
            }
        });
    }

    function initDialog() {
        $('#dlg').dialog({
            width: '766px',
            height: '440px',
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });
        initCategory();
    }

    //初始化反馈类别
    function initCategory(){
        $('#txtCategory').combobox({
            valueField: 'value',
            textField: 'label',
            editable: false,
            data: [
                {
                    label: '故障报修',
                    value: categoryEnum.FAULTREPAIR
                }, {
                    label: '系统问题',
                    value: categoryEnum.SYSTEMERROR
                },{
                    label: '其它',
                    value: categoryEnum.OTHER
                }
            ]
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
            var id = _row.feedbackId;
            var url = config.rootPath + 'user/feedback/' + id;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                openDialog('反馈信息');
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

    return {
        tpl,
        init: function () {
            initGrid();
            initLookFlag();
            initDialog();
            handleEvent();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}
export default feedback;
