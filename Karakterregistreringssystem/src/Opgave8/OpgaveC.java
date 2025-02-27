package Opgave8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OpgaveC {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Connection minConnection;
            minConnection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost;databaseName=Karakterregistreringssystem;user=sa;password=dockerStrongPwd123;");

            // Læser input fra brugeren
            System.out.println("Indtast navn på eksamen: ");
            String eksamensNavn = reader.readLine().trim();

            System.out.println("Indtast terminen for eksamensafvikling (f.eks. 'S2025'): ");
            String terminDato = reader.readLine().trim();

            String sql = "INSERT INTO EksamensAfvikling (termin, startsDato, slutsDato, eksamensID) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = minConnection.prepareStatement(sql);
            stmt.setString(1, eksamensNavn);
            stmt.setString(2, terminDato);


            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Eksamensafvikling oprettet succesfuldt!");
            }
            stmt.close();

        } catch (SQLException e) {
            System.out.println("fejl:  " + e.getMessage());
            System.out.println("fejl:  " + e.getErrorCode());

            if (e.getErrorCode() == 547) {
                if (e.getMessage().contains("check_start_slut")) {
                    System.out.println("Slutdatoen skal lægge efter startsdato");
                } else if (e.getMessage().contains("ugyldigID")) {
                    System.out.println("Ugyldig eksamens id");
                }
            }


        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }
}

