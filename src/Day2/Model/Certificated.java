package Day2.Model;

import java.util.Date;

public class Certificated {
    private String CertificatedID;
    private String CertificateName;
    private String CertificateRank;
    private String CertificatedDate;

    public Certificated() {}

    public Certificated(String certificatedID, String certificateName, String certificateRank, String certificatedDate) {
        CertificatedID = certificatedID;
        CertificateName = certificateName;
        CertificateRank = certificateRank;
        CertificatedDate = certificatedDate;
    }

    public String getCertificatedID() {
        return CertificatedID;
    }

    public void setCertificatedID(String certificatedID) {
        CertificatedID = certificatedID;
    }

    public String getCertificateName() {
        return CertificateName;
    }

    public void setCertificateName(String certificateName) {
        CertificateName = certificateName;
    }

    public String getCertificateRank() {
        return CertificateRank;
    }

    public void setCertificateRank(String certificateRank) {
        CertificateRank = certificateRank;
    }

    public String getCertificatedDate() {
        return CertificatedDate;
    }

    public void setCertificatedDate(String certificatedDate) {
        CertificatedDate = certificatedDate;
    }

    public void showInfo(){
//        System.out.println("Certificated ID: " + CertificatedID);
        System.out.println("Certificated Name: " + CertificateName);
        System.out.println("Certificated Rank: " + CertificateRank);
        System.out.println("Certificated Date: " + CertificatedDate);
    }
}
