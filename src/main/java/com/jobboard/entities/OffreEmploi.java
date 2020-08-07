package com.jobboard.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class OffreEmploi {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String poste;
    String connaissanceTechnique;
    String region;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date dateOffre;
    String diplome;
    @OneToMany(mappedBy = "offreEmploi")
    List<Candidature> candidatures;
    @ManyToOne
    Entreprise entreprise;

    public OffreEmploi() {
    }

    public OffreEmploi(Long id, String poste, String connaissanceTechnique, String region, Date dateOffre, String diplome, Entreprise entreprise) {
        this.id = id;
        this.poste = poste;
        this.connaissanceTechnique = connaissanceTechnique;
        this.region = region;
        this.dateOffre = dateOffre;
        this.diplome = diplome;
        this.entreprise = entreprise;
    }

    @Override
    public String toString() {
        String dateString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateString = sdf.format(dateOffre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }
}
