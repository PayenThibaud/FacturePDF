package com.facturation.factureservice.dto;

public class FactureDto {

    // Attributs
    private String clientNom;
    private String date;
    private double montant;
    private String clienttelephone;
    private String clientemail;
    private String clientadresse;



    // Constructeurs
    public FactureDto() {}

    public FactureDto(String clientNom, String date, String description, double montant, String Clienttelephone, String Clientemail, String Clientadresse) {
        this.clientNom = clientNom;
        this.date = date;
        this.montant = montant;
        this.clienttelephone = Clienttelephone;
        this.clientemail = Clientemail;
        this.clientadresse = Clientadresse;
    }

    // Getters / Setters
    public String getClientNom() {
        return clientNom;
    }
    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getClienttelephone() {
        return clienttelephone;
    }
    public void setClienttelephone(String telephone) {
        this.clienttelephone = telephone;
    }
    public String getClientemail() {
        return clientemail;
    }
    public void setClientemail(String email) {
        this.clientemail = email;
    }
    public String getClientadresse() {
        return clientadresse;
    }
    public void setClientadresse(String adresse) {
        this.clientadresse = adresse;
    }
}
