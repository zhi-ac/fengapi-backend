

package com.sheng.fengapiinterface.controller;

import com.feng.fengapiclientsdk.model.User;
import com.feng.fengapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class NameController {
    @GetMapping("/name")
    public String getNameByGet(String name, HttpServletRequest request) {
        System.out.println(1/0);
        System.out.println(request.getHeader("feng"));
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {


        // 1.拿到这五个我们可以一步一步去做校验,比如 accessKey 我们先去数据库中查一下
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");

        // TODO 2.校验权限,这里模拟一下,直接判断 accessKey 是否为"yupi",实际应该查询数据库验证权限
        if (!accessKey.equals("feng")) {
            throw new RuntimeException("无权限");
        }

        // TODO 3.校验一下随机数,因为时间有限,就不带大家再到后端去存储了,后端存储用hashmap或redis都可以
        // 校验随机数,模拟一下,直接判断nonce是否大于10000
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }

        // 4.校验时间戳与当前时间的差距,交给大家自己实现
//        if (timestamp) {}
        // TODO 实际是从数据库中获取secretKey
        String serverSign = SignUtils.getSign(body, "abcdefgh");
        //存在问题 中文时body会乱码
        if (!serverSign.equals(sign)) {
            throw new RuntimeException("无权限");
        }
        String result = "POST 用户名字是" + user.getUsername();
        return result;
    }
}
