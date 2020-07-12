package de.mlinac.checkoutservice.repository;

import de.mlinac.checkoutservice.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository extends JpaRepository <Artikel, Integer> {
}
