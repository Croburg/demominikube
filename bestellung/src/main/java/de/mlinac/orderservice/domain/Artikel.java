package de.mlinac.orderservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Artikel {

    @Id
    @GeneratedValue
    private int id;

    private int artikelID;
    private String beschreibung;
    private double preis;

    public Artikel(int artikelID, String beschreibung, double preis) {
        this.artikelID = artikelID;
        this.beschreibung = beschreibung;
        this.preis = preis;
    }

    public Artikel() {
    }

    public int getArtikelID() {
        return artikelID;
    }

    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "id=" + id +
                ", artikelID=" + artikelID +
                ", beschreibung='" + beschreibung + '\'' +
                ", preis=" + preis +
                '}';
    }
}

