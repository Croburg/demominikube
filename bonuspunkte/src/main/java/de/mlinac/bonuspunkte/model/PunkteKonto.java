package de.mlinac.bonuspunkte.model;

import javax.persistence.*;

@Entity
public class PunkteKonto {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private int kundeId;
    private int bonuspunkte;

    @Transient
    private String bestellnummer;

    public PunkteKonto() {
    }


    public PunkteKonto(int kundeId, int bonuspunkte, String bestellnummer) {
        this.kundeId = kundeId;
        this.bonuspunkte = bonuspunkte;
        this.bestellnummer = bestellnummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    public int getBonuspunkte() {
        return bonuspunkte;
    }

    public void setBonuspunkte(int bonuspunkte) {
        this.bonuspunkte = bonuspunkte;
    }

    public String getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    @Override
    public String toString() {
        return "PunkteKonto{" +
                "id=" + id +
                ", kundeId=" + kundeId +
                ", bonuspunkte=" + bonuspunkte +
                ", bestellnummer='" + bestellnummer + '\'' +
                '}';
    }
}
