package de.mlinac.versandservice.domain;


import javax.persistence.*;

@Entity
public class Lieferposition {

    @Id
    @GeneratedValue
    private int id;

    private int positionsnummer;

    @Embedded
    private Artikel artikel;

    private int menge;

    public Lieferposition() {
    }

    public Lieferposition(Artikel artikel, int menge) {
        this.artikel = artikel;
        this.menge = menge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPositionsnummer() {
        return positionsnummer;
    }

    public void setPositionsnummer(int positionsnummer) {
        this.positionsnummer = positionsnummer;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}
