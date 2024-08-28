package Day2.Model;

import Day2.Utils.CandidateConstant;

import java.util.List;
import java.util.Objects;

public abstract class Candidate {
    private String CandidateID;
    private String FullName;
    private String BirthDay;
    private String phone;
    private String email;
    private List<Certificated> certificatedList;

    private CandidateConstant Candidate_type;
    public static int Candidate_count = 0;

    public Candidate(){};

    public Candidate(String candidateID, String fullName, String birthDay, String phone, String email, List<Certificated> certificatedList) {
        CandidateID = candidateID;
        FullName = fullName;
        BirthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.certificatedList = certificatedList;
    }

    public String getCandidateID() {
        return CandidateID;
    }

    public void setCandidateID(String candidateID) {
        CandidateID = candidateID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CandidateConstant getCandidate_type() {
        return Candidate_type;
    }

    public void setCandidate_type(CandidateConstant candidate_type) {
        this.Candidate_type = candidate_type;
    }

    public List<Certificated> getCertificatedList() {
        return certificatedList;
    }

    public void setCertificatedList(List<Certificated> certificatedList) {
        this.certificatedList = certificatedList;
    }

    public void showCertificatedList(){
        if(certificatedList == null){return;}
        certificatedList.forEach(Certificated::showInfo);
    }

    public void getInfo(){
        System.out.println("------------------------");
        System.out.println("Candidate ID: " + CandidateID);
        System.out.println("Candidate Type: " + Candidate_type);
        System.out.println("Full Name: " + FullName);
        System.out.println("Birth Day: " + BirthDay);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Certificated List: ");
        showCertificatedList();
    };

    public abstract void showInfo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Candidate && ((Candidate) o).FullName.equals(this.FullName));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(FullName);
    }
}
