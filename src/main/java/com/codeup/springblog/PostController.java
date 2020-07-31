package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
//    NORMALLY THIS METHOD IS NAMED INDEX
    public String posts(){
        return "this is a post";

}

    @GetMapping ("/posts/{id}")
    @ResponseBody
//    NORMALLY THIS METHOD IS NAMED SHOW
    public String postsId(@PathVariable long id) {
        return "this is a post id: " + id;
    }

    @GetMapping ("/posts/create")
    @ResponseBody
//    NORMALLY THIS METHOD IS NAMED CREATE
    public String createPosts() {
        return "this where you create a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
//    NORMALLY THIS METHOD IS NAMED INSERT - REPRESENTS INSERTING THIS INFO INTO A DATABASE.
    public String getInfoFromPostCreation() {
        return "this retrieves the information used to create a post";
    }


}
