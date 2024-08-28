package Day2.DAO;

import Day2.Model.*;
import Day2.Utils.CandidateConstant;
import Day2.Utils.DBUtils;
import Day2.Utils.MessageCommon;
import Day2.Utils.SQLCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public boolean saveExperience(Experience experience){
        try{
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQLCommand.INSERT_EXPERIENCE, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setString(1, experience.getFullName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(experience.getBirthDay()));
            preparedStatement.setString(3, experience.getEmail());
            preparedStatement.setString(4, experience.getPhone());
            preparedStatement.setString(5, String.valueOf(CandidateConstant.EXPERIENCE));
            preparedStatement.setInt(6, experience.getExpInYear());
            preparedStatement.setString(7, experience.getProSkill());

//            results = preparedStatement.executeQuery();
//            results.moveToInsertRow();
//            results.updateString(2, experience.getFullName());
//            results.updateDate(3, java.sql.Date.valueOf(experience.getBirthDay()));
//            results.updateString(4, experience.getEmail());
//            results.updateString(5, experience.getPhone());
//            results.updateString(6, String.valueOf(CandidateConstant.EXPERIENCE));
//            results.updateInt(7, experience.getExpInYear());
//            results.updateString(8, experience.getProSkill());

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
//            results.insertRow();
//            results.moveToCurrentRow();

            String newId = "";
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                newId = generatedKeys.getString(1);
            }
            saveCertificate(newId, experience.getCertificatedList());
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        }
        catch(SQLException e){
            try{
                connection.rollback();
            } catch (SQLException rollbackException){
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }
            System.out.println(MessageCommon.MESSAGE_DEFAULT);
            return false;
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }
        }
    }

    public boolean saveFresher(Fresher fresher) throws SQLException {
        boolean result = false;
        try{
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQLCommand.INSERT_FRESHER, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, fresher.getFullName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(fresher.getBirthDay()));
            preparedStatement.setString(3, fresher.getEmail());
            preparedStatement.setString(4, fresher.getPhone());
            preparedStatement.setString(5, String.valueOf(CandidateConstant.FRESHER));
            preparedStatement.setDate(6, java.sql.Date.valueOf(fresher.getGraduation_date()));
            preparedStatement.setString(7, fresher.getGraduation_rank());
            preparedStatement.setString(8, fresher.getEducation());

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            String newId = "";
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                newId = generatedKeys.getString(1);
            }
            saveCertificate(newId, fresher.getCertificatedList());
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        }
        catch(SQLException e){
            System.out.println(MessageCommon.MESSAGE_DEFAULT);
            return false;
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }
        }
    }

    public boolean saveIntern(Intern intern) throws SQLException {
        boolean result = false;
        try{
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQLCommand.INSERT_INTERN, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, intern.getFullName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(intern.getBirthDay()));
            preparedStatement.setString(3, intern.getEmail());
            preparedStatement.setString(4, intern.getPhone());
            preparedStatement.setString(5, String.valueOf(CandidateConstant.INTERN));
            preparedStatement.setString(6, intern.getMajors());
            preparedStatement.setString(7, intern.getSemester());
            preparedStatement.setString(8, intern.getUniversity_name());

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            String newId = "";
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                newId = generatedKeys.getString(1);
            }
            saveCertificate(newId, intern.getCertificatedList());
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        }
        catch(SQLException e){
            System.out.println(MessageCommon.MESSAGE_DEFAULT);
            return false;
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }
        }
    }

    public void saveCertificate(String id, List<Certificated> certificateList) throws SQLException {
        connection = DBUtils.getInstance().getConnection();
        String sql = "Insert into Certificated(Name, Date, Rank, CandidateId) Values(?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);

        certificateList.forEach(cert -> {
            try {
                preparedStatement.setString(1, cert.getCertificateName());
                preparedStatement.setDate(2, Date.valueOf(cert.getCertificatedDate()));
                preparedStatement.setString(3, cert.getCertificateRank());
                preparedStatement.setString(4, id);
                preparedStatement.addBatch();
            } catch (SQLException e) {
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }
        });
        preparedStatement.executeBatch();
    }

    public ArrayList<Candidate> getCandidateList() throws SQLException {
        ArrayList<Candidate> candidateList = new ArrayList<>();
        connection = DBUtils.getInstance().getConnection();

        preparedStatement = connection.prepareStatement(SQLCommand.SELECT_CANDIDATE);
        results = preparedStatement.executeQuery();

        Candidate candidate = null;

        while (results.next()) {
            if(String.valueOf(CandidateConstant.EXPERIENCE).equals(results.getString("CandidateType"))){
                candidate = new Experience();
                setCommonData(candidate);

                Experience experience = (Experience) candidate;
                experience.setExpInYear(results.getInt("ExpInYear"));
                experience.setProSkill(results.getString("ProSkill"));
            }
            else if(String.valueOf(CandidateConstant.FRESHER).equals(results.getString("CandidateType"))){
                candidate = new Fresher();
                setCommonData(candidate);

                Fresher fresher = (Fresher) candidate;
                fresher.setGraduation_date(results.getString("GraduationDate"));
                fresher.setGraduation_rank(results.getString("GraduationRank"));
                fresher.setEducation(results.getString("Education"));
            }
            else if(String.valueOf(CandidateConstant.INTERN).equals(results.getString("CandidateType"))){
                candidate = new Intern();
                setCommonData(candidate);

                Intern intern = (Intern) candidate;
                intern.setMajors(results.getString("Major"));
                intern.setSemester(results.getString("Semester"));
                intern.setUniversity_name(results.getString("Education"));
            }

            candidateList.add(candidate);
        }
        return candidateList;
    }

    public void setCommonData(Candidate candidate) throws SQLException {
        if(candidate == null) return;

        candidate.setCandidateID(results.getString("CandidateId"));
        candidate.setFullName(results.getString("FullName"));
        candidate.setEmail(results.getString("Email"));
        candidate.setPhone(results.getString("Phone"));
        candidate.setBirthDay(results.getString("BirthDay"));
        candidate.setCandidate_type(CandidateConstant.valueOf(results.getString("CandidateType")));

        connection = DBUtils.getInstance().getConnection();
        List<Certificated> certificateList = new ArrayList<>();
        String sql = "Select * From Certificated Where CandidateId=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, candidate.getCandidateID());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Certificated cert = new Certificated();
            cert.setCertificateName(resultSet.getString("Name"));
            cert.setCertificateRank(resultSet.getString("Rank"));
            cert.setCertificatedDate(resultSet.getDate("Date").toString());
            certificateList.add(cert);
        }
        candidate.setCertificatedList(certificateList);
    }

    public void updateExperience(Experience experience) throws SQLException {
        connection = DBUtils.getInstance().getConnection();
        String candidateFind = "Select * From Candidate Where CandidateId = ?";
        preparedStatement = connection.prepareStatement(candidateFind,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setString(1, experience.getCandidateID());
        results = preparedStatement.executeQuery();

        if(!results.next()){
            System.out.println("!!! Not found to update Experience");
            return;
        }

        CandidateConstant candidateUpdate = CandidateConstant.valueOf(results.getString("CandidateType"));
        if(candidateUpdate != CandidateConstant.EXPERIENCE){
            System.out.println("!!! This ID is not of Experience");
            return;
        }

        results.updateInt("ExpInYear", experience.getExpInYear());
        results.updateString("ProSkill", experience.getProSkill());
        results.updateRow();
    }

    public void updateFresher(Fresher fresher) throws SQLException {
        connection = DBUtils.getInstance().getConnection();
        String findFresher = "Select * From Candidate Where CandidateId = ?";
        preparedStatement = connection.prepareStatement(findFresher,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setString(1, fresher.getCandidateID());
        results = preparedStatement.executeQuery();

        if(!results.next()){
            System.out.println("!!! Not found to update Fresher");
            return;
        }

        CandidateConstant candidateUpdate = CandidateConstant.valueOf(results.getString("CandidateType"));
        if(candidateUpdate != CandidateConstant.FRESHER){
            System.out.println("!!! This ID is not of Fresher");
            return;
        }

        results.updateString("Education", fresher.getEducation());
        results.updateString("GraduationRank", fresher.getGraduation_rank());
        results.updateDate("GraduationDate", Date.valueOf(fresher.getGraduation_date()));
        results.updateRow();
    }

    public void updateIntern(Intern intern) throws SQLException {
        connection = DBUtils.getInstance().getConnection();
        String findIntern = "Select * From Candidate Where CandidateId = ?";
        preparedStatement = connection.prepareStatement(findIntern, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setString(1, intern.getCandidateID());
        results = preparedStatement.executeQuery();

        if(!results.next()){
            System.out.println("!!! Not found to update Intern");
            return;
        }

        CandidateConstant candidateUpdate = CandidateConstant.valueOf(results.getString("CandidateType"));
        if(candidateUpdate != CandidateConstant.INTERN){
            System.out.println("!!! This ID is not of Intern");
            return;
        }

        results.updateString("Major", intern.getMajors());
        results.updateString("Semester", intern.getSemester());
        results.updateString("Education", intern.getUniversity_name());
        results.updateRow();
    }
}
