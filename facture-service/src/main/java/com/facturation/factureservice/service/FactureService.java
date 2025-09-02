package com.facturation.factureservice.service;

import com.facturation.factureservice.dto.FactureDto;
import com.facturation.factureservice.utils.RandomNumero;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
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

        // Mise en forme de la date
        String formattedDate = "";
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate date = LocalDate.parse(facture.getDate(), inputFormatter);
            formattedDate = date.format(outputFormatter);
        } catch (Exception e) {
            formattedDate = "Date invalide";
            e.printStackTrace();
        }

        // Table d'en-tête avec deux colonnes
        float[] headerColWidths = {1, 1};
        Table header = new Table(headerColWidths).useAllAvailableWidth();

// Cellule gauche : Mathis Payen, alignée à gauche, sans bordure
        header.addCell(
                new Cell()
                        .add(new Paragraph("Mathis Payen").setBold()
                                .setFontSize(14))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.LEFT)
        );

// Cellule droite : Note d'honoraire, alignée à droite, sans bordure
        header.addCell(
                new Cell()
                        .add(new Paragraph("NOTE D'HONORAIRES")
                                .setBold()
                                .setFontSize(16))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT)
        );

// Ajout au document
        document.add(header);


        // Deuxième ligne de l'en-tête : gauche = "Ostéopathe D.O.", droite = date
        float[] header2ColWidths = {1, 1};
        Table header2 = new Table(header2ColWidths).useAllAvailableWidth();

        header2.addCell(
                new Cell()
                        .add(new Paragraph("Ostéopathe D.O.").setItalic())
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.LEFT)
        );

        header2.addCell(
                new Cell()
                        .add(new Paragraph(formattedDate))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT)
        );

// Ajout au document
        document.add(header2);

        // Troisieme ligne de l'en-tête : gauche = "adresse", droite = N° de facture
        float[] header3ColWidths = {1, 1};
        Table header3 = new Table(header3ColWidths).useAllAvailableWidth();

        header3.addCell(
                new Cell()
                        .add(new Paragraph("4 rue Emile BASLY"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.LEFT)
        );

        header3.addCell(
                new Cell()
                        .add(new Paragraph("n° " + RandomNumero.genererRandomNumero()))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT)
        );

// Ajout au document
        document.add(header3);



        // 4eme ligne adresse ville
        document.add(new Paragraph("62410 MEURCHIN"));

        // 5eme ligne Teléphone
        document.add(new Paragraph("Tél : 07.69.57.15.55"));

        // 5eme ligne adresse mail
        document.add(new Paragraph("Mathispayen@gmail.com"));

        // 6eme ligne RPPS
        document.add(new Paragraph("Identifiant RPPS : 10111107180"));

        document.add(new Paragraph("\n"));


        // ligne Patient :
        document.add(new Paragraph("Patient :")
                .setPaddingRight(20)
                .setTextAlignment(TextAlignment.RIGHT));


        // 7eme ligne nom client
        document.add(new Paragraph(facture.getClientNom())
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold());

        // 8eme ligne Adresse client
        document.add(new Paragraph(facture.getClientadresse())
                .setTextAlignment(TextAlignment.RIGHT));

        // 9eme ligne teléphone client
        document.add(new Paragraph("Tél: " + facture.getClienttelephone())
                .setTextAlignment(TextAlignment.RIGHT));

        // 10eme ligne EMAIL client
        document.add(new Paragraph(facture.getClientemail())
                .setTextAlignment(TextAlignment.RIGHT));


        document.add(new Paragraph("\n"));

        // Tableau facture
        float[] columnWidths = {7, 3};
        Table table = new Table(columnWidths).useAllAvailableWidth();

        // Couleur de fond pour les en-têtes
        Color bleuPale = new DeviceRgb(173, 216, 230);

        // En-têtes
        table.addCell(
                new Cell()
                        .add(new Paragraph("Prestations").setBold())
                        .setBackgroundColor(bleuPale)
                        .setTextAlignment(TextAlignment.LEFT)
        );
        table.addCell(
                new Cell()
                        .add(new Paragraph("Montant").setBold().setTextAlignment(TextAlignment.RIGHT))
                        .setBackgroundColor(bleuPale)
        );

        // Ligne de prestation
        table.addCell(
                new Cell()
                        .add(new Paragraph("1 Séance d'ostéopathie"))
                        .setTextAlignment(TextAlignment.LEFT)
        );
        table.addCell(
                new Cell()
                        .add(new Paragraph(facture.getMontant() + " €").setTextAlignment(TextAlignment.RIGHT))
        );


        // Ligne total
        table.addCell(
                new Cell()
                        .add(new Paragraph("Total").setBold())
                        .setPaddingRight(6)
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT)
        );
        table.addCell(new Cell().add(new Paragraph(String.format("%.2f €", facture.getMontant())).setBold().setTextAlignment(TextAlignment.RIGHT)));

        // Ajout au document
        document.add(table);

        // acquitté
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Acquitté le " + formattedDate));

        // nom du praticien
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Mathis Payen")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold());

        // signature
        InputStream is = getClass().getClassLoader().getResourceAsStream("static/signMathis.png");
        if (is != null) {
            ImageData imageData = ImageDataFactory.create(is.readAllBytes());
            Image signature = new Image(imageData);
            signature.setWidth(100);
            document.add(signature);
        } else {
            System.out.println("⚠️ Image de signature non trouvée");
        }



        // Fermeture du document
        document.close();

        return baos.toByteArray();
    }
}
