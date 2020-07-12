package de.mlinac.bonuspunkte.repository;

import de.mlinac.bonuspunkte.model.Bestellung;
import de.mlinac.bonuspunkte.model.PunkteKonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunkteRepository extends JpaRepository<PunkteKonto, Integer> {

    PunkteKonto existsByKundeId(int kundeId);

    PunkteKonto findByKundeId(int kundeId);

}
