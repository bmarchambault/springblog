package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

//    DISPLAY THE HTML BEFORE THE GUESS.
    @GetMapping ("/roll-dice")
    public String viewRollDice(){
        return "roll-dice";
    }


    @GetMapping  ("/roll-dice/{n}")
//    @ResponseBody DONT USE THIS UNLESS YOU'RE RETURNING A STRING - NOT A MODEL RETURNING A STRING.
    public String viewGuessResults(@PathVariable int n,   Model model){

        String message = "";
        int randomNum = (int) Math.random()*6 +1;
        String roll = String.valueOf(randomNum);
        String guess = String.valueOf(n);

        if(roll.equals(guess)) {
            message = "Great guess! You guessed " + n + ". Dice roll equaled " + roll;
        }else{
            message = "Try again.  You guessed " + n + ". Dice roll equaled " + roll;
        }


model.addAttribute("message", message);
return "roll-dice";
    }
}
