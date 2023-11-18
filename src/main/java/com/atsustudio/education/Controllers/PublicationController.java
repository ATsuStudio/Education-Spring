package com.atsustudio.education.Controllers;

import com.atsustudio.education.Interfaces.IPostRepository;
import com.atsustudio.education.Models.Post;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PublicationController {
    @Autowired
    private IPostRepository postRepository;

    @GetMapping("/news")
    public String news(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        Post post = postRepository.findById(2L).orElseThrow();
        return "news";
    }
    @GetMapping("/news/add")
    public String addNews(Model model) {
        return "news_create";
    }
    @PostMapping("/news/add")
    public String CreateNews(@RequestParam String title, @RequestParam String author, @RequestParam String body, Model model, HttpServletRequest request) {
        Post post = new Post(title,body,author);
        postRepository.save(post);
        String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
        return "redirect:" + baseUrl + "/news";
    }
    @GetMapping("/news/{id}")
    public String getSpecNews(@PathVariable(value = "id") long nid, Model model,HttpServletRequest request) {

        if(!postRepository.findById(nid).isPresent()){
            String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
            return "redirect:" + baseUrl + "/news";
        }

        Optional<Post> post =  postRepository.findById(nid);
        ArrayList<Post> postArr = new ArrayList<>();
        post.ifPresent(postArr::add);
        model.addAttribute("post", postArr);

        return "specNews";
    }

    @GetMapping("/news/{id}/edit")
    public String getEditNews(@PathVariable(value = "id") long nid, Model model,HttpServletRequest request) {

        if(!postRepository.findById(nid).isPresent()){
            String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
            return "redirect:" + baseUrl + "/news";
        }

        Optional<Post> post =  postRepository.findById(nid);
        ArrayList<Post> postArr = new ArrayList<>();
        post.ifPresent(postArr::add);
        model.addAttribute("post", postArr);

        return "news_edit";
    }



    @PostMapping("/news/{id}/edit")
    public String getEditNews(@RequestParam String title,
                              @RequestParam String author,
                              @RequestParam String body,
                              @PathVariable(value = "id") long nid,
                              HttpServletRequest request,
                              Model model
    ) {

        if(!postRepository.findById(nid).isPresent()){
            String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
            return "redirect:" + baseUrl + "/news";
        }

        Post post =  postRepository.findById(nid).orElseThrow();
        post.setTitle(title);
        post.setAuthor(author);
        post.setContent(body);
        postRepository.save(post);

        String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
        return "redirect:" + baseUrl + "/news/"+nid;
    }

}
