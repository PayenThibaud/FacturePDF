package com.facturation.factureservice.controller;

import com.facturation.factureservice.dto.FactureDto;
import com.facturation.factureservice.service.FactureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @PostMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> createFacture(@RequestBody FactureDto facture) {
        try {
            System.out.println("📥 Facture reçue : " + facture);

            byte[] pdfBytes = factureService.genererFacturePdf(facture);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "facture.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            System.err.println("🛑 Erreur pendant la génération du PDF");
            e.printStackTrace(); // 🔥 Ceci affiche la cause réelle dans ta console
            return ResponseEntity.status(500)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(("Erreur serveur : " + e.getMessage()).getBytes());
        }
    }



}
