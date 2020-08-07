package com.jobboard.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Cv {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String specialite;
    String diplome;
    String connaissances;
    int cv_documents;
    @OneToOne
    Etudiant etudiant;
    @OneToMany(mappedBy = "cv")
    List<Candidature> candidatures;
    public Cv() {
    }

    public Cv(String specialite, String diplome, String connaissances, int cv_documents, Etudiant etudiant) {
        this.specialite = specialite;
        this.diplome = diplome;
        this.connaissances = connaissances;
        this.cv_documents = cv_documents;
        this.etudiant = etudiant;
    }
}
