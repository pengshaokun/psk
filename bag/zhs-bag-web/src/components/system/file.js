import tpl from './file.html';
import delIcon from '../../themes/images/remove.png';
function file() {

    var imgDataList = [];

    //初始化图片html
    function initImgHtml(Id, imgPath, delIcon) {
        var html = '<div id="imgArea_' + Id + '" style="width: 200px;height: 200px;float: left;margin:5px;"  >'
            + '<img id="imgShow_' + Id + '"  src="' + imgPath + '"  style="width: 200px;height: 200px;" />'
            + '<div id="cover_' + Id + '" style="margin-top:-200px;width: 200px;height: 200px;background-color:#FFF;filter:alpha(Opacity=50);-moz-opacity:0.5;opacity: 0.5;display: none;">'
            + '<div id="btnShow_' + Id + '" style="margin-top:-190px;margin-left:160px;height: 30px;width: 30px;display: none;">'
            + '<img src="' + delIcon + '" height="30" width="30"/>'
            + '</div></div></div>';
        return html;
    }


    //上传单一文件{imgType:0封面图片 1预览图跑 2宣传图片}
    function singleUploadFile(fileName, imgType) {
        var fileList = $("#form1")[0];
        var formData = new FormData(fileList);
        formData.append("file", formData.get('' + fileName + ''));
        $.ajax({
            url: config.rootPath + 'web/file/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returnInfo) {
                //上传成功后将控件内容清空，并显示上传成功信息
                if (returnInfo.code == 1) {
                    var imgInfo = {
                        img: returnInfo.data.filePath,
                        imgType: imgType
                    }
                    $.each(imgDataList,function (index,item) {
                        if(item.imgType == 0){
                            imgDataList.splice(index,1);
                        }
                    })
                    imgDataList.push(imgInfo);
                    singleBindFile(returnInfo.data.filePath);
                }
                $.messager.alert('提示', returnInfo.msg, 'info');
            },
            error: function (returnInfo) {
                $.messager.alert('提示', returnInfo, 'info');
            }
        });
    }

    //上传批量文件{imgType:0封面图片 1预览图跑 2宣传图片}
    function batchUploadFile(fileName, imgType) {
        var fileList = $("#form1")[0];
        var formData = new FormData(fileList);
        var _val = formData.getAll('' + fileName + '');
        var _formData = new FormData();
        for (var i = 0; i < _val.length; i++) {
            _formData.append('files', _val[i]);
        }
        $.ajax({
            url: config.rootPath + 'web/file/batch/upload',
            type: 'POST',
            data: _formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returnInfo) {
                //上传成功后将控件内容清空，并显示上传成功信息
                if (returnInfo.code == 1) {
                    var imgArr = returnInfo.data;
                    $.each(imgArr, function (index, item) {
                        var imgData = {
                            img: item.filePath,
                            imgType: imgType
                        }
                        imgDataList.push(imgData);
                    });
                    batchBindFile(imgArr,imgType);
                }
                $.messager.alert('提示', returnInfo.msg, 'info');
            },
            error: function (returnInfo) {
                $.messager.alert('提示', returnInfo, 'info');
            }
        });
    }

    //单一图片绑定
    function singleBindFile(filePath) {
        var version = Date.parse(new Date());
        var imgPath = config.imagePath + filePath + '?version=' + version;
        var id = filePath.split('/').join('').split('.').join('');
        var html = initImgHtml(id, imgPath, delIcon);
        $('#surfacePicture').children().remove();
        $('#surfacePicture').append(html);
        initImgEvent(id, imgPath);
    }

    //批量图片绑定
    function batchBindFile(imgArr,imgType) {
        $.each(imgArr, function (index, item) {
            var path = item.filePath;
            var version = Date.parse(new Date());
            var imgPath = config.imagePath + path + '?version=' + version;
            var id = path.split('/').join('').split('.').join('');
            var html = initImgHtml(id, imgPath, delIcon);
            switch (imgType) {
                case 1://预览
                    $('#previewPicture').append(html);
                    break;
                case 2://宣传
                    $('#publicityPicture').append(html);
                    break;
            }
            initImgEvent(id, path);
        });
    }

    function initFileBoxEvent() {
        //封面图片
        $('#txtSurfacePicture').filebox({
            onChange: function () {
                singleUploadFile('surfacePictureFile', 0);
            }
        });
        //预览图集
        $('#txtPreviewPicture').filebox({
            onChange: function () {
                batchUploadFile('previewPicture', 1);
            }
        });
        //宣传图集
        $('#txtPublicityPicture').filebox({
            onChange: function () {
                batchUploadFile('publicityPicture', 2);
            }
        });
    }

    function initImgEvent(id, imgPath) {
        $('#imgShow_' + id + '').mouseover(function () {
            $(this).css('cursor', 'hand');
            $('#cover_' + id + '').show();
            $('#btnShow_' + id + '').show();
        });
        $('#cover_' + id + '').mouseover(function () {
            $(this).css('cursor', 'hand');
            $('#btnShow_' + id + '').show();
        });
        $('#cover_' + id + '').mouseout(function () {
            $(this).hide();
            $('#btnShow_' + id + '').hide();
        });
        $('#imgShow_' + id + '').mouseout(function () {
            $('#btnShow_' + id + '').hide();
        });
        $('#btnShow_' + id + '').mouseover(function () {
            $(this).css('cursor', 'hand');
            $('#btnShow_' + id + '').show();
        });
        $('#btnShow_' + id + '').click(function () {
            removePath(imgPath);
            $('#imgArea_' + id + '').remove();
            $(this).hide();
        });
    }

    function removePath(imgPath) {
        //移除数组中删除的数据
        $.each(imgDataList, function (index, item) {
            if (imgPath == item.img) {
                imgDataList.splice(index, 1);
                return false;
            }
        });
    }



    return {
        tpl,
        init: function (params) {
            initFileBoxEvent();

        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default file;