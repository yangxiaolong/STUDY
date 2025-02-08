package com.willow.aop;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangxiaolong
 */
@Component
public class DoCalculate {

    private CalculateInterface calculate;

    //    @Autowired
    @Resource
    public void setCalculate(CalculateInterface calculate) {
        this.calculate = calculate;
    }

//    public void setCalculate(@Autowired CalculateInterface calculate) {
//        this.calculate = calculate;
//    }

//    @Autowired
//    public DoCalculate(CalculateInterface calculate) {
//        this.calculate = calculate;
//    }

    public int doDiv(int i, int j) {
        return i / j;
    }

}
