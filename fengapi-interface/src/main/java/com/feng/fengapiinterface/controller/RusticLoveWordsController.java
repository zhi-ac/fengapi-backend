package com.feng.fengapiinterface.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.feng.fengapiinterface.model.entity.RusticLoveWords;
import com.feng.fengapiinterface.service.RusticLoveWordsService;
import org.apache.ibatis.annotations.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/love")
public class RusticLoveWordsController {
    @Resource
    private RusticLoveWordsService rusticLoveWordsService;

    @Resource
    private RedisTemplate redisTemplate;
    @GetMapping("/get/word")
    public String getNameByGet(String name, HttpServletRequest request) {
        String key = "rusticLoveWordsList";
        List<RusticLoveWords> list = (List<RusticLoveWords>) redisTemplate.opsForValue().get(key);
        if (list == null) {
            QueryWrapper<RusticLoveWords> queryWrapper =new QueryWrapper<>();
            queryWrapper.select("content");
            List<RusticLoveWords> tmplist = rusticLoveWordsService.list(queryWrapper);
            list = tmplist;
            redisTemplate.opsForValue().set(key, tmplist, 1, TimeUnit.HOURS);
        }
        int index = new Random().nextInt(list.size());
        return list.get(index).getContent();
    }
}
