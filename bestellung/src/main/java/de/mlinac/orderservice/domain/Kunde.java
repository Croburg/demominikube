package de.mlinac.orderservice.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Kunde {

    private int kundeId;
    private String vorname;
    private String nachname;
    private String email;


    public Kunde(){
    }

    public Kunde(int kundeId, String vorname, String nachname, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
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
                ", email='" + email + '\'' +
                '}';
    }
}
