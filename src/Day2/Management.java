package Day2;

import Day2.DAO.CandidateDAO;
import Day2.Model.*;
import Day2.Utils.Exception.BirthDayException;
import Day2.Utils.Exception.EmailException;
import Day2.Utils.MessageCommon;
import Day2.Utils.ValidateData;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    public static Scanner sc = new Scanner(System.in);
    static CandidateDAO cdao = new CandidateDAO();

    public static void addInfoCommon(Candidate candidate) throws ParseException, BirthDayException {
        sc.nextLine();
        System.out.print("Enter the name of your student: ");
        candidate.setFullName(sc.nextLine());

        while(true){
            try {
                System.out.print("Enter the BirthDate of your student (yyyy-MM-dd): ");
                candidate.setBirthDay(sc.nextLine());
                boolean isValid = ValidateData.validateBirthday(candidate.getBirthDay());
                if(isValid) break;
            } catch (BirthDayException e){
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }
        }

        while(true){
            try{
                System.out.print("Enter the Email your student: ");
                candidate.setEmail(sc.nextLine());
                if(ValidateData.validateEmail(candidate.getEmail())) break;
            }catch (EmailException e){
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }
        }

        System.out.print("Enter the Phone of your student: ");
        candidate.setPhone(sc.nextLine());

        int numberCertificate;
        System.out.print("Enter number of Certificates:");
        numberCertificate = sc.nextInt();
        sc.nextLine();
        ArrayList<Certificated> certificates = new ArrayList<>();
        /*Certificated cert = new Certificated("1", "Test", "Good", "2023-01-01");
        Certificated cert1 = new Certificated("2", "Test1", "Good", "2023-01-01");
        certificates.add(cert);
        certificates.add(cert1);*/

        for(int i = 0; i < numberCertificate; i++){
            Certificated cert = new Certificated();
            System.out.print("Enter name: ");
            cert.setCertificateName(sc.nextLine());
            System.out.print("Enter rank: ");
            cert.setCertificateRank(sc.nextLine());
            while(true){
                System.out.print("Enter date: ");
                cert.setCertificatedDate(sc.nextLine());
                try{
                    if(ValidateData.validateDate(cert.getCertificatedDate())) break;
                }catch (Exception e){
                    System.out.println(MessageCommon.MESSAGE_DEFAULT);
                }

            }
            certificates.add(cert);
        }
        candidate.setCertificatedList(certificates);
    }

    public static void addNewExperience() throws Exception {
        Candidate candidate = new Experience();
        addInfoCommon(candidate);
        Experience experience = (Experience) candidate;

        System.out.print("Enter yearOfExperience: ");
        experience.setExpInYear(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter ProSkill: ");
        experience.setProSkill(sc.nextLine());

        if(cdao.saveExperience(experience)){
            System.out.println("Your experience has been saved!");
            Candidate.Candidate_count++;
        }
        else System.out.println("Save failed!");
    }

    public static void addNewFresher() throws Exception {
        Candidate candidate = new Fresher();
        addInfoCommon(candidate);
        Fresher fresher = (Fresher) candidate;

        System.out.print("Enter graduation date: ");
        String graduationDate = sc.nextLine();
        ValidateData.validateBirthday(graduationDate);
        fresher.setGraduation_date(graduationDate);

        System.out.print("Enter graduation rank: ");
        fresher.setGraduation_rank(sc.nextLine());
        System.out.print("Enter education name: ");
        fresher.setEducation(sc.nextLine());

        if(cdao.saveFresher(fresher)){
            System.out.println("Your fresher has been saved!");
            Candidate.Candidate_count++;
        }
        else System.out.println("Save failed!");
    }

    public static void addNewIntern() throws Exception {
        Candidate candidate = new Intern();
        addInfoCommon(candidate);
        Intern intern = (Intern) candidate;

        System.out.print("Enter major: ");
        intern.setMajors(sc.nextLine());
        System.out.print("Enter semester: ");
        intern.setSemester(sc.nextLine());
        System.out.print("Enter university name: ");
        intern.setUniversity_name(sc.nextLine());

        if(cdao.saveIntern(intern)){
            System.out.println("Your intern has been saved!");
            Candidate.Candidate_count++;
        }
        else System.out.println("Save failed!");
    }

    public static void addNew() throws Exception {
        System.out.println("Nhap loai ung vien:");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Intern");
        int selection = sc.nextInt();
        if(selection == 1) addNewExperience();
        else if(selection == 2) addNewFresher();
        else if(selection == 3) addNewIntern();

    }

    public static void showCandidates() throws Exception {
        List<Candidate> candidateList = cdao.getCandidateList();
        candidateList.sort((o1, o2) -> {
            String type1 = o1.getCandidate_type().toString();
            String type2 = o2.getCandidate_type().toString();
            int compare = type1.compareTo(type2);
            if (compare != 0) return compare;

            String date1 = o1.getBirthDay().split("-")[0];
            String date2 = o2.getBirthDay().split("-")[0];
            return -date1.compareTo(date2);
        });
        if(candidateList.isEmpty()) System.out.println("List not found!");
        candidateList.forEach(Candidate::showInfo);

//        Set<Candidate> candidateSet = new LinkedHashSet<>(candidateList);
//        for (Candidate candidate : candidateSet) {
//            candidate.showInfo();
//            System.out.println("---------------");
//        }
    }

    public static void getAllName() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        List<Candidate> candidateList = cdao.getCandidateList();
        candidateList.forEach(candidate -> stringBuilder.append(candidate.getFullName()).append(", "));
        System.out.println("Name: " + stringBuilder);
    }

    public static void updateExperience() throws Exception {
        Experience experience = new Experience();
        System.out.print("Enter ID update: ");
        experience.setCandidateID(sc.nextLine().trim());
        System.out.print("Enter Experience in year: ");
        experience.setExpInYear(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter skill");
        experience.setProSkill(sc.nextLine());

        cdao.updateExperience(experience);
    }

    public static void updateFresher() throws Exception {
        Fresher fresher = new Fresher();
        System.out.print("Enter ID update: ");
        fresher.setCandidateID(sc.nextLine().trim());
        System.out.print("Enter graduation date: ");
        fresher.setGraduation_date(sc.nextLine());
        System.out.print("Enter graduation rank: ");
        fresher.setGraduation_rank(sc.nextLine());
        System.out.print("Enter education name: ");
        fresher.setEducation(sc.nextLine());

        cdao.updateFresher(fresher);
    }

    public static void updateIntern() throws Exception {
        Intern intern = new Intern();
        System.out.print("Enter ID update: ");
        intern.setCandidateID(sc.nextLine().trim());
        System.out.print("Enter major: ");
        intern.setMajors(sc.nextLine());
        System.out.print("Enter semester: ");
        intern.setSemester(sc.nextLine());
        System.out.print("Enter university name: ");
        intern.setUniversity_name(sc.nextLine());

        cdao.updateIntern(intern);
    }

    public static void main(String[] args) throws Exception {
        int select = 0;
        do {
            System.out.println("\n\n====== CANDIDATE MANAGEMENT ======\n");
            System.out.println("1. Them ung vien");
            System.out.println("2. Hien thi tat ca ung vien");
            System.out.println("3. Tong hop ten ung vien");
            System.out.println("4. Update info Experience");
            System.out.println("5. Update info Fresher");
            System.out.println("6. Update info Intern");
            System.out.print("\nEnter option: ");

            try{
                select = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                select = -1;
                System.out.println(MessageCommon.MESSAGE_DEFAULT);
            }

            if(select == 1) addNew();
            else if(select == 2) showCandidates();
            else if(select == 3) getAllName();
            else if (select == 4) updateExperience();
            else if(select == 5) updateFresher();
            else if(select == 6) updateIntern();
            else if(select != 0) System.out.println("Invalid option!");
        } while(select != 0);
    }
}
