package com.xihu.conference.xihu.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.xihu.conference.xihu.properties.SMSProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * 短信发送工具类
 */

@Data
@AllArgsConstructor
public class SMSUtils {

	private String accessKey;
	private  String accessSecret;
 
	/**
	 * 发送短信
	 * @param signName 签名
	 * @param templateCode 模板
	 * @param phoneNumbers 手机号
	 * @param param 参数
	 */
	public  void sendMessage(String signName, String templateCode,String phoneNumbers,String param){

		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessSecret);
 
		IAcsClient client = new DefaultAcsClient(profile);
 
		SendSmsRequest request = new SendSmsRequest();
 
		request.setSysRegionId("cn-hangzhou");
//	    要发送给那个人的电话号码
		request.setPhoneNumbers(phoneNumbers);
//      我们在阿里云设置的签名
		request.setSignName(signName);
//	    我们在阿里云设置的模板
		request.setTemplateCode(templateCode);
//	    在设置模板的时候有一个占位符
		request.setTemplateParam("{\"code\":\""+param+"\"}");
 
//		request.setPhoneNumbers("1368846****");//接收短信的手机号码
//		request.setSignName("阿里云");//短信签名名称
//		request.setTemplateCode("SMS_20933****");//短信模板CODE
//		request.setTemplateParam("张三");//短信模板变量对应的实际值
 
		try {
			SendSmsResponse response = client.getAcsResponse(request);
		}catch (ClientException e) {
			e.printStackTrace();
		}
	}
 
}