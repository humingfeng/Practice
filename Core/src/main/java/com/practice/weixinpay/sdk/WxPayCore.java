package com.practice.weixinpay.sdk;

import java.util.HashMap;
import java.util.Map;

import com.practice.dto.WexinPayInfoDTO;
import com.practice.utils.JsonUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



/**
 * show 微信支付工具类
 * @author Xushd
 * @since 2016年12月19日 下午5:21:55
 *
 */
@SuppressWarnings("deprecation")
public class WxPayCore {
	// 微信支付接口链接
	public static String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";	
	// appid
	public static String appid = "wx9c13e51e43a1b064";
	// 商户号
	public static String mch_id = "1499045902";
	// 通知地址(在此页面接收微信支付返回的信息，然后做下一步处理，例如修改订单状态、修改库存等)
	public static String notify_url = "http://practise.uping.wang/weixin/pay/result";
	// 交易类型
	public static String trade_type = "APP";	
	// key(微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置)
	public static String key = "56gP8fm44m8GgUIbJivoL0k7qD82fSpO";
	
	public static String getXml(String body,String nonce_str,String out_trade_no,String spbill_create_ip,String total_fee) {
		// 请求参数列表
		StringBuffer sb = new StringBuffer();
		// appid
		sb.append("appid=" + appid);
		// 商品描述
		sb.append("&body=" + body);
		// 商户号
		sb.append("&mch_id=" + mch_id);
		// 随机字符串
		sb.append("&nonce_str=" + nonce_str);
		// 通知地址
		sb.append("&notify_url=" + notify_url);
		// 商户订单号
		sb.append("&out_trade_no=" + out_trade_no);
		// 终端IP
		sb.append("&spbill_create_ip=" + spbill_create_ip);
		// 支付金额，分为单位
		sb.append("&total_fee=" + total_fee);
		// 交易类型
		sb.append("&trade_type=" + trade_type);
		// key
		sb.append("&key=" + key);
		// 生成sign
		String sign = MD5Encrypt.MD5Encode(sb.toString(), true);
        System.out.println(sign);
        // xml
		StringBuffer xml=new StringBuffer();
		xml.append("<xml>");
		xml.append("<appid>");
		xml.append(appid);
		xml.append("</appid>");
		xml.append("<body><![CDATA[");
		xml.append(body);
		xml.append("]]></body>");
		xml.append("<mch_id>");
		xml.append(mch_id);
		xml.append("</mch_id>");
		xml.append("<nonce_str>");
		xml.append(nonce_str);
		xml.append("</nonce_str>");
		xml.append("<notify_url>");
		xml.append(notify_url);
		xml.append("</notify_url>");
		xml.append("<out_trade_no>");
		xml.append(out_trade_no);
		xml.append("</out_trade_no>");
		xml.append("<spbill_create_ip>");
		xml.append(spbill_create_ip);
		xml.append("</spbill_create_ip>");
		xml.append("<total_fee>");
		xml.append(total_fee);
		xml.append("</total_fee>");
		xml.append("<trade_type>");
		xml.append(trade_type);
		xml.append("</trade_type>");
		xml.append("<sign>");
		xml.append(sign);
		xml.append("</sign>");
		xml.append("</xml>");


        return xml.toString();
	}
	
	public static String getPrepayId(String xml){
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);
		String prepay_id = "";
		try {
			httpost.setEntity(new StringEntity(xml, "UTF-8"));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpost);
			String jsonStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");			
			System.out.println("jsonStr:" + jsonStr);		
			Map<String, Object>  map = XmlUtil.doXMLParse(jsonStr);
			String return_code = (String) map.get("return_code");

			prepay_id = (String) map.get("prepay_id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prepay_id;
	}
	public static String getJson(String nonce_str,String prepay_id){
		String timestamp=Sha1Util.getTimeStamp();

        String s="appid="+appid+"&noncestr="+nonce_str+"&package=Sign=WXPay"+"&partnerid="+mch_id+"&prepayid="+prepay_id+"&timestamp="+timestamp;

        String newSign=MD5Encrypt.MD5Encode(s+"&key="+key,true);

        WexinPayInfoDTO wexinPayInfoDTO = new WexinPayInfoDTO();

        wexinPayInfoDTO.setApiKey(appid);

        wexinPayInfoDTO.setOrderId(prepay_id);

        wexinPayInfoDTO.setMchId(mch_id);

        wexinPayInfoDTO.setNonceStr(nonce_str);

        wexinPayInfoDTO.setTimeStamp(timestamp);

        wexinPayInfoDTO.setPackageStr("Sign=WXPay");

        wexinPayInfoDTO.setSign(newSign);

		return JsonUtils.objectToJson(wexinPayInfoDTO);
	}
	
}

