package Day2.Model;

import java.util.Date;
import java.util.List;

public class Experience extends Candidate{
    private int ExpInYear;
    private String ProSkill;

    public Experience() {}

    public Experience(String candidateID, String fullName, String birthDay, String phone, String email,
                      int ExpInYear, String ProSkill, List<Certificated> certificatedList) {
        super(candidateID, fullName, birthDay, phone, email, certificatedList);
        this.ExpInYear = ExpInYear;
        this.ProSkill = ProSkill;
    }

    public int getExpInYear() {
        return ExpInYear;
    }

    public void setExpInYear(int expInYear) {
        ExpInYear = expInYear;
    }

    public String getProSkill() {
        return ProSkill;
    }

    public void setProSkill(String proSkill) {
        ProSkill = proSkill;
    }

    @Override
    public void showInfo() {
        super.getInfo();
        System.out.println("Experience In Year: " + getExpInYear());
        System.out.println("Pro Skill: " + getProSkill());
    }
}
