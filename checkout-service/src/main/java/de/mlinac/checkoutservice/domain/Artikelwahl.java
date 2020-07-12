package de.mlinac.checkoutservice.domain;


import javax.persistence.*;

@Entity
public class Artikelwahl {

    @Id
    @GeneratedValue
    @Column(name="artikelwahl")
    private int id;

    @ManyToOne
    private Artikel artikel;
    private int Menge;

    public Artikelwahl() {
    }

    public Artikelwahl(Artikel artikel, int menge) {
        this.artikel = artikel;
        Menge = menge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public int getMenge() {
        return Menge;
    }

    public void setMenge(int menge) {
        Menge = menge;
    }
}
