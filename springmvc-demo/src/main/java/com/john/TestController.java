package com.john;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 接收单个单个的请求参数（当方法中参数名与请求参数名一样时，可以省略@RequestParam注解）
     * 当@RequestParam省略不写时，参数默认是可选的
     * @param name
     * @param title
     * @return
     */
    @RequestMapping("/test")
    public String test(String name, String title) {
        System.out.println("name: " + name + ", title: " + title);
        return "test";
    }

    /**
     * 显示写上@RequestParam注解后，title参数就必须传了，否则就报400，可以通过设置属性解决
     * @param name
     * @param title
     * @return
     */
    @RequestMapping("/test1")
    public String test1(@RequestParam(name = "sname", required = false) String name, @RequestParam String title) {
        System.out.println("name: " + name + ", title: " + title);
        return "test";
    }

    /**
     *
     * @param stu
     * @return
     */
    @RequestMapping("/stu")
    @ResponseBody
    public Student getStudent(@RequestBody Student stu) {
        return stu;
    }

    @RequestMapping("/baidu")
    public void baidu() {

        String baidu = restTemplate.getForObject("https://www.baidu.com", String.class);
        System.out.println(baidu);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
