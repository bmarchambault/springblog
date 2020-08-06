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
//        this id refers to the id in the getmapping
//        Post myPost = new Post( "SophiesPost", "Hello World");
       Post pulledPost = postDao.getOne(id);
        model.addAttribute("post", pulledPost);
        return "posts/show";
    }
//        INSTEAD OF THIS BELOW, DO LIKE ABOVE AND CHANGE HTML TO POST.XYZ
//        Post myPost = postDao.getOne(id);
//        model.addAttribute("title", myPost.getTitle());
//        model.addAttribute("body", myPost.getBody());
//        model.addAttribute("id", id);
//        this "id" is referenced by the th value on the html(${id}) and id refers to the @pathVariable
//        return "posts/show";
//    }
//----------------------------------------------------------------------
//    MY SOLUTION TO SHOWING A SINGLE POST:
//----------------------------------------------------------------------




    @GetMapping ("/posts/create")
    public String createPost(Model model) {
        User user = new User("eiffelT", "somewhere@paris.com", "lotastairs");

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "this where you create a post";
    }
//============================================================================

    @PostMapping("/posts/create")
    @ResponseBody
//    NORMALLY THIS METHOD IS NAMED INSERT - REPRESENTS INSERTING THIS INFO INTO A DATABASE.
    public String getInfoFromPostCreation() {
        return "this retrieves the information used to create a post";
    }






//----------------------------------------------------------------------
//    MY SOLUTION TO EDITING THE POST to use with the show html:
//----------------------------------------------------------------------
//    @PostMapping("/post/save")
////    don't use path variables, instead grab the value with request param.
//    public String save(@RequestParam(name="id") long id,  @RequestParam(name="postTitle") String postTitle, @RequestParam(name="postBody") String postBody){
////the name in request param refer to the name in the html
//
////   post Dao will create a new post that's why i don't need to say new Post()
//    Post postToSave =  postDao.getOne(id) ;
//    postToSave.setTitle(postTitle);
//    postToSave.setBody(postBody);
//    postDao.save(postToSave);
////   don't need to add attributes, it should just update.
////redirect to something that is GetMapped, not the file path.
//    return"redirect:/index";
////    even though it says string, we are to return html's or redirects.
//    }
//----------------------------------------------------------------------
//    MY SOLUTION TO EDITING THE POST to use with the show html:
//----------------------------------------------------------------------





//----------------------------------------------------------------------
//    INSTRUCTOR SOLUTION TO EDITING THE POST to use with the edit html:
//----------------------------------------------------------------------

@GetMapping("/post/{id}/edit")
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
//----------------------------------------------------------------------  //


//----------------------------------------------------------------------
//    MY SOLUTION TO DELETING A SINGLE POST:
//----------------------------------------------------------------------
    @PostMapping("/post/delete")
    public String delete(@RequestParam(name="deleteId")long id){
        postDao.deleteById(id);
        return "redirect:/index";
//        THIS REDIRCT SHOULD MATCH WITH THE GETMAPPING YOU WANT TO REDIRECT TO.
    }
//----------------------------------------------------------------------
//    MY SOLUTION TO DELETING A SINGLE POST:
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
