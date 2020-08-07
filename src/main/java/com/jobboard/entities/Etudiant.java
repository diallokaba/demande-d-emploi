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
public class Etudiant extends User{

    String nom;
    String prenom;
    String adresse;
    String niveau;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date dateNaissance;
    String telephone;
    @OneToMany(mappedBy = "etudiant")
    List<Candidature> candidatures;

    public Etudiant() {
    }

    public Etudiant(String email, String password, int etat, String nom, String prenom, String adresse, String niveau, Date dateNaissance, String telephone) {
        super(email, password, etat);
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.niveau = niveau;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        String dateString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateString = sdf.format(dateNaissance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }
}
