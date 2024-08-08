package com.willow.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaolong on 2019\10\10 0010.
 */
@Component
public class Calculate {

    @Autowired
    private DoCalculate doCalculate;

    @WebLog()
    public int div(int i, int j) {
        return doCalculate.doDiv(i, j);
    }

}
