package de.mlinac.bonuspunkte.model;


import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Kunde {

    private int kundeId;
    private String vorname;
    private String nachname;

    public Kunde(int kundeId, String vorname, String nachname) {
        this.kundeId = kundeId;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Kunde() {
    }

    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kundeId=" + kundeId +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                '}';
    }
}
