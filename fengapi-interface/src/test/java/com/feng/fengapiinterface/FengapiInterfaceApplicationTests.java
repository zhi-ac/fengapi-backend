package com.feng.fengapiinterface;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.feng.fengapiclientsdk.client.FengApiClient;
import com.feng.fengapiclientsdk.model.User;
import com.feng.fengapiinterface.mapper.RusticLoveWordsMapper;
import com.feng.fengapiinterface.model.entity.RusticLoveWords;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FengapiInterfaceApplicationTests {

    // 注入一个名为yuApiClient的Bean
    @Resource
    private FengApiClient fengApiClient;

    @Resource
    private RusticLoveWordsMapper resticLove;
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

    @Test
    void insertInto() {
        // 调用yuApiClient的getNameByGet方法，并传入参数"feng"，将返回的结果赋值给result变量
        String fileName = "E:\\yupiproject\\api\\fengapi-backend\\fengapi-interface\\src\\test\\java\\com\\feng\\fengapiinterface\\resticLove.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                RusticLoveWords rusticLoveWords = new RusticLoveWords();
                rusticLoveWords.setContent(line);
                rusticLoveWords.setUserId(1L);
                resticLove.insert(rusticLoveWords);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
