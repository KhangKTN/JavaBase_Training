package Day2.Utils;

public class SQLCommand {
    public static String INSERT_CANDIDATE = "INSERT INTO CANDIDATE (FullName, BirthDay, Phone, Email) VALUES (?,?,?,?)";
    public static String INSERT_EXPERIENCE = "INSERT INTO CANDIDATE (FullName, BirthDay, Email, Phone, CandidateType, ExpInYear, ProSkill) VALUES (?,?,?,?,?,?,?)";
    public static String INSERT_FRESHER = "INSERT INTO CANDIDATE (FullName, BirthDay, Email, Phone, CandidateType, GraduationDate, GraduationRank, Education) VALUES (?,?,?,?,?,?,?,?)";
    public static String INSERT_INTERN = "INSERT INTO CANDIDATE (FullName, BirthDay, Email, Phone, CandidateType, Major, Semester, Education) VALUES (?,?,?,?,?,?,?,?)";

    public static String SELECT_CANDIDATE = "SELECT * FROM CANDIDATE";
}
