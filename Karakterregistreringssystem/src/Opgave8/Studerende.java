package Opgave8;

public class Studerende {
    private int studieID;
    private String navn;
    private String StudieStatus;
    private String tlfNr;
    private int uddannelsesID;

    public Studerende(int studieID, String navn, String studieStatus, String tlfNr, int uddannelsesID) {
        this.studieID = studieID;
        this.navn = navn;
        this.StudieStatus = studieStatus;
        this.tlfNr = tlfNr;
        this.uddannelsesID = uddannelsesID;
    }

    @Override
    public String toString() {
        return "Studerende{" +
                "studieID=" + studieID +
                ", navn='" + navn + '\'' +
                ", StudieStatus='" + StudieStatus + '\'' +
                ", tlfNr='" + tlfNr + '\'' +
                ", uddannelsesID=" + uddannelsesID +
                '}';
    }
}
