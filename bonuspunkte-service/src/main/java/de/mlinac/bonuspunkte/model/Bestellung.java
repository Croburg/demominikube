package de.mlinac.bonuspunkte.model;

public class Bestellung {

    private String bestellnummer;
    private int bonuspunkte;
    private Kunde kunde;

    public Bestellung(String bestellnummer, int bonuspunkte, Kunde kunde) {
        this.bestellnummer = bestellnummer;
        this.bonuspunkte = bonuspunkte;
        this.kunde = kunde;
    }

    public Bestellung() {
    }

    public String getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    public int getBonuspunkte() {
        return bonuspunkte;
    }

    public void setBonusunkte(int bonuspunkte) {
        this.bonuspunkte = bonuspunkte;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "bestellnummer='" + bestellnummer + '\'' +
                ", bonuspunkte=" + bonuspunkte +
                ", kunde=" + kunde +
                '}';
    }
}
