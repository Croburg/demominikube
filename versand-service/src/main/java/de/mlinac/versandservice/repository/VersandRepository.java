package de.mlinac.versandservice.repository;

import de.mlinac.versandservice.domain.Lieferung;

import de.mlinac.versandservice.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersandRepository extends JpaRepository<Lieferung, Integer> {

    Lieferung findByBestellnummer(String bestellnummer);

    List<Lieferung> findByStatus(Status status);
}
