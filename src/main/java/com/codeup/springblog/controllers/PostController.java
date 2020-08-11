package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UsersRepository;
import com.codeup.springblog.services.EmailService;
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
    private final UsersRepository usersDao;
    private EmailService emailService;


    public PostController(PostRepository postDao, UsersRepository usersDao, EmailService emailService){

        this.postDao = postDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

//**********************************************************
// DEPENDENCY INJECTION.  NEEDED TO ACCESS THE JPA REPOSITORY
//**********************************************************

    @GetMapping("/email")
    @ResponseBody
    public String sendEmail(){
        emailService.prepareAndSend(postDao.getOne(1L), "test email", "test message");
        return "Check your mailtrap inbox";
    }


//----------------------------------------------------------------------
//    MY SOLUTION TO SHOWING ALL POSTS:
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
//    USEING FORM MODEL BINDING.
    @GetMapping ("/posts/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
//        return "Here is the form to create a post";
        return "posts/create";
    }



// this should receive the form info  WITH FORM MODEL BINDING and save it then redirect.
    @PostMapping("/posts/create")
    public String insertNewPost(@ModelAttribute Post post){
//        HARD CODE A USER UNTIL SPRING SECURITY.
//        need to add a user because of the many to many .
        User user = usersDao.getOne(1L);
        post.setAuthor(user);
//        save the post.
        postDao.save(post);
        return "redirect:/posts";






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
//
//@PostMapping("/posts/{id}/edit")
//public String update(@PathVariable long id,
//                     @RequestParam(name="title") String title,
//                     @RequestParam(name="body") String body){
//        Post postToEdit = postDao.getOne(id);
//        postToEdit.setTitle(title);
//        postToEdit.setBody(body);
//
//        postDao.save(postToEdit);
//        return "redirect:/posts/" + id;
//}

//with the save method, if the object has an id, it will create a new one. if it doesnt, it will update.
//----------------------------------------------------------------------
//    END EDIT W/O FORM MODEL BINDING
//----------------------------------------------------------------------


//----------------------------------------------------------------------
//    INSTRUCTOR SOLUTION TO EDITING THE POST to use with the edit html:  FORM MODEL BINDING:
//----------------------------------------------------------------------
//GET MAPPING IS THE SAME

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id,
                         @ModelAttribute Post post){
//       STILL HAVE TO HARDCODE THE USER:
        User user = usersDao.getOne(1L);
        post.setAuthor(user);
        postDao.save(post);
        return "redirect:/posts/";
    }

    //----------------------------------------------------------------------
//    END EDIT WITH FORM MODEL BINDING:
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
