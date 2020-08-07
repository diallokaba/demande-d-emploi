package com.jobboard.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
public class Entreprise extends User{

    String nomRec;
    String prenomRec;
    String nomEnt;
    String sectActivite;
    String region;
    String telephone;
    @OneToMany(mappedBy = "entreprise")
    List<OffreEmploi> offreEmplois;

    public Entreprise() {
    }

    public Entreprise(String email, String password, int etat, String nomRec, String prenomRec, String nomEnt, String sectActivite, String region, String telephone) {
        super(email, password, etat);
        this.nomRec = nomRec;
        this.prenomRec = prenomRec;
        this.nomEnt = nomEnt;
        this.sectActivite = sectActivite;
        this.region = region;
        this.telephone = telephone;
    }
}
