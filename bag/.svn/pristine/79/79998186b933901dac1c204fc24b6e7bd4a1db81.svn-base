import tpl from './lostInfo.html';
import moment from '../../../libs/moment.js';
import modal from './lostInfoModal.html';
function opinion() {
    function initGrid() {
        $('#grid').datagrid({
            fit:true,
            rownumbers:true,
            checkOnSelect:true,
            singleSelect:true,
            method: 'get',
            // url: config.rootPath + 'web/lost/info/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'fullName',
                    title: '丢失人',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'clientId',
                    title: '设备编码',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'lostTime',
                    title: '丢失时间',
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
                    field: 'lon',
                    title: '经度',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'lat',
                    title: '纬度',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'lostFlag',
                    title: '是否已找回',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 100,
                    formatter:function(val){
                        switch (val) {
                            case 1:
                                return '<span style="color: #FF0000">未找回</span>';
                            case 0:
                                return '<span style="color: #3c8b3c">已找回</span>';
                            default:
                                return '<span style="color: #FF0000">未找回</span>';
                        }
                    }
                },
                {
                    field: 'lostContent',
                    title: '丢失说明',
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
            onLoadSuccess: function (data) {
                $('.handle-view').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];

                            //添加百度地图
                            var map = new BMap.Map("mapbox");
                            map.centerAndZoom(new BMap.Point(row.lon, row.lat), '15');
                            map.addControl(new BMap.MapTypeControl());
                            map.addControl(new BMap.NavigationControl());
                            map.setCurrentCity("北京");
                            var marker = new BMap.Marker(new BMap.Point(row.lon, row.lat));
                            map.addOverlay(marker);
                            map.enableScrollWheelZoom(true);

                            view(row);
                        }
                    });
                });
            },
            loadFilter(data){
                return data;
            }
        });

        $('#grid').datagrid("options").url = config.rootPath + 'web/lost/info/page/list';
    }

    function lostFlag() {
        $('#lostFlag').combobox({
            width:'100px',
            valueField: 'value',
            textField: 'label',
            editable: false,
            onSelect:function (data) {
                var queryParams = {
                    lostFlag:data.value,
                };
                reload(queryParams);
            }
        });
    }

    //初始化对话框
    function initDialog() {
        $('#dlg').dialog({
            // width: '720px',
            // height: '480px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '54%',
            height: '85%',
            resizable: true,
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });
    }

    // 保存方法
    function save(){
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson();console.log(data);
        var val = {id: data.id, lostFlag:data.lostFlag};
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/lost/info/updateLostFlag' ,
            data: JSON.stringify(val),
            success: function (result) {
                if (result.code == 1) {
                    $('#dlg').dialog('close');
                    reload();
                    $.messager.show({
                        title: '保存状态',
                        height: '1px',
                        content: result.msg,
                        timeout: 2000
                    });
                } else {
                    $.messager.alert('提示', result.msg, 'error');
                }
            }
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

    function view(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            var id = _row.id;
            var url = config.rootPath + 'web/lost/info/' + id;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
            });
            openDialog('反馈信息');
        }
    }

    function handleEvent() {
        // 添加一个保存修改的按钮
        $('#btnSave').linkbutton({
            onClick: function(){
                save();
            }
        });

        $('#btnClose').linkbutton({
            onClick: function () {
                $('#dlg').dialog('close');
                reload();
            }
        });

        $('#btnClear').linkbutton({
            onClick: function () {
                $('#searchForm').form('reset');
            }
        });
        $('#btnSearch').linkbutton({
            onClick: function () {
                var queryParams = $('#searchForm').formJson(true);
                queryParams.lostFlag=$('#lostFlag').combobox('getValue');
                reload(queryParams)
            }
        });
    }

    return{
        tpl,
        init:function () {
            handleEvent();
            initGrid();
            lostFlag();
            initDialog();
        },
        onBeforeLoad:function(){
            return true;
        }
    }

}

export default opinion;