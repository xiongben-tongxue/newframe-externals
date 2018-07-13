package com.gws.newframe;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.cdn.model.v20141111.PushObjectCacheRequest;
import com.aliyuncs.cdn.model.v20141111.PushObjectCacheResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author:wangdong
 * @description: PushObjectCache 预热接口Demo
 */
public class PushObjectCacheDemo {

    public static void main(String[] args) {

        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou","LTAI8VNtGyEjfGCS","sJ2idqTOL7Cz1leqNmd0CWokCzRVlv"
        );
        // regionId 国内的阿里云工单说填：cn-hangzhou
        /*DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou","access_key_id","access_key_secret"
        );*/
        DefaultAcsClient client = new DefaultAcsClient(profile);
        PushObjectCacheRequest request = new PushObjectCacheRequest();
        // 系统规定参数。取值：PushObjectCache
        request.setActionName("PushObjectCache");
        //加速的文件位置wdtest.licai.cn为配置的域名
        request.setObjectPath("wdtest.licai.cn/helloworld.jpg");
        // 发起请求并处理应答或异常
        PushObjectCacheResponse response;
        try {
            response = client.getAcsResponse(request);
            //正确的返回
            //2703660566
            System.out.println(response.getPushTaskId());
            //866295AF-3A13-43F9-B654-1B254326D4F4
            System.out.println(response.getRequestId());

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
