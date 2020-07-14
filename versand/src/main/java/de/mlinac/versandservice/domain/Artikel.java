package de.mlinac.versandservice.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Artikel {

    private int artikelId;
    private String beschreibung;

    public Artikel() {
    }

    public Artikel(int artikelId, String beschreibung) {
        this.artikelId = artikelId;
        this.beschreibung = beschreibung;
    }

    public int getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(int artikelId) {
        this.artikelId = artikelId;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
