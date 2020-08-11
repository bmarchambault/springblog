package com.codeup.springblog.services;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class SillySvc {
//    service example 2.
//    accessing doas in a method
//    going to count the number of characters in an ad
    private AdRepository  adsDao;
    private UsersRepository usersDao;

    public SillySvc(AdRepository adsDao, UsersRepository usersDao){

        this.adsDao = adsDao;
        this.usersDao = usersDao;
    }

    public int totalAdCharacters(){
//        iterable is a generic list.
        Iterable<Ad> ads = adsDao.findAll();
        Iterable<User> users = usersDao.findAll();
//        make a bucket
        int total = 0;
        for(Ad ad : ads) {
            total += ad.getDescription().length();
        }
        for (User user : users){
            total += user.getUsername().length();
        }
        return total;
    }

}
