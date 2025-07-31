package com.facturation.factureservice.service;

import com.facturation.factureservice.dto.FactureDto;
import com.facturation.factureservice.utils.RandomNumero;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class FactureService {

    public byte[] genererFacturePdf(FactureDto facture) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Titre
        Paragraph title = new Paragraph("Facture n° " + RandomNumero.genererRandomNumero())
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Infos client
        document.add(new Paragraph("Client : " + facture.getClientNom()));
        String dateString = facture.getDate();
        LocalDate date = LocalDate.parse(dateString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        String dateFormater = date.format(formatter);

        document.add(new Paragraph("Date : " + dateFormater));
        document.add(new Paragraph("\n"));

        // Tableau facture
        float[] columnWidths = {200F, 200F};
        Table table = new Table(columnWidths);

        table.addCell("Description");
        table.addCell(facture.getDescription());

        table.addCell("Montant");
        table.addCell(String.format("%.2f €", facture.getMontant()));

        document.add(table);

        // Total
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Total : " + String.format("%.2f €", facture.getMontant()))
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT));

        document.close();

        return baos.toByteArray();
    }
}
