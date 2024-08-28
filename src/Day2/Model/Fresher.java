package Day2.Model;

import java.util.Date;
import java.util.List;

public class Fresher extends Candidate {
    private String Graduation_date;
    private String Graduation_rank;
    private String Education;

    public Fresher() {}

    public Fresher(String candidateID, String fullName, String birthDay, String phone, String email,
                   String graduation_date, String graduation_rank, String Education, List<Certificated> certificatedList
    ) {
        super(candidateID, fullName, birthDay, phone, email, certificatedList);
        this.Graduation_date = graduation_date;
        this.Graduation_rank = graduation_rank;
        this.Education = Education;
    }

    public String getGraduation_date() {
        return Graduation_date;
    }

    public void setGraduation_date(String graduation_date) {
        Graduation_date = graduation_date;
    }

    public String getGraduation_rank() {
        return Graduation_rank;
    }

    public void setGraduation_rank(String graduation_rank) {
        Graduation_rank = graduation_rank;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    @Override
    public void showInfo() {
        super.getInfo();
        System.out.println("Graduation date : " + Graduation_date);
        System.out.println("Graduation rank : " + Graduation_rank);
        System.out.println("Education : " + Education);
    }
}
