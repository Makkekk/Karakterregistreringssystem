package Opgave8;

import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OpgaveC {
    public static void main(String[] args) {

List<Studerende> studerendeList = new ArrayList<>();

String sql = "SELECT navn,studieID,termin from EksamensAfviklingsStuderende ";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Connection minConnection;
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://LAPTOP-F9FN58TJ\\SQLExpress;databaseName=tidsregistrering2 klasseeks;user=sa;password=123456;");

            Statement stmt = minConnection.createStatement();
            ResultSet res stmt.executeQuery(sql)) {

                System.out.println("Henter studerende...");
            }


            System.out.println("Indtast navnet på eksamen");
            String eksamenNavn = reader.readLine().trim();

            System.out.println("Indtast termin");
            String termin = reader.readLine().trim();

            if (eksamenNavn.isEmpty() || termin.isEmpty()) {
                System.out.println("Error: Eksamensnavn og termin skal være udfyldt");
                return;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
