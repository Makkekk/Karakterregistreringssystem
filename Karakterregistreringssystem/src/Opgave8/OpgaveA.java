package Opgave8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OpgaveA {
    public static void main(String[] args) {

        try { BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            {

                Connection minConnection;
                minConnection = DriverManager
                        .getConnection("jdbc:sqlserver://LocalHost\\SQLExpress;databaseName=SQL Obligatorisk opgave 25-02-2025;user=sa;password=Davmeddig1;");

                System.out.println("Indtast studieID: ");
                int studieID = Integer.parseInt(reader.readLine().trim());

                System.out.println("Enter eksamensAfviklingsID: ");
                int eksamensAfviklingsID = Integer.parseInt(reader.readLine().trim());

                System.out.println("Indtast karakter (-3, 0, 2, 4, 7, 10, 12): ");
                int karakter = Integer.parseInt(reader.readLine().trim());


                System.out.println("Indtast administrativ bedømmelse (SY, IM, IA eller tryk ENTER for ingen): ");
                String adminBedømmelse = reader.readLine().trim();
                if (adminBedømmelse.isEmpty()) {
                    adminBedømmelse = null;

                }
                String beståetIkkeBestået = (karakter >= 2) ? "JA" : "NEJ";

                String sql = "INSERT INTO Eksamensbesvarelse (karakter, administrativBedømmelse, BeståetIkkeBestået, studieID, EksamensAfviklingsID) " +
                        "VALUES (?,?,?,?,?)";

                try (PreparedStatement stmt = minConnection.prepareStatement(sql)) {
                    stmt.setInt(1, karakter);
                    stmt.setString(2, adminBedømmelse);
                    stmt.setString(3, beståetIkkeBestået);
                    stmt.setInt(4, studieID);
                    stmt.setInt(5, eksamensAfviklingsID);


                    int rowInserted = stmt.executeUpdate();
                    if (rowInserted > 0) {
                        System.out.println("Eksamensforsøg oprettet");
                    } else {
                        System.out.println("Ingen rækker blev indsat.");
                    }
                }

            }
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();

            System.out.println(" SQL Fejl: " + e.getMessage() + e.getErrorCode());
            if (errorCode == 547)
                System.out.println("Input fejl: Den indskrevne karakter skal være inde for skalaen");
        } catch (IOException e) {
            System.out.println("Input fejl: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig input! Indtast venligst tal, hvor det er nødvendigt.");
        }
    }
}
