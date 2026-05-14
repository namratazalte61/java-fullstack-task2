package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ContactRepository repo;

    // Home Page
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("contacts", repo.findAll());

        return "index";
    }

    // Save Contact
    @PostMapping("/save")
    public String saveContact(@RequestParam String name,
                              @RequestParam String email,
                              Model model) {

        Contact contact = new Contact(name, email);

        repo.save(contact);

        model.addAttribute("message", "Contact Saved Successfully!");

        model.addAttribute("contacts", repo.findAll());

        return "index";
    }

    // Delete Contact
    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {

        repo.deleteById(id);

        return "redirect:/";
    }
}