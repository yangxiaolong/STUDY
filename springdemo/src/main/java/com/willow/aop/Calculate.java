package com.willow.aop;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaolong on 2019\10\10 0010.
 */
@Component
public class Calculate implements CalculateInterface {

    //    @Autowired
    @Resource
    private DoCalculate doCalculate;

    @PostConstruct
    public void initMe() {
        System.out.println("initMe...");
    }

    @Value("{valueName:11}")
    private String id;

    @WebLog()
    public int div(int i, int j) {
        return i / j;
//        return doCalculate.doDiv(i, j);
    }

}
