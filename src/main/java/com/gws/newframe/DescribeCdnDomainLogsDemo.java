package com.gws.newframe;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.cdn.model.v20141111.DescribeCdnDomainLogsRequest;
import com.aliyuncs.cdn.model.v20141111.DescribeCdnDomainLogsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author:wangdong
 * @description: DescribeCdnDomainLogs 下载域名访问日志Demo
 */
public class DescribeCdnDomainLogsDemo {

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
        DescribeCdnDomainLogsRequest request = new DescribeCdnDomainLogsRequest();
        // 系统规定参数。取值：DescribeCdnDomainLogs
        request.setActionName("DescribeCdnDomainLogs");
        // 域名（只支持单个查询）。
        request.setDomainName("wdtest.licai.cn");
        //获取日志起始时间点。日期格式按照ISO8601表示法，并使用UTC时间。例如：2018-07-10T08:00:00Z。
        request.setStartTime("2018-07-10T08:00:00Z");
        //结束时间需大于起始时间。起止时间和结束时间，间隔不超过一年。起止时间和结束时间，间隔不超过一年。例如：2018-07-12T08:00:00Z。
        request.setEndTime("2018-07-12T08:00:00Z");
        //以下四项为非必填项
        //要获取日志的天。格式yyyy-MM-dd。LogDay与StartTime、EndTime指定其一即可。默认值：当天
        //request.setLogDay("2018-07-10");
        //取得第几页。取值范围：>1的任意整数。
        //request.setPageNumber(2L);
        //分页大小。最大值：1000取值范围：1~1000之前的任意整数。默认值：300
        //request.setPageSize(300L);
        // 发起请求并处理应答或异常
        DescribeCdnDomainLogsResponse response;
        try {
            response = client.getAcsResponse(request);
            System.out.println(response.getRequestId());
            System.out.println(response.getDomainLogModel());
            System.out.println(response.getPageNumber());
            System.out.println(response.getPageSize());
            System.out.println(response.getTotalCount());

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
