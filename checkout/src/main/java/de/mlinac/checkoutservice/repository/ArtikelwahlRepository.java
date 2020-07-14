package de.mlinac.checkoutservice.repository;

import de.mlinac.checkoutservice.domain.Artikelwahl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelwahlRepository extends JpaRepository <Artikelwahl, Integer> {
}
