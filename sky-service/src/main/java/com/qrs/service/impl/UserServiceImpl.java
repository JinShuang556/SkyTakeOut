package com.qrs.service.impl;

import com.google.gson.Gson;
import com.qrs.constant.MessageConstant;
import com.qrs.dto.UserLoginDTO;
import com.qrs.entity.User;
import com.qrs.exception.LoginFailedException;
import com.qrs.mapper.UserMapper;
import com.qrs.properties.WeChatProperties;
import com.qrs.service.UserService;
import io.swagger.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    private final WeChatProperties weChatProperties;
    private final UserMapper userMapper;

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String code = userLoginDTO.getCode();
        //向微信发送请求：
        try {
            //设置uri并设置Query的参数
            URI uri = new URIBuilder(WX_LOGIN)
                    .addParameter("appid",weChatProperties.getAppId())
                    .addParameter("secret",weChatProperties.getAppSecret())
                    .addParameter("js_code",code)
                    .addParameter("grant_type","authorization_code")
                    .build();
            //HttpClient客户端
            CloseableHttpClient client = HttpClients.createDefault();
            //设置请求方式：
            HttpGet httpGet = new HttpGet(uri);
            //发送请求并获得响应数据：
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            //获得响应状态：
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("微信登录响应状态码：{}",statusCode);
            //获取响应数据：
            HttpEntity body = httpResponse.getEntity();
            String result = EntityUtils.toString(body);
            log.info("微信登录响应数据：{}",result);
            //解析响应回来的json数据：
            JSONObject jsonObject = new JSONObject(result);
            String openid = jsonObject.get("openid").toString();
            //检查openid是否合法：
            if(openid == null || openid.isEmpty()){
                throw new LoginFailedException(MessageConstant.LOGIN_FAILURE);
            }
            //将openid保存到数据库中：
            User user = userMapper.selectByOpenid(openid);
            if(user == null){
                //为新用户添加到数据库中：
                user = new User();
                user.setOpenid(openid);
                user.setCreateTime(LocalDateTime.now());
                userMapper.insert(user);
            }
            return user;
        } catch (URISyntaxException | IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
