package com.application_web_gestion.service;

import com.application_web_gestion.classe.Resultat;
import com.application_web_gestion.classe.Etudiant;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.FileOutputStream;
import java.util.List;

public class RelevePdfService {

    public void genererRelevePdf(Session session, Long etudiantId, String filePath, double moyenne) {
        List<Resultat> resultats = getResultatsParEtudiant(session, etudiantId);

        if (resultats == null || resultats.isEmpty()) {
            System.out.println("[INFO] Aucun résultat trouvé pour l'étudiant ID : " + etudiantId);
            return;
        }

        Etudiant etudiant = resultats.get(0).getEtudiant();

        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Ajout des informations de base
            document.add(new Paragraph("Relevé de notes").setBold().setFontSize(16));
            document.add(new Paragraph("Nom de l'étudiant : " + etudiant.getNom() + " " + etudiant.getPrenom()));
            document.add(new Paragraph("Matricule : " + etudiant.getId()));
            document.add(new Paragraph("Moyenne générale : " + String.format("%.2f", moyenne)));
            document.add(new Paragraph("\n"));

            // Tableau des résultats
            float[] columnWidths = {200F, 100F};
            Table table = new Table(columnWidths);
            table.addHeaderCell(new Cell().add(new Paragraph("Cours").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Note").setBold()));

            for (Resultat resultat : resultats) {
                table.addCell(new Cell().add(new Paragraph(resultat.getCours().getNom())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(resultat.getNote()))));
            }

            document.add(table);
            document.close();
            System.out.println("[INFO] PDF généré avec succès : " + filePath);
        } catch (Exception e) {
            System.err.println("[ERROR] Erreur lors de la génération du PDF : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Resultat> getResultatsParEtudiant(Session session, Long etudiantId) {
        System.out.println("[DEBUG] getResultatsParEtudiant - Étudiant ID : " + etudiantId);
        String hql = "FROM Resultat r WHERE r.etudiant.id = :etudiantId";

        System.out.println("[DEBUG] HQL : " + hql);
        try {
            Query<Resultat> query = session.createQuery(hql, Resultat.class);
            query.setParameter("etudiantId", etudiantId);

            List<Resultat> resultats = query.getResultList();
            System.out.println("[DEBUG] Nombre de résultats trouvés : " + resultats.size());
            return resultats;
        } catch (Exception e) {
            System.err.println("[ERROR] getResultatsParEtudiant - Exception : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
