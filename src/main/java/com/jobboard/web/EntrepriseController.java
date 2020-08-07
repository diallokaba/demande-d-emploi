package com.jobboard.web;

import com.jobboard.entities.Entreprise;
import com.jobboard.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class EntrepriseController {

    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @RequestMapping(value = "/jobboard/entreprise")
    public String listEntreprise(ModelMap map) {
        map.addAttribute("entrepriseList", entrepriseRepository.findAll());
        map.addAttribute("entreprise",new Entreprise());
        return "entreprise/list";
    }

    @RequestMapping(value = "/jobboard/entreprise/edit", method = RequestMethod.GET)
    public String editEntreprise(ModelMap map, String email) {

        map.addAttribute("entrepriseList", entrepriseRepository.findAll());//Pour la liste
        map.addAttribute("entreprise", entrepriseRepository.getEntrepriseByEmail(email));//Pour le formulaire
        return "entreprise/list";
    }
    @RequestMapping(value = "/jobboard/entreprise/delete", method = RequestMethod.GET)
    public String deleteEntreprise(String email) {

        entrepriseRepository.delete(entrepriseRepository.getEntrepriseByEmail(email));
        return "redirect:/jobboard/entreprise";
    }

    @RequestMapping(value = "/jobboard/entreprise/add", method = RequestMethod.POST)
    public String addProf(Entreprise entreprise) {
        //Crypt du mot de passe
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        String hashedPwd = pwdEncoder.encode(entreprise.getPassword());
        entreprise.setPassword(hashedPwd);

        entrepriseRepository.save(entreprise);
        return "redirect:/jobboard/entreprise";
    }

    @RequestMapping(value = "/jobboard/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "")
    public String home() {
        return "redirect:/jobboard/logon";
    }

    @RequestMapping(value = "/jobboard/logon")
    public String logon(HttpServletRequest req, HttpServletResponse resp) {
        return "redirect:/jobboard/presentation";
    }

    @RequestMapping(value = "/")
    public String index() {

        return "redirect:/jobboard/logon";
    }

    @RequestMapping(value="/jobboard/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/jobboard/login?logout";
    }

    @RequestMapping(value="/jobboard/403")
    public String accessDenied(){
        return "403";
    }
}
