package com.gws.newframe;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.cdn.model.v20141111.DescribeCdnDomainLogsRequest;
import com.aliyuncs.cdn.model.v20141111.DescribeCdnDomainLogsResponse;
import com.aliyuncs.cdn.model.v20141111.DescribeDomainFlowDataRequest;
import com.aliyuncs.cdn.model.v20141111.DescribeDomainFlowDataResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author:wangdong
 * @description: DescribeDomainFlowData 查询流量监控数据Demo
 */
public class DescribeDomainFlowDataDemo {

    public static void main(String[] args) {

        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou", "LTAI8VNtGyEjfGCS", "sJ2idqTOL7Cz1leqNmd0CWokCzRVlv"
        );
        // regionId 国内的阿里云工单说填：cn-hangzhou
        /*DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou","access_key_id","access_key_secret"
        );*/
        DefaultAcsClient client = new DefaultAcsClient(profile);
        DescribeDomainFlowDataRequest request = new DescribeDomainFlowDataRequest();
        // 系统规定参数。取值：DescribeDomainFlowData
        request.setActionName("DescribeDomainFlowData");
        //以下均为非必填项
        // 域名（只支持单个查询）。
        request.setDomainName("wdtest.licai.cn");
        //查询类型。传dynamic时，查询全站加速动态资源的实时流量和静态资源的实时流量。不传时查询静态资源的实时流量。
        request.setDomainType("dynamic");
        //获取数据起始时间点。日期格式按照ISO8601表示法，并使用UTC时间。格式为：YYYY-MM-DDThh:mmZ。最小数据粒度为5分钟。不写默认读取过去24小时数据。
        request.setStartTime("2018-07-10T08:00Z");
        //结束时间需大于起始时间。获日期格式按照ISO8601表示法，并使用UTC时间。格式为：YYYY-MM-DDThh:mmZ。
        request.setEndTime("2018-07-13T08:00Z");
        //是否补零。true或者false
        request.setFixTimeGap("false");
        //查询数据的时间粒度。支持300, 3600, 14400, 28800和86400秒。不传和传的值不支持时，使用默认值300秒。
        request.setInterval("300");
        //运营商英文名，通过DescribeCdnRegionAndIsp接口获得，不传为所有运营商。
        //request.setIspNameEn("unicom");
        //区域英文名，通过DescribeCdnRegionAndIsp接口获得，不传为所有区域。
        //request.setLocationNameEn("beijing");
        //取值范围:on：默认值，每条记录的时间间隔会根据时间跨度做合并。off：返回5分钟粒度数据，最大时间跨度为31天。
        request.setTimeMerge("on");
        // 发起请求并处理应答或异常
        DescribeDomainFlowDataResponse response;
        try {
            response = client.getAcsResponse(request);
            System.out.println(response.getRequestId());
            System.out.println(response.getDataInterval());
            System.out.println(response.getDomainName());
            System.out.println(response.getEndTime());
            System.out.println(response.getFlowDataPerInterval());
            System.out.println(response.getStartTime());

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
