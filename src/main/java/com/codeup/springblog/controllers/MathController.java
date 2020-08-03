package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static java.lang.Integer.parseInt;

@Controller
public class MathController {

//    CONTROLLERS WILL ALWAYS RETURN A STRING:


//    USING REQUEST MAPPING:
    @RequestMapping(path = "/add/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int num1, @PathVariable int num2) {
        return "your numbers = " + (num1 + num2);
    }


// WILL NOT WORK:
//    @GetMapping("/add/3/and/4")
//    @ResponseBody
//    public String add3and4 (int x, int y){
//        String math;
//        x=3;
//        y=4;
//       return x + y;
//    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtract(@PathVariable int num1, @PathVariable int num2 ){
        int sum = num1 - num2;
        return String.valueOf(sum);
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiply (@PathVariable int num1, @PathVariable int num2) {
      int product = num1 * num2;
        return String.valueOf(product);
    }

    @GetMapping("/divide/{num1}/and/{num2}")
    @ResponseBody
    public String divide (@PathVariable int num1, @PathVariable int num2){
        int quotient = num1 / num2;
        return String.valueOf(quotient);
    }


}

