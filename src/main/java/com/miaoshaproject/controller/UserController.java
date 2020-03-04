package com.miaoshaproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by chenhaiyang on 2019/9/13.
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true")
public class UserController extends BaseController{

    //短信平台相关参数
    //这个不用改
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "榛子云系统上的 Id";
    private String appSecret = "榛子云系统上的密码";

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    /**
     * 用户登陆接口
     * @param telphone
     * @param password
     * 注意如果没有@ResponseBody，则前端会直接返回404错误
     * @return
     */
    @RequestMapping(value = "/login",method={RequestMethod.POST},consumes = {CONTEXT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam("telphone") String telphone,
                                  @RequestParam("encrptPassword") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone) || org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //用户登陆服务，用来校验用户登陆是否合法
        UserModel userModel = userService.validateLogin(telphone, this.encodeByMD5(password));

        //将登陆凭证加入到用户登陆成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);

        return CommonReturnType.create(null);
    }

    /**
     * 用户注册接口
     * @param telphone  手机号
     * @param optCode  验证码
     * @param name  用户昵称
     * @param gender  性别
     * @param age  年龄
     * @param password  密码
     * @return 通用返回类型
     * @throws BusinessException  自定义异常
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTEXT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam("optCode") String optCode,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender")Byte gender,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("encrptPassword") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和optCode符合
        String sessionOptCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if(!StringUtils.equals(optCode,sessionOptCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码错误");
        }
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setId(null);
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.encodeByMD5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    /**
     * MD5加密
     * @param str  任一字符串
     * @return  字符串
     * @throws NoSuchAlgorithmException  算术异常
     * @throws UnsupportedEncodingException  不支持编码异常
     */
    public String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
        return newstr;
    }


    //用户获取otp短信接口
//    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTEXT_TYPE_FORMED})
//    @ResponseBody
//    public CommonReturnType getOtp(@RequestParam(name="telphone")String telphone){
//        //需要按照一定的规则生成otp验证码
//        Random random = new Random();
//        int randomInt = random.nextInt(99999);
//        randomInt += 10000;
//        String optCode = String.valueOf(randomInt);
//
//        //将otp验证码同对应用户的手机号关联,使用HttpSession的方式绑定他的手机号和验证码。实际开发中应该使用redis
//        httpServletRequest.getSession().setAttribute(telphone,optCode);
//
//        //将otp验证码通过短信通道发送给用户，省略
//        System.out.println("telphone =" + telphone + "&optCode=" + optCode);
//
//        return CommonReturnType.create(null);
//    }

    //用户获取otp短信接口
    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTEXT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone")String telphone){
        try {
            JSONObject json = null;
            //生成6位验证码
            String code  =  String.valueOf(new Random().nextInt(899999) + 100000);
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            String result = client.send(telphone, "最帅的ocean您好，您的验证码为:" + code  + "，该码有效期为5分钟，该码只能使用一次！");
            System.out.println(result);
            System.out.println("telphone =" + telphone + "&optCode=" + code);
            json = JSONObject.parseObject(result);
            if(json.getIntValue("code") != 0){     //发送短信失败
                int a = json.getIntValue("code");
                return CommonReturnType.create(false);
            }
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
//            HttpSession session = httpServletRequest.getSession();
//            json = new JSONObject();
//            json.put("telphone",telphone);
//            json.put("code",code);
//            json.put("createTime",System.currentTimeMillis());
//            // 将认证码存入SESSION
            httpServletRequest.getSession().setAttribute(telphone,code);
            return CommonReturnType.create(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if(userModel == null){
//                userModel.setEncrptPassword("123");
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        //将核心领域模型用户对象转化为可供 UI 使用的viewobject
        UserVO userVO = convertFromModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }




}
