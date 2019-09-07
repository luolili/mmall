package com.mmall.test;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test//精度丢失
    public void test() {
        System.out.println(0.05 + 0.1);
        System.out.println(1.0 - 0.43);
        System.out.println(100 * 0.43);
        System.out.println(2.12 / 100);
    }

    @Test//精度丢失
    public void test2() {
        BigDecimal b1 = new BigDecimal(0.05);
        BigDecimal b2 = new BigDecimal(0.1);
        System.out.println(b1.add(b2));
    }

    @Test //right
    public void test3() {
        BigDecimal b1 = new BigDecimal("0.05");
        BigDecimal b2 = new BigDecimal("0.01");
        System.out.println(b1.add(b2));
    }

}
