package Day2.Model;

import java.util.Date;
import java.util.List;

public class Intern extends Candidate{
    private String Majors;
    private String Semester;
    private String University_name;

    public Intern(){}

    public Intern(String candidateID, String fullName, String birthDay, String phone, String email,
                  String majors, String semester, String university_name, List<Certificated> certificatedList) {
        super(candidateID, fullName, birthDay, phone, email, certificatedList);
        this.Majors = majors;
        this.Semester = semester;
        this.University_name = university_name;
    }

    public String getMajors() {
        return Majors;
    }

    public void setMajors(String majors) {
        Majors = majors;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getUniversity_name() {
        return University_name;
    }

    public void setUniversity_name(String university_name) {
        University_name = university_name;
    }

    @Override
    public void showInfo() {
        super.getInfo();
        System.out.println("Majors: " + Majors);
        System.out.println("Semester: " + Semester);
        System.out.println("University name: " + University_name);
    }
}
