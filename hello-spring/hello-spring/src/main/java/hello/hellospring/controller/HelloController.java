package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
       model.addAttribute("data", "spring!!");

        return "hello";

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name" ) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http body 부분에 데이터를 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring"

    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }



    static class Hello { //Hello 객체 생성
        private String name;

        public String getName(){
            return name;

        }

        public void setName(String name){
            this.name=name;
        }

    }

}
