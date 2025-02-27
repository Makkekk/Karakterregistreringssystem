package Opgave8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OpgaveC {
    public static void main(String[] args) {

        // Opretter en liste til at gemme studerende
        List<Studerende> studerendeList = new ArrayList<>();

        // JDBC connection info
        String url = "jdbc:sqlserver://LAPTOP-F9FN58TJ\\SQLExpress;databaseName=Karakterregistreringssystem;user=sa;password=123456;";

        // Bruger BufferedReader til at tage input
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Indtast navnet på eksamen:");
            String eksamenNavn = reader.readLine().trim();

            System.out.println("Indtast termin:");
            String termin = reader.readLine().trim();

            // Tjekker om input er gyldigt
            if (eksamenNavn.isEmpty() || termin.isEmpty()) {
                System.out.println("Fejl: Eksamensnavn og termin skal være udfyldt.");
                return;
            }

            // SQL-query med parameterisering for at undgå SQL Injection
            String sql = "SELECT s.navn, s.studieID, eb.karakter " +
                    "FROM EksamensBesvarelse eb " +
                    "JOIN Studerende s ON eb.studieID = s.studieID " +
                    "JOIN Eksamensafvikling ea ON eb.EksamensAfviklingsID = ea.EksamensAfviklingsID " +
                    "JOIN Eksamen e ON ea.eksamensID = e.eksamensID " +
                    "WHERE e.eksamensNavn = ? AND ea.termin = ?";

            // Opretter forbindelsen
            try (Connection connection = DriverManager.getConnection(url);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {

                // Indsætter brugerinput i SQL-query
                pstmt.setString(1, eksamenNavn);
                pstmt.setString(2, termin);

                // Udfører query og henter resultater
                try (ResultSet res = pstmt.executeQuery()) {
                    boolean found = false;
                    System.out.println("\nStuderende, der deltog i eksamen '" + eksamenNavn + "' i termin '" + termin + "':");

                    while (res.next()) {
                        String navn = res.getString("navn");
                        int studieID = res.getInt("studieID");
                        int karakter = res.getInt("karakter");

                        studerendeList.add(new Studerende(navn, studieID, karakter));
                        System.out.println("Navn: " + navn + ", StudieID: " + studieID + ", Karakter: " + karakter);
                        found = true;
                    }

                    if (!found) {
                        System.out.println("Ingen studerende fundet for denne eksamen og termin.");
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Databasefejl: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Inputfejl: " + e.getMessage());
        }
    }
}

