package com.kwk;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
public class HelloController {
    @Resource
    private TestService testService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", testService.dump());
        return "hello.jsp";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String printHello2(ModelMap model) {
        //model.addAttribute("message", testService.dump());
        return "redirect:http://www.baidu.com";
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public String printHello3(ModelMap model) {
        model.addAttribute("message", testService.dump());
        return "forward:/hello";
    }

    @RequestMapping(value = "/hello4", method = RequestMethod.GET)
    public String printHello4(ModelMap model,
                              @RequestParam(value = "age", required = false) Integer age) {
        if (age == null) {
            return "forward:/hello";
        }

        User user1 = new User(10, "user1");
        User user2 = new User(20, "user2");

        List<User> userList = Lists.newArrayList(user1, user2);

        model.addAttribute("userList", userList);

        return "a.ftl";
    }

    @RequestMapping(value = "/hello5/{orderId}", method = RequestMethod.GET)
    public String printHello5(ModelMap model, @PathVariable String orderId) {
        model.addAttribute("message", testService.dump());
        return "redirect:http://www.baidu.com";
    }

    // /spring-web/spring-web-3.0.5.jar
    @RequestMapping("/spring-web/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public String printHello6(@PathVariable String version, @PathVariable String extension) {
        System.out.println(version);
        System.out.println(extension);
        return "redirect:http://www.baidu.com";
    }

    @RequestMapping(value = "/hello7", method = RequestMethod.GET)
    public String printHello7(
            HttpServletRequest request,
            HttpSession httpSession,
            WebRequest webRequest,
            Locale locale,
            SessionStatus sessionStatus,
            ModelMap model
    ) {
        model.addAttribute("message", testService.dump());
        return "redirect:http://www.baidu.com";
    }

    @RequestMapping(value = "/hello8", method = RequestMethod.GET)
    public String printHello8(@RequestHeader("Accept-Encoding") String encoding) {
        return "redirect:http://www.baidu.com";
    }

    @RequestMapping(value = "/hello9", method = RequestMethod.GET)
    public String printHello9(ModelMap model) {
        throw new IllegalArgumentException("invalid para");
    }

//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<String> handleIOException(IOException ex) {
//        // prepare responseEntity
//        return responseEntity;
//    }

    public static class User {
        private int age;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }

}
