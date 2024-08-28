package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1_Bai10 {
    public static class Student implements Comparable<Student> {
        private String StdNo;
        private String StdName;
        private String StdPhone;
        private String StdEmail;
        private int GradePoint;

        public String getStdNo() {
            return StdNo;
        }

        public void setStdNo(String stdNo) {
            StdNo = stdNo;
        }

        public String getStdName() {
            return StdName;
        }

        public void setStdName(String stdName) {
            StdName = stdName;
        }

        public String getStdPhone() {
            return StdPhone;
        }

        public void setStdPhone(String stdPhone) {
            StdPhone = stdPhone;
        }

        public String getStdEmail() {
            return StdEmail;
        }

        public void setStdEmail(String stdEmail) {
            StdEmail = stdEmail;
        }

        public int getGradePoint() {
            return GradePoint;
        }

        public void setGradePoint(int gradePoint) {
            GradePoint = gradePoint;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "StdNo='" + StdNo + '\'' +
                    ", StdName='" + StdName + '\'' +
                    ", StdPhone='" + StdPhone + '\'' +
                    ", StdEmail='" + StdEmail + '\'' +
                    ", GradePoint='" + GradePoint + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Student o) {
            return this.GradePoint - o.getGradePoint();
        }
    }

    public static Student getStudentFromString(String studentLine){
        Student student = new Student();

        String[] dataParts = studentLine.split(",");
        student.setStdNo(dataParts[0]);
        student.setStdName(dataParts[1]);
        String phone = dataParts[2];
        student.setStdPhone(phone);
        String email = dataParts[3];
        student.setStdEmail(email);
        student.setStdEmail(dataParts[3]);
        try{
            int gradePoint = Integer.parseInt(dataParts[4]);
            student.setGradePoint(gradePoint);
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return student;
    }

    public static void checkValidateStudent(List<Student> studentList){
        boolean isValid = true;
        for(int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            if(!checkValidPhone(student.getStdPhone())){
                System.out.print("Line " + (i + 1) + ": co loi sai dinh dang ");
                System.out.print("StdPhone");
                isValid = false;
            };
            if(!checkValidEmail(student.getStdEmail())){
                if(isValid) System.out.print("Line " + (i + 1) + ": co loi sai dinh dang ");
                else System.out.print(", ");
                System.out.print("StdEmail");
            }
            System.out.print("\n");
        }
    }

    public static boolean checkValidEmail(String email){
        // Check email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static boolean checkValidPhone(String phone){
        String phoneRegrex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        return phone.matches(phoneRegrex);
    }

    public static List<Student> readFileStudentData() throws FileNotFoundException {
        File myFile = new File("./src/Day1/student.csv");
        List<Student> studentList = new ArrayList<>();
        Student student;
        if (myFile.exists() && myFile.canRead()) {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                student = getStudentFromString(data);
                studentList.add(student);
            }
            myReader.close();
        }
        return studentList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Student> studentList = readFileStudentData();
//        Collections.sort(studentList, Comparator.comparingInt(Student::getGradePoint));
//        studentList.sort(Comparator.comparingInt(Student::getGradePoint));
//        Collections.sort(studentList);


        Optional<Student> studentOptional = studentList.stream().parallel().filter(student -> student.getStdNo().startsWith("ST")).findAny();
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            System.out.println(student.toString());
            System.out.println("-------------------");
        }else System.out.println("Not found!");
        studentList.forEach(student -> System.out.println(student.toString()));
//        checkValidateStudent(studentList);
    }
}
