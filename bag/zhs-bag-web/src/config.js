const config = {
    loginInfo: {},
    appId: 'wx0d6a7fd188a93851',
    rootPath: 'http://' + location.host + '/api/',
    imagePath: 'http://192.168.1.101:9999/',
    pageSize: 20,
    qrcodeSuffix: '&zhs',//优惠券二维码后缀
    getImgHtml: function (Id, imgPath, delIcon, width, fileName) {
        var version = Date.parse(new Date());
        var html = '<div id="imgArea_' + Id + '" class="image" style="width: ' + width + ';margin-left: 5px;"  >'
            + '<img id="imgShow_' + Id + '" data-original="' + config.imagePath + imgPath + '?version=' + version + '"  src="' + config.imagePath + imgPath + '?version=' + version + '"  style="width: ' + width + ';" />'
            //+ '<div id="cover_' + Id + '" style="margin-top:-' + height + ';height: ' + height + ';background-color:#FFF;filter:alpha(Opacity=50);-moz-opacity:0.5;opacity: 0.5;display: none;">'
            + '</div></div>'
            + '<div id="btnShow_' + Id + '" data-name="' + fileName + '" data-path="' + imgPath + '" style="float:right;margin-left: -30px;height: 30px;width: 30px;display: none;">'
            + '<img src="' + delIcon + '" height="30" width="30"/>'
            + '</div>';
        return html;
    }
}
window.config = config;
export default config;