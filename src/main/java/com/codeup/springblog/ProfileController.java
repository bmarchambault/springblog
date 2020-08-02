package com.codeup.springblog;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ProfileController {

//    @GetMapping("/profile/{username}")
//    public String viewProfile(@PathVariable String username){
//        return "profile";
//    }

    //TO GET INFO FROM THE HTML, IMPORT MODEL OBJECT - WHICH REFERS TO THE VEIW NOT THE .JAVA FILE.  GET MAPPING HANDLES THE REQUEST VIA THE MODEL OBJECT.  THE WORD USER IS WHAT WE REFER TO  AS ${XYZ} ON THE HTML AND APPLIES THAT INFO TO THE VARIABLE WE ARE WANTING IT TO APPLY TO.
    @GetMapping("/profile/{username}")
    public String viewProfile(@PathVariable String username, Model model) {
        model.addAttribute("user", username);
        return "profile";
    }


    @GetMapping("/profile")
    public String viewProfile(){
        return "profile";
    }





    //    THIS URL PATH  NEEDS TO MATCH THE ACTION OF THE FORM YOU'RE TRYING TO ACCESS ON THE HTML
    @PostMapping("/profile")

    public String viewLoginInfo(@RequestParam(name = "username") String username, @RequestParam String password, Model model){
        ArrayList<String> ads = new ArrayList<String>();
        boolean isLoggedIn = true;
        ads.add("Ad 1");
        ads.add("Ad 2");
        ads.add("Ad 3");


        model.addAttribute("username", username);
        model.addAttribute("password", password);
//        THE RETURN PATH IS YOUR REDIRECT
        return "profile";
    }
}
