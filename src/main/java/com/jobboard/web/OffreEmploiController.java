package com.jobboard.web;

import com.jobboard.entities.Entreprise;
import com.jobboard.entities.OffreEmploi;
import com.jobboard.repositories.EntrepriseRepository;
import com.jobboard.repositories.OffreEmploiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OffreEmploiController {

    @Autowired
    private OffreEmploiRepository offreEmploiRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @RequestMapping(value = "/jobboard/offre", method = RequestMethod.GET)
    public String listOffreEmploi(ModelMap model){
        List<OffreEmploi> offreEmplois = offreEmploiRepository.findAll();
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        model.addAttribute( "offreList", offreEmplois);
        model.addAttribute( "liste_entreprise", entreprises);
        model.addAttribute("offre", new OffreEmploi());
        return "offre/list";
    }

    @RequestMapping(value = "/jobboard/offre/add", method = RequestMethod.POST)
    public String addOffreEmploi(OffreEmploi offreEmploi, String emailEnt, String dateOffre){
        try{
            offreEmploi.setDateOffre(new SimpleDateFormat("yyyy-MM-dd").parse(dateOffre));
        }catch (Exception e){
            e.printStackTrace();
        }
        Entreprise e = entrepriseRepository.getEntrepriseByEmail(emailEnt);
        offreEmploi.setEntreprise(e);
        if(e == null) throw new RuntimeException("L'entreprise ne doit pas être nulle");
        offreEmploiRepository.save(offreEmploi);
        return "redirect:/jobboard/offre";
    }

    @RequestMapping(value = "/jobboard/offre/edit", method = RequestMethod.GET)
    public String editOffreEmploi(ModelMap map, Long id) {

        map.addAttribute("offreList", offreEmploiRepository.findAll());//Pour la liste
        map.addAttribute("liste_entreprise", entrepriseRepository.findAll());//Pour la liste
        map.addAttribute("offre", offreEmploiRepository.getOne(id));//Pour le formulaire
        return "offre/list";
    }
    @RequestMapping(value = "/jobboard/offre/delete", method = RequestMethod.GET)
    public String deleteEntreprise(Long id) {
        offreEmploiRepository.delete(offreEmploiRepository.getOne(id));
        return "redirect:/jobboard/offre";
    }
}
