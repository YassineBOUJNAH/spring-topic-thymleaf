package com.sqli.topic.controller;

import com.sqli.topic.model.Topic;
import com.sqli.topic.repository.TopicRepository;
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
@RequestMapping("/")
public class TopicController {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping()
    public String home(Model model) {
        return "redirect:/topics/list";
    }

    @GetMapping("topics/addtopic")
    public String showSignUpForm(Topic topic) {
        return "add-topic";
    }

    @GetMapping("topics/list")
    public String showUpdateForm(Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        return "index";
    }

    @PostMapping("topics/add")
    public String addTopic(@Valid Topic topic, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-topic";
        }

        topicRepository.save(topic);
        return "redirect:list";
    }

    @GetMapping("topics/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Topic topic = topicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid topic Id:" + id));
        model.addAttribute("topic", topic);
        return "update-topic";
    }

    @PostMapping("topics/update/{id}")
    public String updateTopic(@PathVariable("id") long id, @Valid Topic topic, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            topic.setId(id);
            return "update-topic";
        }

        topicRepository.save(topic);
        model.addAttribute("topics", topicRepository.findAll());
        return "index";
    }

    @GetMapping("topics/delete/{id}")
    public String deleteTopic(@PathVariable("id") long id, Model model) {
        Topic topic = topicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid topic Id:" + id));
        topicRepository.delete(topic);
        model.addAttribute("topics", topicRepository.findAll());
        return "index";
    }
}