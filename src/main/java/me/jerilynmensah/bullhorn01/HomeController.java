package me.jerilynmensah.bullhorn01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "index";
    }
    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("messages", new Message());
        return "messageform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Message messages, BindingResult result)
    {
        if(result.hasErrors()){
            return "messageform";
        }
        messageRepository.save(messages);
        return "redirect:/";
    }
    @RequestMapping("/content/{id}")
    public String showContent(@PathVariable("message") long id, Model model) {
        model.addAttribute("messages", messageRepository.findOne(id));
        return "show";
    }
    @RequestMapping("/posteddate/{id}")
    public String showDate(@PathVariable("message") long id, Model model) {
        model.addAttribute("messages", messageRepository.findOne(id));
        return "messageform";
    }



}
