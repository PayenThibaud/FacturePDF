package com.facturation.factureservice.dto;

public class FactureDto {
    private String clientNom;
    private String date;
    private String description;
    private double montant;

    // Constructeurs
    public FactureDto() {}

    public FactureDto(String clientNom, String date, String description, double montant) {
        this.clientNom = clientNom;
        this.date = date;
        this.description = description;
        this.montant = montant;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
}
