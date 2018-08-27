import home from './components/home/home.js';
import menu from './components/system/menu.js';
import resource from './components/system/resource.js';
import role from './components/system/role.js';
import user from './components/system/index.js';
import file from './components/system/file.js';
import area from './components/system/area.js';
import fileTemp from './components/system/fileTemp.js';
import thirdPartyInfo from './components/platformHelp/thirdPartyInfo.js';
import userDevice from './components/equipment/userDevice.js';
import userDeviceDetail from './components/equipment/userDeviceDetail.js';
import device from './components/device/device.js';
import opinion from './components/platformHelp/opinion.js';
import lostInfo from './components/equipment/lostInfo.js';
import problem from './components/platformHelp/problem.js';
import appVersion from './components/platformHelp/appVersion.js';
import threshold from './components/equipment/threshold.js';
import deviceFault from './components/equipment/deviceFault.js';
import aboutUs from './components/platformHelp/aboutUs.js';
import serverInfo from './components/platformHelp/serverInfo.js';
import userBag from './components/equipment/userBag.js';
import userBagData from './components/equipment/userBagData.js';
import messageManager from './components/system/messageManager.js';
import systemInforms from './components/system/systemInforms.js';
import hardwareVersion from './components/hardware/hardwareVersion.js';

function router() {
    const components = {
        home: $.extend({}, {caption: '首页'}, home()),
        menu: $.extend({}, {caption: '菜单管理'}, menu()),
        resource: $.extend({}, {caption: '资源管理'}, resource()),
        role: $.extend({}, {caption: '角色管理'}, role()),
        user: $.extend({}, {caption: '用户管理'}, user()),
        area: $.extend({}, {caption: '行政区域管理'}, area()),
        fileTemp: $.extend({}, {caption: '文件清理'}, fileTemp()),
        file: $.extend({}, {caption: '文件上传'}, file()),
        thirdPartyInfo: $.extend({}, {caption: '第三方用户信息'}, thirdPartyInfo()),
        userDevice: $.extend({}, {caption: '箱包用户'}, userDevice()),
        userDeviceDetail: $.extend({}, {caption: '用户箱包'}, userDeviceDetail()),
        device: $.extend({}, {caption: '箱包设备'}, device()),
        opinion: $.extend({}, {caption: '意见反馈'}, opinion()),
        lostInfo: $.extend({}, {caption: '丢失信息'}, lostInfo()),
        problem: $.extend({}, {caption: '常见问题'}, problem()),
        appVersion: $.extend({}, {caption: '版本管理'}, appVersion()),
        threshold: $.extend({}, {caption: '参数设置'}, threshold()),
        deviceFault: $.extend({}, {caption: '故障申报'}, deviceFault()),
        aboutUs: $.extend({}, {caption: '关于我们'}, aboutUs()),
        serverInfo: $.extend({}, {caption: '服务器信息'}, serverInfo()),
        userBag: $.extend({}, {caption: '箱包列表'}, userBag()),
        userBagData: $.extend({}, {caption: '箱包数据'}, userBagData()),
        messageManager: $.extend({}, {caption: '消息管理'}, messageManager()),
        systemInforms: $.extend({}, {caption: '系统通知'}, systemInforms()),
        hardwareVersion: $.extend({}, {caption: '硬件版本管理'}, hardwareVersion()),

    };
    var paths = [];
    var header = $('#content-header');
    var current = {};

    function go(com, params, caption) {
        if (typeof com == "string") {
            com = components[com];
            push(com, params, caption);
        }
        else if (typeof com == "number" && com == -1) {
            paths.pop();
            var length = paths.length;
            if (length >= 1) {
                com = paths[length - 1];
                current = com;
            }
            else if (typeof params == "string") {
                com = components[params];
                current = $.extend({}, com, {params});
            }
        }
        else {
            com = com || {};
            push(com, params, caption);
        }
        if (current.onBeforeLoad(current.params)) {
            $('.window .window-body').window('destroy');
            $('#content-container').panel("clear");
            $('#content-container').html(current.tpl);
            $.parser.parse($('#content-container'));
            $('#content-container').hide();
            $('#content-container').fadeIn('slow');
            com.init(current.params);
            loadHeader();
        }
    }

    function push(com, params, caption) {
        if (caption) com.caption = caption;
        params = params || {};
        current = $.extend({}, com, {params});
        paths.push(current);
    }

    function loadHeader() {
        header.html('');
        var html = [];
        $.each(paths, function (i, n) {
            html.push('<a herlf="javascript:void();">' + n.caption + '</a>');
        });
        header.append(html.join('>'));
    }

    function clear() {
        paths = [];
    }

    function level() {
        return paths.length;
    }

    return {
        go,
        clear,
        level,
        current,
        components
    }
}

const _router = router();
window.router = _router;
export default _router;