package com.jobboard.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Candidature {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Etudiant etudiant;
    @ManyToOne
    OffreEmploi offreEmploi;
    @ManyToOne
    Cv cv;

    public Candidature() {
    }

    public Candidature(Etudiant etudiant, OffreEmploi offreEmploi, Cv cv) {
        this.etudiant = etudiant;
        this.offreEmploi = offreEmploi;
        this.cv = cv;
    }
}
