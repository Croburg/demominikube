package de.mlinac.zahlungservice.repository;

import de.mlinac.zahlungservice.domain.Status;
import de.mlinac.zahlungservice.domain.Zahlung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZahlungRepository extends JpaRepository<Zahlung, Integer> {

    Zahlung findByBestellnummer(String bestellnummer);

    List<Zahlung> findByStatus(Status status);

}
