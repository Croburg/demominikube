package de.mlinac.orderservice.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Bestellung {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String bestellnummer = UUID.randomUUID().toString();
    private Kunde kunde;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "strasse", column = @Column(name = "RECHNUNG_STRASSE")),
                            @AttributeOverride(name = "plz", column = @Column(name = "RECHNUNG_PLZ")),
                            @AttributeOverride(name = "stadt", column = @Column(name = "RECHNUNG_STADT")) })
    private Adresse rechnungsadresse;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "strasse", column = @Column(name = "LIEFERUNG_STRASSE")),
                            @AttributeOverride(name = "plz", column = @Column(name = "LIEFERUNG_PLZ")),
                            @AttributeOverride(name = "stadt", column = @Column(name = "LIEFERUNG_STADT")) })
    private Adresse lieferadresse;

    private String zahlungsmethode;
    private double gesamtwert;
    private int bonuspunkte;

    @Enumerated(EnumType.ORDINAL)
    private Status gesamtstatus;
    @Enumerated(EnumType.ORDINAL)
    private Status zahlungstatus;
    @Enumerated(EnumType.ORDINAL)
    private Status lieferungstatus;
    @Enumerated(EnumType.ORDINAL)
    private Status bonuspunktestatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Artikelwahl> artikelwahl;

    public Bestellung(Kunde kunde, Adresse rechnungsadresse, Adresse lieferadresse, String zahlungsmethode, int bonuspunkte, List<Artikelwahl> artikelwahl) {
        this.kunde = kunde;
        this.rechnungsadresse = rechnungsadresse;
        this.lieferadresse = lieferadresse;
        this.zahlungsmethode = zahlungsmethode;
        this.bonuspunkte = bonuspunkte;
        this.artikelwahl = artikelwahl;

        for(Artikelwahl artikelwahlEinheit : this.artikelwahl){
            this.gesamtwert =+ this.gesamtwert + (artikelwahlEinheit.getMenge() * artikelwahlEinheit.getArtikel().getPreis());};

        }

    public Bestellung() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Adresse getLieferadresse() {
        return lieferadresse;
    }

    public void setLieferadresse(Adresse lieferadresse) {
        this.lieferadresse = lieferadresse;
    }

    public String getZahlungsmethode() {
        return zahlungsmethode;
    }

    public void setZahlungsmethode(String zahlungsmethode) {
        this.zahlungsmethode = zahlungsmethode;
    }

    public int getBonuspunkte() {
        return bonuspunkte;
    }

    public void setBonuspunkte(int bonuspunkte) {
        this.bonuspunkte = bonuspunkte;
    }

    public List<Artikelwahl> getArtikelwahl() {
        return artikelwahl;
    }

    public void setArtikelwahl(List<Artikelwahl> artikelwahl) {
        this.artikelwahl = artikelwahl;
    }

    public String getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    public double getGesamtwert() {
        return gesamtwert;
    }

    public void setGesamtwert(double gesamtwert) {
        this.gesamtwert = gesamtwert;
    }

    public Status getGesamtstatus() {
        return gesamtstatus;
    }

    public void setGesamtstatus(Status gesamtstatus) {
        this.gesamtstatus = gesamtstatus;
    }

    public Status getZahlungstatus() {
        return zahlungstatus;
    }

    public void setZahlungstatus(Status zahlungstatus) {
        this.zahlungstatus = zahlungstatus;
    }

    public Status getLieferungstatus() {
        return lieferungstatus;
    }

    public void setLieferungstatus(Status lieferungstatus) {
        this.lieferungstatus = lieferungstatus;
    }

    public Status getBonuspunktestatus() {
        return bonuspunktestatus;
    }

    public void setBonuspunktestatus(Status bonuspunktestatus) {
        this.bonuspunktestatus = bonuspunktestatus;
    }

    @Transient
    public void calculateGesamtwert(){
        for(Artikelwahl artikelwahlEinehit : this.artikelwahl){
            this.gesamtwert =+ this.gesamtwert + (artikelwahlEinehit.getMenge() * artikelwahlEinehit.getArtikel().getPreis());};
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "id=" + id +
                ", bestellnummer='" + bestellnummer + '\'' +
                ", kunde=" + kunde +
                ", rechnungsadresse=" + rechnungsadresse +
                ", lieferadresse=" + lieferadresse +
                ", zahlungsmethode='" + zahlungsmethode + '\'' +
                ", gesamtwert=" + gesamtwert +
                ", bonuspunkte=" + bonuspunkte +
                ", gesamtstatus=" + gesamtstatus +
                ", zahlungstatus=" + zahlungstatus +
                ", lieferungstatus=" + lieferungstatus +
                ", bonuspunktestatus=" + bonuspunktestatus +
                ", artikelwahl=" + artikelwahl +
                '}';
    }
}
