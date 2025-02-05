package com.willow.aop;

import org.springframework.stereotype.Component;

/**
 * Created by yangxiaolong on 2019\10\10 0010.
 */
@Component
public class Calculate implements CalculateInterface {

    /*@Autowired
    private DoCalculate doCalculate;*/

    @WebLog()
    public int div(int i, int j) {
        return i / j;
//        return doCalculate.doDiv(i, j);
    }

}
