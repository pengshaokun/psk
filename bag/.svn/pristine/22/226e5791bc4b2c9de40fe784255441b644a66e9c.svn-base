import tpl from './deviceFault.html';
import modal from './deviceFaultModal.html';
import modalImg from './deviceFaultImgModal.html';
import moment from '../../../libs/moment.js';

function deviceFault() {
    //初始化列表
    function initGrid() {
        $('#grid').datagrid({
            fit: true,
            rownumbers: true,
            checkOnSelect: true,
            singleSelect: true,
            method: 'get',
           // url: config.rootPath + 'web/user/page/list',
            pagination: true,
            pageSize: config.pageSize,
            idField: 'id',
            fitColumns: true,
            toolbar: '#grid-toolbar',
            columns: [[
                {
                    field: 'clientId',
                    title: '箱包编码',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'productName',
                    title: '箱包名称',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'createTime',
                    title: '申报时间',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 180,
                    formatter: function (value) {
                        if (value) {
                            return moment(value).format('YYYY-MM-DD HH:mm:ss');
                        }
                        return '';
                    }
                },
                {
                    field: 'reason',
                    title: '申报原因',
                    sortable: false,
                    align: 'left',
                    halign: 'center',
                    width: 100
                },
                {
                    field: 'tel',
                    title: '联系人',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 150
                },
                {
                    field: 'status',
                    title: '处理状态',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 100,
                    formatter:function (val) {
                        if(val==1){
                            return '<span style="color: green">已处理</span>';
                        }else {
                            return '<span style="color: red">未处理</span>';
                        }
                    }
                },
                {
                    field: 'imgList',
                    title: '图片',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    fixed: true,
                    width: 300,
                    height: 50,
                    formatter:function (val) {
                        // console.log(val)
                        if(val.length > 0){
                            var imgArr = ``;
                            for(var i=0; i<val.length; i++){
                                imgArr +=  `<img src='http://www.jiutongtang.cn/${val[i]}' style='width:100px; height: 100px' />`;
                            }
                            return imgArr;
                         //   return `<img src='http://www.jiutongtang.cn/${val[0]}' style='width:100px; height: 100px' /><img src='http://www.jiutongtang.cn/${val[1]}' style='width:100px; height: 100px' /><img src='http://www.jiutongtang.cn/${val[2]}' style='width:100px; height: 100px' />`
                        }
                    }
                },
             /*   {
                    field: 'remark',
                    title: '备注',
                    sortable: false,
                    align: 'center',
                    halign: 'center',
                    width: 80,
                },
                */
                {
                    field: 'handle', title: '操作', align: 'center', fixed: true, width: 160,
                    formatter: function (val, row, index) {
                        var html = [];
                        html.push('<div class="grid-handlebutton">');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-edit">处理</a>');
                        html.push('<a href="javascript:void(0);" data-index="' + index + '" class="handle-del">删除</a>');
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
                $('.handle-del').each(function (index, domEle) {
                    $(domEle).linkbutton({
                        onClick: function () {
                            var index = $(domEle).attr('data-index');
                            var row = $('#grid').datagrid('getRows')[index];
                            remove(row);
                        }
                    });
                });

                $("img").click(function(){
                    var _index = $(this).index()+1;
                    var srcimg = $(this).attr("src");
                    openImgDialog(srcimg,_index)
                });

            }
            // loadFilter 方法会影响分页，现在将他注释。 注释后分页正常
            // loadFilter(data){
            //     return data.data;
            // }
        });
        $('#grid').datagrid("options").url = config.rootPath + 'web/device/fault/page/list';
    }

    //初始化表单
    function initForm() {
        $('#form1').form({
            onLoadSuccess: function (data) {
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
    }

    function status() {
        $('#status').combobox({
            width:'100px',
            valueField: 'value',
            textField: 'label',
            editable: false,
            onSelect:function (data) {
                var queryParams = {
                    status:data.value,
                };
                reload(queryParams);
            }
        });
    }

    //初始化对话框
    function initDialog() {
        $('#dlg').dialog({
            // width: '770px',
            // height: '608px',
            /* 去掉之前固定的宽高改为百分比 配合css部分的响应式 */
            width: '39%',
            height: '60%',
            content: modal,
            iconCls: 'icon-save',
            modal: true,
            closed: true,
            buttons: '#dlg-buttons'
        });
        //设置图片对话框
        $('#imgDlg').dialog({
            width: '23%',
            height: '55%',
            content: modalImg,
            modal: true,
            closed: true,
        });
    }

    // 打开图片对话框
    function openImgDialog(srcurl, index) {
        $('#imgDlg').dialog('setTitle', '图片' + index);
        $("#deviceImg").attr("src",srcurl);
        $('#imgDlg').dialog('open');
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


    //申报处理
    function edit(row) {
        $('#form1').form('reset');
        var _row = getSelectedRow(row);
        if (_row) {
            var url = config.rootPath + 'web/device/fault/' + _row.id;
            $.get(url, function (result) {
                $('#form1').form('load', result.data);
                openDialog('申报处理');
            });
        }
    }

    function remove(row) {
        var _row = getSelectedRow(row);
        if (_row) {
            $.messager.confirm('确认', '你确定要删除该记录吗？', function (r) {
                if (r) {
                    var url = config.rootPath + 'web/device/fault/remove/' + _row.id;
                    $.get(url, function (result) {
                        if (result.code == 1) {
                            $.messager.alert('提示', result.msg, 'info');
                            reload();
                        } else {
                            $.messager.alert('提示', result.msg, 'error');
                        }
                    }).error(function (xhr, errorText, errorType) {
                        $.messager.alert('提示', error.message(xhr), 'error');
                    })
                }
            });
        }
    }

    //保存
    function save() {
        if (!$('#form1').form('validate')) return;
        var data = $('#form1').formJson('json');
        // data.status = 1;
        data.createTime = moment(data.createTime).format('X') * 1000;
        $.ajax({
            type: 'POST',
            url: config.rootPath + 'web/device/fault/update',
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
                    // 注释删除弹出框，让删除成功后的信息在右下角显示2秒然后消失
                    $.messager.show({
                        title: '删除状态',
                        height: '1px',
                        content: _row.name + '用户已删除成功',
                        timeout: 2000
                    });
                    //$.messager.alert('提示', result.msg, 'error');
                }
            }
        });
    }

    //重新加载
    function reload(queryParams) {
        $('#grid').datagrid('reload', queryParams);
    }

    //绑定事件
    function handleEvent() {
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
                _queryParams.status = queryParams.status;
                reload(_queryParams);
            }
        });
    }

    return {
        tpl,
        init: function () {
            initDialog();
            initGrid();
            initForm();
            handleEvent();
            status();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default deviceFault;

