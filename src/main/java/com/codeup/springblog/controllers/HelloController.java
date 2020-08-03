package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//define this as the controller
@Controller
public class HelloController {

//    our do get
    @GetMapping("/hello")
//    tell the controller to list for /hello and then do ...
    @ResponseBody
//    define our method
    public String hello (){
//        basic return for example
        return "Hello from Spring";
    }

    @GetMapping("/goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye";
    }

//    to pull information from our URI - pathvariable INDICATE IT WITH CURLY BRACES.

@GetMapping("/hello/{name}")
@ResponseBody
public String sayHello(@PathVariable String name){
//        RETURN A DYNAMIC STRING
        return "Hello " + name;

}

@GetMapping("/books/{id}")
@ResponseBody
    public String getBook(@PathVariable long id){
//        USE CASE WOULD BE GET THIS ID AND CONNECT TO DB TO GET BOOK INFO - THEN RETURN A VIEW(AKA JSP)
        return "Viewing book " + id;

}

//HOW TO USE REQUEST MAPPING:
@RequestMapping(path = "/welcome/{name}", method = RequestMethod.GET)
@ResponseBody
public String welcome(@PathVariable String name){
//        RETURN A DYNAMIC STRING
    return "Welcome " + name + " from a request mapping";

}

}
