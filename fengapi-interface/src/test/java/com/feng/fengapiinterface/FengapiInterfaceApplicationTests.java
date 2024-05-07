package com.feng.fengapiinterface;

import com.feng.fengapiclientsdk.client.FengApiClient;
import com.feng.fengapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FengapiInterfaceApplicationTests {

    // 注入一个名为yuApiClient的Bean
    @Resource
    private FengApiClient fengApiClient;
    @Test

    void contextLoads() {
        // 调用yuApiClient的getNameByGet方法，并传入参数"feng"，将返回的结果赋值给result变量
        String result = fengApiClient.getNameByGet("feng");
        // 创建一个User对象
        User user = new User();
        // 设置User对象的username属性为"kuanfeng"
        user.setUsername("kuanfeng");
        // 调用yuApiClient的getUserNameByPost方法，并传入user对象作为参数，将返回的结果赋值给usernameByPost变量
        String usernameByPost = fengApiClient.getUserNameByPost(user);
        // 打印result变量的值
        System.out.println(result);
        // 打印usernameByPost变量的值
        System.out.println(usernameByPost);
    }

}
