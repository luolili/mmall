package com.mmall.test;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
@EqualsAndHashCode(of = "name")//只包含name的equal and hashcode
public class LombokTest {
    private String name;
    private String addr;

    public static void main(String[] args) {
        LombokTest test = new LombokTest();

        test.setName("hu");
        test.setAddr("tr");
        System.out.println(test);
    }
}
