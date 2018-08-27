package com.zhskg.bag.util;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import java.util.concurrent.*;

/**
 * Created by lwb on 2018/5/29.
 */
public class SmsPusher
{
    private static ExecutorService fixedThreadPool;
    static {
        fixedThreadPool = Executors.newFixedThreadPool(5);
    }

    public interface CallBack
    {
        void onRequestComplete(AlibabaAliqinFcSmsNumSendResponse result);
    }

    public static boolean doSend(String appKey, String appSecret, String templateId, String params, String freeSign, String mobile){
        try
        {

            Future<Boolean> sb = fixedThreadPool.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    AlibabaAliqinFcSmsNumSendResponse result = doSendMsg(appKey, appSecret, templateId, params, freeSign, mobile);
                    if (result !=null){
                       return result.isSuccess();
                    }
                    return false;
                }
            });
            Boolean rs = sb.get(2, TimeUnit.SECONDS);
            return rs;
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            return false;
        }
    }

    private static AlibabaAliqinFcSmsNumSendResponse doSendMsg(String appKey, String appSecret, String templateId, String params, String freeSign, String mobile)
    {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", appKey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsFreeSignName(freeSign);
        req.setSmsParamString(params);
        req.setRecNum(mobile);
        req.setSmsType("normal");
        req.setSmsTemplateCode(templateId);
        try {
            return client.execute(req);
        } catch (Exception ex) {
            return null;
        }
    }
}
