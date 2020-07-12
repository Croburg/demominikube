package de.mlinac.checkoutservice.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Session {

    @Id
    @GeneratedValue
    private int id;

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
    private int bonuspunkte;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Artikelwahl> artikelwahl;

    public Session(Kunde kunde, Adresse rechnungsadresse, Adresse lieferadresse, String zahlungsmethode, int bonuspunkte, List<Artikelwahl> artikelwahl) {
        this.kunde = kunde;
        this.rechnungsadresse = rechnungsadresse;
        this.lieferadresse = lieferadresse;
        this.zahlungsmethode = zahlungsmethode;
        this.bonuspunkte = bonuspunkte;
        this.artikelwahl = artikelwahl;
    }

    public Session() {
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
}
