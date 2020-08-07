package com.jobboard.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable {

    @Id
    @Column(length = 64)
    String email;
    String password;
    int etat;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "email", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "nom", nullable = false, updatable = false)})
    List<Roles> roles;

    public User() {
    }

    public User(String email, String password, int etat) {
        this.email = email;
        this.password = password;
        this.etat = etat;
    }
}
