package Opgave8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OpgaveB {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://LocalHost\\SQLExpress;databaseName=SQL Obligatorisk opgave 25-02-2025;user=sa;password=Davmeddig1;");

            // Læser input fra brugeren
            System.out.println("Indtast termin for eksamensafvikling (f.eks. '2025-01'): ");
            String termin = reader.readLine().trim();

            System.out.println("Indtast startdato for eksamensafvikling (f.eks. '2025-01-10'): ");
            String startdato = reader.readLine().trim();

            System.out.println("Indtast slutdato for eksamensafvikling (f.eks. '2025-01-15'): ");
            String slutdato = reader.readLine().trim();

            System.out.println("Indtast eksamensID for eksamenen, der skal afvikles: ");
            int eksamensID = Integer.parseInt(reader.readLine().trim());

            String sql = "INSERT INTO EksamensAfvikling (termin, startsDato, slutsDato, eksamensID) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = minConnection.prepareStatement(sql);
            stmt.setString(1, termin);
            stmt.setString(2, startdato);
            stmt.setString(3, slutdato);
            stmt.setInt(4, eksamensID);

      
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Eksamensafvikling oprettet succesfuldt!");
            }
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
            System.out.println("Fejl: " + e.getErrorCode());
            if (e.getErrorCode() == 547){
                if (e.getMessage().contains("check_start_slut")){
                    System.out.println("Slut dato skal være efter start dato");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}