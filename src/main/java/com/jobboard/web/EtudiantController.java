package com.jobboard.web;

import com.jobboard.entities.Etudiant;
import com.jobboard.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @RequestMapping(value = "/jobboard/etudiant")
    public String listEtudiant(ModelMap map) {
        map.addAttribute("etudiantList", etudiantRepository.findAll());
        map.addAttribute("etudiant",new Etudiant());
        return "etudiant/list";
    }

    @RequestMapping(value = "/jobboard/etudiant/edit", method = RequestMethod.GET)
    public String editEtudiant(ModelMap map, String email) {

        map.addAttribute("etudiantList", etudiantRepository.findAll());//Pour la liste
        map.addAttribute("etudiant", etudiantRepository.getEtudiantByEmail(email));//Pour le formulaire
        return "etudiant/list";
    }
    @RequestMapping(value = "/jobboard/etudiant/delete", method = RequestMethod.GET)
    public String deleteEtudiant(String email) {

        etudiantRepository.delete(etudiantRepository.getEtudiantByEmail(email));
        return "redirect:/jobboard/etudiant";
    }

    @RequestMapping(value = "/jobboard/etudiant/add", method = RequestMethod.POST)
    public String addProf(Etudiant etudiant, String dateNaissance) {
        //Crypt du mot de passe
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        String hashedPwd = pwdEncoder.encode(etudiant.getPassword());
        etudiant.setPassword(hashedPwd);

        try{
            etudiant.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse(dateNaissance));
        }catch (Exception e){
            e.printStackTrace();
        }

        etudiantRepository.save(etudiant);
        return "redirect:/jobboard/etudiant";
    }

}
