package com.example.mini_project.Repository;

import com.example.mini_project.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonneRepository <T extends Personne> extends JpaRepository<T, Integer> {
}
