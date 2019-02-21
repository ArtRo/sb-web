package com.example.demo;

import com.example.demo.validator.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dhf_ndm on 2019/2/21
 * the previous bug derived from the previous
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidaterTest {

    static String name;

    @Test
    public void test() {
        name="str";
        System.out.println("canshu "+name);
    }
}
