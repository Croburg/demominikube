package de.mlinac.versandservice.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lieferung {

    @Id
    @GeneratedValue
    private int lieferId;
    @Column(unique = true)
    private String bestellnummer;
    private String liefernummer;
    private String lieferdienst;

    @Embedded
    private Kunde kunde;

    @Embedded
    private Adresse lieferaddresse;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lieferposition> lieferposition;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Lieferung(String bestellnummer, String lieferdienst, Kunde kunde, Adresse lieferaddresse, List<Lieferposition> positionen) {
        this.bestellnummer = bestellnummer;
        this.lieferdienst = lieferdienst;
        this.kunde = kunde;
        this.lieferaddresse = lieferaddresse;
        this.lieferposition = positionen;
        for(Lieferposition lieferPosition : this.lieferposition){
            lieferPosition.setPositionsnummer(this.lieferposition.indexOf(lieferPosition) + 1);
        }
    }

    public Lieferung() {
    }

    public String getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    public int getLieferId() {
        return lieferId;
    }

    public void setLieferId(int lieferId) {
        this.lieferId = lieferId;
    }

    public String getLiefernummer() {
        return liefernummer;
    }

    public void setLiefernummer(String liefernummer) {
        this.liefernummer = liefernummer;
    }

    public String getLieferdienst() {
        return lieferdienst;
    }

    public void setLieferdienst(String lieferdienst) {
        this.lieferdienst = lieferdienst;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Adresse getLieferaddresse() {
        return lieferaddresse;
    }

    public void setLieferaddresse(Adresse lieferaddresse) {
        this.lieferaddresse = lieferaddresse;
    }

    public List<Lieferposition> getLieferposition() {
        return lieferposition;
    }

    public void setLieferposition(List<Lieferposition> lieferposition) {
        this.lieferposition = lieferposition;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lieferung{" +
                "lieferId=" + lieferId +
                ", bestellnummer='" + bestellnummer + '\'' +
                ", liefernummer='" + liefernummer + '\'' +
                ", lieferdienst='" + lieferdienst + '\'' +
                ", kunde=" + kunde +
                ", lieferaddresse=" + lieferaddresse +
                ", lieferposition=" + lieferposition +
                ", status=" + status +
                '}';
    }
}
