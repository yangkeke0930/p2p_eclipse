package com.xmg.p2p.base.service.impl;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.xmg.p2p.base.service.IVerifyCodeService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.DateUtil;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.base.vo.VerifyCodeVo;

/**
 * 手机发送验证码服务类的实现类
 * @author 78158
 *
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

	/**
	 * 注入相关的短信发送网关的参数
	 * {@value} 是注入值得方式
	 */
	@Value("${sms.username}")
	private String username;
	@Value("${sms.password}")
	private String password;
	@Value("${sms.apikey}")
	private String apikey;
	@Value("${sms.url}")
	private String url;
	
	/**
	 * 给特定手机号发送验证码的逻辑方法
	 */
	@Override
	public void sendVerifyCode(String phoneNumber) {
		//判断当前用户是否可以发送验证码
	    //从session中获取最后一次成功发送短信的时间
		VerifyCodeVo vc = UserContext.getCurrentVerifyCode();
		if(vc == null || DateUtil.secondsBetween(new Date(), vc.getLastSendTime()) > 90 ){
			/**
			 * 可以正常发送短信
			 */
			//生成一个 验证码，使用UUID。并且只取前四位
			String verifyCode = UUID.randomUUID().toString().substring(0,4);
		    
			/**
			 * 具体如和发送短信的代码
			 */
			
			try {
				//1、创建一个url  目的是为了的到httpurlconnection
				URL url = new URL(this.url);
				//2、得到想要的httpurlconnection对象
			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			    //3、拼接对应的消息内容
			    StringBuilder content = new StringBuilder(100)
			    		.append("username=").append(username)
			    		.append("&password=").append(password)
			    		.append("&apikey=").append(apikey)
			    		.append("&mobile=").append(phoneNumber)
			    		.append("&content=")
			    		.append("验证码是：").append(verifyCode).append("请在五分钟内使用！");
			   //4、内容拼接好了以后，就应该是提交内容了  注意：这里的提交方式不管是POST还是GET，都应该大写
			    conn.setRequestMethod("POST");
			   //5、设置request请求的请求提
			    conn.setDoOutput(true);   
			   //6、将request请求体写入request请求中
			   conn.getOutputStream().write(content.toString().getBytes());
			   //7、得到对应的响应流
			   String response = StreamUtils.copyToString(conn.getInputStream(),Charset.forName("UTF-8"));
			   //8、判断响应回来的数据内容是什么内容
			   if(response.startsWith("success")){	//以success内容开头的,则开始发送短息 
				   /**
					 * 将手机号 验证码的内容 和最后一次发送成功的时间都保存在session中
					 */
					//1、创建对象
					vc = new VerifyCodeVo();
					/*
					 * 给新创建的对象vc中添加内容(手机号  验证码 最后一次完成发送验证码的时间)
					 */
					vc.setPhoneNumber(phoneNumber);
					vc.setLastSendTime(new Date());
					vc.setVerifyCode(verifyCode);
					//将对象vc放置到session中
					UserContext.putVerifyCode(vc);  
			   }else{//发送短信失败 ，这个异常时发送短信时候的异常
				   throw new RuntimeException(); 
			   }
			} catch (Exception e) {
				e.printStackTrace();
				//抛出异常 这个异常是运行时候的异常
				throw new RuntimeException("发送短信失败");
			}				
		}else{
			throw new RuntimeException("您的操作过于频繁！");
		}
			
		
	}
	
	/**
	 * 用来验证手机验证码是否合法
	 */
	@Override
	public Boolean verify(String phoneNumber, String verifyCode) {
		/**
		 * 1、验证手机号是否正确
		 * 2、验证验证码是否正确
		 * 3、验证验证码是否在有效时间内五分钟之内
		 * 4、关于有效事件，已经在bindconst中定义300秒
		 */
		//创建verifyCodeVo对象，这个对象是将绑定手机的手机号 验证码和最后发送成功的时间封装起来的一个类
		VerifyCodeVo vc = UserContext.getCurrentVerifyCode();
		//判断
		if(vc != null
				&&vc.getPhoneNumber().equals(phoneNumber) //手机号
				&&vc.getVerifyCode().equalsIgnoreCase(verifyCode) //验证码
				&&DateUtil.secondsBetween(new Date(), vc.getLastSendTime()) <= BidConst.VERIFYCODE_VALIDATE_SECOND
				){
			return true;
		}
		return false;
	}

}
