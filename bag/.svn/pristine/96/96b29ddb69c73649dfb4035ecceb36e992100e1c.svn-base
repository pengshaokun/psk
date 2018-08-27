package com.zhskg.bag.util;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.zhskg.bag.enums.PushSendType;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static cn.jpush.api.push.model.notification.PlatformNotification.ALERT;

/**
 * 极光推送
 * Created by lwb on 2017/12/8.
 */
public final class PushUtil
{
    public static final String MASTER_SECRET="9d85667273b8e8bf74d3f7f7";
    public static final String APP_KEY="56a817dbb984ee56cbcccde5";

    public static boolean pushByAlias(String title,String msg,String alias)
    {
        try {
        System.out.println("mobilenumber:"+alias);
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload content = buildContent(title,msg,alias);
            PushResult anroidResult = jpushClient.sendPush(content);
            jpushClient.close();
            return anroidResult.isResultOK();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }


    public static boolean pushCarStatu(String msg,String alias,String val)
    {
        try {
            System.out.println("mobilenumber:"+alias);
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            PushPayload content = buildCar(msg,alias,val);
            PushResult anroidResult = jpushClient.sendPush(content);
            jpushClient.close();
            return anroidResult.isResultOK();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }


    private static PushPayload buildContent(String alert,String msg,String alias)
    {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .setMessage(Message.content(msg))
                .build();
    }

    private static PushPayload boastCast(String msg)
    {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .incrBadge(1)
                                .build())
                        .build())
                .setMessage(Message.content(msg))
                .build();//IosNotification.newBuilder()
    }

    private static PushPayload buildIos(String msg,String title,String alias)
    {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder().addPlatformNotification(IosNotification.newBuilder().setAlert(title).incrBadge(1).build()).build())
                .setMessage(Message.content(msg))
                .build();
    }
    public static PushPayload buildPushAndroid(String msg,String title,String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(title, msg, null))
                .build();
    }

    private static PushPayload buildCar(String msg,String alias,String val)
    {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
               // .setMessage(Message.content(msg))
                .setMessage(Message.newBuilder().setMsgContent(msg).addExtra("statu", val).build())
                .build();
    }

    /**
     *ios 通知模板
     * @param alert 通知内容
     * @param extras 操作map
     * @return
     */
    private static PushPayload buildNotificationIos(String alert,Map<String,String> extras)
    {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.ios(alert,extras))
                .build();
    }

    /**
     * android 通知模板
     * @param alert 通知内容
     * @param title 标题
     * @param extras 操作内容
     * @return
     */
    private static PushPayload buildNotificationAndroid(String alert,String title,Map<String,String> extras)
    {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(alert,title,extras))
                .build();
    }

    /**
     * 所有平台自定义消息发送模板
     *@param ident 标识
     *@param status 成功状态  true成功，false 不成功
     * @return
     */
    private  static  PushPayload customTerrace(String title,String msg,String ident,boolean status){
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(msg).addExtra(ident,status).setTitle(title).build())
                .build();
    }


    /**
     * IOS平台自定义消息发送模板
     *@param ident 标识
     *@param status 成功状态  true成功，false 不成功
     * @return
     */
    private  static  PushPayload customIosTerrace(String title,String msg,String ident,boolean status){
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(msg).addExtra(ident,status).setTitle(title).build())
                .build();
    }

    /**
     * android平台自定义消息发送模板
     *@param ident 标识
     *@param status 成功状态  true成功，false 不成功
     * @return
     */
    private  static  PushPayload customAndroidTerrace(String title,String msg,String ident,boolean status){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(msg).addExtra(ident,status).setTitle(title).build())
                .build();
    }



    /**
     * 个人自定义消息发送模板
     *@param ident 标识
     *@param status 成功状态  true成功，false 不成功
     * @return
     */
    private  static PushPayload customMes(String title,String msg,String alias,String ident,boolean status){
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder().setMsgContent(msg).addExtra(ident,status).setTitle(title).build())
                .build();
    }

    /**
     * 发送个人自定义消息
     * @param title
     * @param msg
     * @param alias
     *@param ident 标识
     *@param status 成功状态  true成功，false 不成功
     * @return
     */
    public static boolean pushCustomMesByAlias(String title,String msg,String alias,String ident,boolean status)
    {
        try {
            System.out.println("mobilenumber:"+alias);
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            PushPayload content = customMes(title,msg,alias,ident,status);
            PushResult result = jpushClient.sendPush(content);
            jpushClient.close();
            return result.isResultOK();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }


    /**
     * 发送平台自定义消息
     * @param title
     * @param msg
     * @param platform
     *@param ident 标识
     *@param status 成功状态  true成功，false 不成功
     * @return
     */
    public static boolean pushCustomMesByTerrace(String title, String msg, PushSendType platform, String ident, boolean status)
    {
        if(platform==null){
            return  false;
        }
        System.out.println("Terrace:"+platform);
        try {
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            PushPayload content;
            switch (platform){
                case android:
                    content=customAndroidTerrace(title,msg,ident,status);
                    break;
                case ios:
                    content=customIosTerrace(title,msg,ident,status);
                    break;
                    default:
                        content=customTerrace(title,msg,ident,status);
                        break;
            }
            if (content==null){
                return false;
            }
            PushResult result = jpushClient.sendPush(content);
            jpushClient.close();
            return result.isResultOK();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 安卓端通知
     * @param title 标题
     * @param alert 通知体
     * @param extras 携带体
     * @return
     */
    public static boolean pushByNotificationAndorid(String title, String alert,Map<String,String> extras)
    {
        try {
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            PushPayload contentAndroid=buildNotificationAndroid(alert,title,extras);
            PushResult result = jpushClient.sendPush(contentAndroid);
            jpushClient.close();
            return result.isResultOK();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * ios端通知
     * @param alert 通知体
     * @param extras 携带体
     * @return
     */
    public static boolean pushByNotificationIos( String alert,Map<String,String> extras)
    {
        try {
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            PushPayload contentIos=buildNotificationIos(alert,extras);
            PushResult result = jpushClient.sendPush(contentIos);
            jpushClient.close();
            return result.isResultOK();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }



    public static void main(String[] args)
    {
        Map<String,String> map=new HashMap<String, String>();
        map.put("url","www.baidu.com");
        System.out.println(pushByNotificationAndorid("蒲老板","蒲老板你在爪子",map));
    }
}
