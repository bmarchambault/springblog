package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    //    for us to use the thymleaf html page we don't need the  @ResponseBody following:

//    @GetMapping("/")
//    @ResponseBody
//                public String landingPageMessage (){
//                    return "This is the landing page";
//        }
//    }

//remap the getmapping what you name the html file needs to match exactly.  it's not returning the string, its returning the html :

    @GetMapping("/home")
                public String landingPageMessage (){
                    return "home";
        }

        @PostMapping("/")
    public String returnCohort(@RequestParam(name = "cohort") String cohort, Model model){
        model.addAttribute("cohort", cohort);
        return "home";
        }
    }
