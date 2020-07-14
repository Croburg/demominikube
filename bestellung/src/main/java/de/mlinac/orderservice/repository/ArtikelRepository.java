package de.mlinac.orderservice.repository;


import de.mlinac.orderservice.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository extends JpaRepository <Artikel, Integer> {
}
