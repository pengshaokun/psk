import tpl from './threshold.html';
import moment from '../../../libs/moment.js';
import modal from './thresholdModal.html';
function threshold() {
    function initGrid() {
        $('#grid').datagrid({
            fit:true,
            rownumbers:true,
            checkOnSelect:true,
            singleSelect:true,
            method: 'get',
            // url: config.rootPath + 'web/threshold/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    fixed:true,
                    field: 'clientId',
                    title: '箱包ID',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 180
                },
                {
                    fixed:true,
                    field: 'batteryPower',
                    title: '电池电量',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'batteryVoltage',
                    title: '电池电压',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 120,
                },
                {
                    fixed:true,
                    field: 'batteryCurrent',
                    title: '电池电流',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'batteryTemperature',
                    title: '电池温度',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'safeDistance',
                    title: '防丢距离',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 130
                },
                {
                    fixed:true,
                    field: 'trackingDistance',
                    title: '跟踪距离',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'obstacleDistance',
                    title: '障碍距离',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'rayThreshold',
                    title: '光线阀值',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'speedThreshold',
                    title: '速度阀值',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'weightLimit',
                    title: '称重限量',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'chargeNum',
                    title: '充电次数',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 120
                },
                {
                    fixed:true,
                    field: 'modifyTime',
                    title: '修改时间',
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
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 120,fixed:true,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-edit">参数设置</a>');
                        html.push('</div>');
                        return html.join('');
                    }
                }
            ]],
            onDblClickRow: function (index, row) {
                edit(row);
            },
            onLoadSuccess: function () {
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
        $('#grid').datagrid("options").url = config.rootPath + 'web/threshold/page/list';

    }

    function setFlagCombo() {
        $('#setFlag').combobox({
            width:'150px',
            valueField: 'value',
            textField: 'label',
            editable: false,
            onSelect:function (data) {
                var queryParams = {
                    setFlag:data.value,
                };
                reload(queryParams);
            }
        });
    }

    function initDialog() {
        $('#dlg').dialog({
            // width: '780px',
            // height: '450px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '50%',
            height: '50%',
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

    function edit(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var id = _row.id;
            var url = config.rootPath + 'web/threshold/' + id;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
            });
            openDialog('参数设置');
            reload();
        }
    }

    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/threshold/save',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.code == 1) {
                    $('#dlg').dialog('close');
                    reload();
                    //  注释掉弹出款，让保存成功后的信息在右下角显示2秒然后消失
                    $.messager.show({
                        title: '保存状态',
                        height: '1px',
                        content: result.msg,
                        timeout: 2000
                    });
                    // $.messager.alert('提示', result.msg, 'info');
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }



    function handleEvent() {
        $('#btnEdit').linkbutton({
            onClick: function () {
                edit();
            }
        });

        $('#btnSave').linkbutton({
            onClick: function () {
                save();
            }
        });
        $('#btnCancel').linkbutton({
            onClick: function () {
                $('#dlg').dialog('close');
            }
        });

        $('#btnClear').linkbutton({
            onClick: function () {
                $('#searchForm').form('reset');
                $('#setFlag').combobox('setValue',2);
            }
        });
        $('#btnSearch').linkbutton({
            onClick: function () {
                var queryParams = $('#searchForm').formJson(true);
                queryParams.setFlag = $('#setFlag').combobox('getValue');
                reload(queryParams)
            }
        });
    }

    return{
        tpl,
        init:function () {
            handleEvent();
            initGrid();
            initDialog();
            setFlagCombo();
        },
        onBeforeLoad:function(){
            return true;
        }
    }

}

export default threshold;