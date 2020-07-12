package de.mlinac.orderservice.repository;

import de.mlinac.orderservice.domain.Bestellung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellungRepository extends JpaRepository<Bestellung, Integer> {

    /*
    List<Bestellung> findByBestellnummer(String bestellnummer);
    */
    Bestellung findByBestellnummer(String bestellnummer);

    boolean existsByBestellnummer(String bestellnummer);


    Bestellung findById(int id);

}
