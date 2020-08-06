package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//**********************************************************
// DEPENDENCY INJECTION.  NEEDED TO ACCESS THE JPA REPOSITORY
//**********************************************************
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){

        this.postDao = postDao;
        this.userDao = userDao;
    }

//**********************************************************
// DEPENDENCY INJECTION.  NEEDED TO ACCESS THE JPA REPOSITORY
//**********************************************************




//----------------------------------------------------------------------
//    MY SOLUTION TO SHOWING A SINGLE POST:
//----------------------------------------------------------------------

//THIS PATH NAME CAN BE WHATEVER I WANT AS LONG AS THE RETURN PATH IS CORRECT
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String index(Model model){
//         List<Post>myPosts = postDao.findAll();  ADDING THIS AND CHANGING THE MODEL TO SAY MYPOSTS WORKS TOO.  BETTER FOR VALIDATION.
       model.addAttribute("posts", postDao.findAll());
        return "posts/index";
}


//----------------------------------------------------------------------
//    MY SOLUTION TO SHOWING ALL POSTS:
//----------------------------------------------------------------------




//----------------------------------------------------------------------
//    MY SOLUTION TO SHOWING A SINGLE POST:
//----------------------------------------------------------------------

    @GetMapping ("/posts/{id}")
//    the curly brace id refers the id in the database
    public String show( @PathVariable long id, Model model) {
       Post pulledPost = postDao.getOne(id);
        model.addAttribute("post", pulledPost);
        return "posts/show";
    }

//----------------------------------------------------------------------
//    MY SOLUTION TO SHOWING A SINGLE POST:
//----------------------------------------------------------------------


//============================================================================
//this should give you the form.  need to create a create view:
    @GetMapping ("/posts/create")
    @ResponseBody
    public String createPost() {
        return "Here is the form to create a post";
//        return "posts/create";
    }



// this should receive the form info and save it then redirect.
    @PostMapping("/posts/create")
    @ResponseBody
    public String insert(){
        return "Post has been created!";






//    if you're going to rename your parameter, you need to include the name=""
//    public String insert(@RequestParam(name="title") String title, @RequestParam String body, Model model) {
//
////        hardcoding the user we manually inserted in the database.
//        User user = userDao.getOne(1L);
//        Post post = new Post(title, body, user);
//        postDao.save(post);
//
//        return "redirect:/posts";
    }


//----------------------------------------------------------------------
//    INSTRUCTOR SOLUTION TO EDITING THE POST to use with the edit html:
//----------------------------------------------------------------------

@GetMapping("/posts/{id}/edit")
public String editForm( @PathVariable long id, Model model) {
    model.addAttribute("post", postDao.getOne(id));
    return "posts/edit";
}

@PostMapping("/posts/{id}/edit")
public String update(@PathVariable long id,
                     @RequestParam(name="title") String title,
                     @RequestParam(name="body") String body){
        Post postToEdit = postDao.getOne(id);
        postToEdit.setTitle(title);
        postToEdit.setBody(body);

        postDao.save(postToEdit);
        return "redirect:/posts/" + id;
}

//with the save method, if the object has an id, it will create a new one. if it doesnt, it will update.
//----------------------------------------------------------------------
//    INSTRUCTOR SOLUTION TO EDITING THE POST to use with the edit html:
//----------------------------------------------------------------------


    //----------------------------------------------------------------------
//    INSTRUCTOR SOLUTION TO Deleting THE POST
//----------------------------------------------------------------------  //
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}
