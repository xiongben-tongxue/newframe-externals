package com.gws.newframe;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.cdn.model.v20141111.DescribeDomainBpsDataRequest;
import com.aliyuncs.cdn.model.v20141111.DescribeDomainBpsDataResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author:wangdong
 * @description: DescribeDomainBpsData 查询域名的带宽监测数据Demo
 */
public class DescribeDomainBpsDataDemo {

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
        DescribeDomainBpsDataRequest request = new DescribeDomainBpsDataRequest();
        // 系统规定参数。取值：DescribeDomainBpsData
        request.setActionName("DescribeDomainBpsData");
        //若参数为空，默认返回所有加速域名合并后数据。可输入需要查询的加速域名。支持批量域名查询，多个域名用逗号（半角）分隔。
        request.setDomainName("wdtest.licai.cn");
        //非必填项。查询类型。传dynamic时，查询全站加速动态资源的实时带宽和静态资源的实时带宽。不传时查询静态资源的实时带宽。
        request.setDomainType("dynamic");
        //例如EndTime、Interval等更多查询项均为非必填项，请查考详细参考文档：https://help.aliyun.com/document_detail/27205.html?spm=a2c4g.11186623.2.35.TbQCwU
        // 发起请求并处理应答或异常
        DescribeDomainBpsDataResponse response;
        try {
            response = client.getAcsResponse(request);
            //正确的返回,根据需要可以获取到更多的值
            //E3F6FB5A-5740-4552-BB5F-0E40BE6B082A,300,wdtest.licai.cn,2018-07-11T07:15Z,2018-07-12T07:15Z
            System.out.println(response.getRequestId()+","+response.getDataInterval()+","+ response.getDomainName()+"," + response.getStartTime()+","+
                                response.getEndTime());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
