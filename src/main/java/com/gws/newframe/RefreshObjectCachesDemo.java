package com.gws.newframe;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.cdn.model.v20141111.RefreshObjectCachesRequest;
import com.aliyuncs.cdn.model.v20141111.RefreshObjectCachesResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author:wangdong
 * @description: RefreshObjectCaches 刷新接口Demo
 */
public class RefreshObjectCachesDemo {

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
        RefreshObjectCachesRequest request = new RefreshObjectCachesRequest();
        // 系统规定参数。取值：RefreshObjectCaches
        request.setActionName("RefreshObjectCaches");
        //加速的文件位置wdtest.licai.cn为配置的域名,后加加速的文件名
        request.setObjectPath("wdtest.licai.cn/helloworld.jpg");
        // 发起请求并处理应答或异常
        RefreshObjectCachesResponse response;
        try {
            response = client.getAcsResponse(request);
            //正确的返回
            //2703743651
            System.out.println(response.getRefreshTaskId());
            //12B947D1-9990-4FB1-91AF-F86AC5EE636A
            System.out.println(response.getRequestId());

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
