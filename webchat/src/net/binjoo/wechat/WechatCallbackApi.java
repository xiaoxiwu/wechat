package net.binjoo.wechat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class WechatCallbackApi extends HttpServlet {
	// 鑷畾涔�token
	private String  TOKEN= "845X2550903XD6ZC54CACDB82EAD4350";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp =request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");

		// 随机字符串
		String echostr = request.getParameter("echostr");

		String[] list={TOKEN,timestamp,nonce};
		Arrays.sort(list); // 字典序排序
		String bigStr = list[0] + list[1] + list[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes())
				.toLowerCase();

		// 确认请求来至微信
		if (digest.equals(signature)) {
			response.getWriter().print(echostr);
		}
	}
}
