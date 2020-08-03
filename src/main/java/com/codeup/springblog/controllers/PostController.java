package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
//    @ResponseBody
//    NORMALLY THIS METHOD IS NAMED INDEX
//    public String posts(){
//        return "this is a post";
//    REFACTORED WITH A CORRELATING HTML THAT SHOWS ALL POSTS:

    public String index(Model model){
        List<Post>posts = new ArrayList<>();
        Post p1 = new Post("Title1", "Some text about this post");
        Post p2 = new Post("Title2", "Some text about this post");

        posts.add(p1);
        posts.add(p2);
        posts.add(new Post(3, "Title 3", "some text about this post"));
        model.addAttribute("posts", posts);
        return "posts/index";
}

//=========================================================================
    @GetMapping ("/show")
//   @ResponseBody
//    NORMALLY THIS METHOD IS NAMED SHOW
//    public String postsId(@PathVariable long id) {
//       return "this is a post id: " + id;
//       REFACTORED WITH A CORRELATING HTML THAT SHOW ONE POST:
        public String show( Model model) {
        Post p1 = new Post( "TitleUno", "Some text about this post");

        model.addAttribute("postTitle", p1.getTitle());
        model.addAttribute("postBody", p1.getBody());
            return "posts/show";
    }
    //**********************************************************************
//INSTRUCTOR SOLUTION BELOW.  THE ABOVE GETMAPPING /SHOW WORKS TOO. :

    @GetMapping ("/post/{id}")
    public String show( @PathVariable int id, Model model) {
        Post myPost = new Post( "SophiesPost", "Hello World");

        model.addAttribute("title", myPost.getTitle());
        model.addAttribute("body", myPost.getBody());
        return "posts/show";
    }

//    ====================================================================

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
