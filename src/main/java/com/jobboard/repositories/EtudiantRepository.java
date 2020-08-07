package com.jobboard.repositories;

import com.jobboard.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {

    @Query("select e from Etudiant e where e.email=:email")
    public Etudiant getEtudiantByEmail(@Param("email") String em);
}
