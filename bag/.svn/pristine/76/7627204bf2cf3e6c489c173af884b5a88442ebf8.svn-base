/**
 * Created by Administrator on 2017-11-23.
 */
import tpl from './home.html';

function home() {
    //初始化页面
    function initForm() {
        $('#form1').form({
            onLoadSuccess: function () {
            }
        });
    }

    return {
        tpl,
        init: function () {
            initForm();
        },
        onBeforeLoad: function () {
            return true;
        }
    };
}

export default home;