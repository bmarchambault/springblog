package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {

    // dependency injection
    private final AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String getAd(@PathVariable long id) {
//        return adsDao.getOne(id).toString();
        return adsDao.getOne(id).toString();
    }


    // return json
    @GetMapping("/ads")
    @ResponseBody
    public List<Ad> getAds() {
        return adsDao.findAll();
    }

    // return a view
    @GetMapping("/ads/view")
    public String getAdsIndex(Model model) {
        model.addAttribute("ads", adsDao.findAll());
//        return "ads/index";
        return "rel/ads";
    }

    @GetMapping("/ads/save")
    public String save() {
        Ad adToSave = new Ad();
        adToSave.setTitle("New Ad 1!");
        adToSave.setDescription("This is the new ad description!");
        adsDao.save(adToSave);
        return "redirect:/ads";
    }

    @GetMapping("/ads/test")
    @ResponseBody
    public String getTestAd() {
        return adsDao.findByTitle("title 1").toString();
    }

    @GetMapping("/ads/create")
    public String showCreateForm(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@ModelAttribute Ad ad){
        adsDao.save(ad);
        return "redirect: ads/view";
    }


}
