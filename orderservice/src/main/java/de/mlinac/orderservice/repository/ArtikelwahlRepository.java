package de.mlinac.orderservice.repository;

import de.mlinac.orderservice.domain.Artikelwahl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelwahlRepository extends JpaRepository <Artikelwahl, Integer> {
}
