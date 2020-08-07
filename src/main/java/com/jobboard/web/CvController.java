package com.jobboard.web;

import com.jobboard.entities.Cv;
import com.jobboard.entities.Etudiant;
import com.jobboard.repositories.CvRepository;
import com.jobboard.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CvController {

    @Autowired
    private CvRepository cvRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @RequestMapping(value = "/jobboard/cv", method = RequestMethod.GET)
    public String listCv(ModelMap model){
        model.addAttribute("cvList", cvRepository.findAll());
        model.addAttribute("liste_etudiant", etudiantRepository.findAll());
        model.addAttribute("cv", new Cv());
        return "cv/list";
    }

    @RequestMapping(value = "/jobboard/cv/add", method = RequestMethod.POST)
    public String addCv(Cv cv, String emailEtud){
        Etudiant e = etudiantRepository.getEtudiantByEmail(emailEtud);
        cv.setEtudiant(e);
        if(e == null) throw new RuntimeException("veuillez saisir le champ étudiant");
        cvRepository.save(cv);
        return "redirect:/jobboard/cv";
    }

    @RequestMapping(value = "/jobboard/cv/edit", method = RequestMethod.GET)
    public String editEntreprise(ModelMap map, Long id) {

        map.addAttribute("cvList", cvRepository.findAll());//Pour la liste
        map.addAttribute("liste_etudiant", etudiantRepository.findAll());//Pour la liste
        map.addAttribute("cv", cvRepository.getOne(id));//Pour le formulaire
        return "cv/list";
    }
    @RequestMapping(value = "/jobboard/cv/delete", method = RequestMethod.GET)
    public String deleteEntreprise(Long id) {
        cvRepository.delete(cvRepository.getOne(id));
        return "redirect:/jobboard/cv";
    }

    @RequestMapping(value = "/jobbord/presentation")
    public String pres(){
        return "pres/list";
    }


    @GetMapping(value = "/download/file")
    public String main() {
        return "cv/list";
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/jobboard/cv";
    }
}

