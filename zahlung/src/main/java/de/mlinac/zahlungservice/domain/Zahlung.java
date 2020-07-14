package de.mlinac.zahlungservice.domain;

import javax.persistence.*;

@Entity
public class Zahlung {

    @Id
    @GeneratedValue
    private int id;

    private String bestellnummer;

    @Embedded
    private Kunde kunde;

    @Embedded
    private Adresse rechnungsadresse;

    private double gesamtwert;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Zahlung() {
    }

    public Zahlung(String bestellnummer, Kunde kunde, Adresse rechnungsadresse, double gesamtwert) {
        this.bestellnummer = bestellnummer;
        this.kunde = kunde;
        this.rechnungsadresse = rechnungsadresse;
        this.gesamtwert = gesamtwert;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Adresse getRechnungsadresse() {
        return rechnungsadresse;
    }

    public void setRechnungsadresse(Adresse rechnungsadresse) {
        this.rechnungsadresse = rechnungsadresse;
    }

    public double getGesamtwert() {
        return gesamtwert;
    }

    public void setGesamtwert(double gesamtwert) {
        this.gesamtwert = gesamtwert;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Zahlung{" +
                "id=" + id +
                ", bestellnummer='" + bestellnummer + '\'' +
                ", kunde=" + kunde +
                ", rechnungsadresse=" + rechnungsadresse +
                ", gesamtwert=" + gesamtwert +
                '}';
    }
}
