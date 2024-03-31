import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;


public class Test {
    public static void main(String[] args) {
        User studentA = UserFactory.getUser("Student", "Sulea", "Popescu");
        User studentB = UserFactory.getUser("Student", "Trulea", "Ionescu");
        User studentC = UserFactory.getUser("Student", "Logan", "Isarescu");
        User studentD = UserFactory.getUser("Student", "NOUL", "MEUSTUDENT");

        User studentNou1 = UserFactory.getUser("Student", "STELICA", "COMANESCU");
        User studentNou2 = UserFactory.getUser("Student", "PATROCLE", "TATARESCU");
        User studentNou3 = UserFactory.getUser("Student", "LOREDANA", "BIBESCU");
        User studentNou4 = UserFactory.getUser("Student", "LAURA", "GAURA");

        ArrayList<Student> students = new ArrayList<>();
        students.add((Student)studentA);
        students.add((Student)studentB);
        students.add((Student)studentC);
        students.add((Student)studentD);

        ArrayList<Student> studentiNoi = new ArrayList<>();
        studentiNoi.add((Student)studentNou1);
        studentiNoi.add((Student)studentNou2);
        studentiNoi.add((Student)studentNou3);
        studentiNoi.add((Student)studentNou4);

        User mother1 = UserFactory.getUser("Parent", "Miss", "COMANESCU");
        User father1 = UserFactory.getUser("Parent", "Mister", "COMANESCU");
        User mother2 = UserFactory.getUser("Parent", "Miss", "TATARESCU");
        User father2 = UserFactory.getUser("Parent", "Mister", "TATARESCU");
        User mother3 = UserFactory.getUser("Parent", "Miss", "BIBESCU");
        User father3 = UserFactory.getUser("Parent", "Mister", "BIBESCU");
        User mother4 = UserFactory.getUser("Parent", "Miss", "GAURA");
        User father4 = UserFactory.getUser("Parent", "Mister", "GAURA");


        User motherA = UserFactory.getUser("Parent", "Maricica", "Popescu");
        User fatherA = UserFactory.getUser("Parent", "Marian", "Popescu");
        User motherB = UserFactory.getUser("Parent", "Mirabela", "Ionescu");
        User fatherB = UserFactory.getUser("Parent", "Nicu", "Ionescu");
        User motherC = UserFactory.getUser("Parent", "Carina", "Isarescu");
        User fatherC = UserFactory.getUser("Parent", "Relu", "Isarescu");

        ((Student) studentA).setMother((Parent) motherA);
        ((Student) studentA).setFather((Parent) fatherA);
        ((Student) studentB).setFather((Parent) fatherB);
        ((Student) studentB).setMother((Parent) motherB);
        ((Student) studentC).setFather((Parent) fatherC);
        ((Student) studentC).setMother((Parent) motherC);

        ((Student) studentNou1).setMother((Parent) mother1);
        ((Student) studentNou1).setFather((Parent) father1);
        ((Student) studentNou2).setFather((Parent) father2);
        ((Student) studentNou2).setMother((Parent) mother2);
        ((Student) studentNou3).setFather((Parent) father3);
        ((Student) studentNou3).setMother((Parent) mother3);
        ((Student) studentNou4).setMother((Parent) mother4);
        ((Student) studentNou4).setFather((Parent) father4);





        User teacherA = UserFactory.getUser("Teacher", "Catalin", "Stanica");
        User assistantA1 = UserFactory.getUser("Assistant", "Ana", "Coman");
        User assistantA2 = UserFactory.getUser("Assistant", "Nevasta","Stanica");
        TreeSet<Assistant> assistantsA = new TreeSet<>();
        assistantsA.add((Assistant) assistantA1);
        assistantsA.add((Assistant) assistantA2);
        Grade gradeA =new Grade("Matematica",(Student)studentA, 3.5, 5.0);
        Grade gradeB =new Grade("Matematica",(Student)studentB, 3.0,4.0);
        Grade gradeC =new Grade("Matematica",(Student)studentC, 2.0,4.0);
        ArrayList<Grade> gradesA = new ArrayList<>();
        gradesA.add(gradeA);
        gradesA.add(gradeB);
        gradesA.add(gradeC);


        User teacherB = UserFactory.getUser("Teacher", "Paolo", "Prus");
        User assistantB1 = UserFactory.getUser("Assistant", "Monica", "Unghiicugel");
        User assistantB2 = UserFactory.getUser("Assistant", "Vasile","Unghiicugel");
        TreeSet<Assistant> assistantsB = new TreeSet<>();
        assistantsB.add((Assistant) assistantB1);
        assistantsB.add((Assistant) assistantB2);
        Grade gradeAA =new Grade("Informatica",(Student)studentA, 3.6,4.7);
        Grade gradeBB =new Grade("Informatica",(Student)studentB, 5.0,5.0);
        Grade gradeCC =new Grade("Informatica",(Student)studentC, 1.0,4.0);
        ArrayList<Grade> gradesB = new ArrayList<>();
        gradesB.add(gradeAA);
        gradesB.add(gradeBB);
        gradesB.add(gradeCC);



        FullCourse fullCourse = (FullCourse) new FullCourse.FullCourseBuilder()
                .setCourseName("Matematica")
                .setTeacher((Teacher)teacherA)
                .setAssistants(assistantsA)
                .setGrades(gradesA)
                .setStrategy(new BestExamScore())
                .setCreditPoints(5)
                .buildCourse();
        PartialCourse partialCourse = (PartialCourse) new PartialCourse.PartialCourseBuilder()
                .setCourseName("Informatica")
                .setTeacher((Teacher)teacherB)
                .setAssistants(assistantsB)
                .setGrades(gradesB)
                .setStrategy(new BestPartialScore())
                .setCreditPoints(3)
                .buildCourse();

        System.out.println("Course: "+ fullCourse);
        System.out.println("Best student of this course:" + fullCourse.getBestStudent());

        System.out.println("Course: "+ partialCourse);
        System.out.println("Best student of this course:" + partialCourse.getBestStudent());

        Catalog catalog = Catalog.getInstance();
        catalog.addCourse(fullCourse);
        catalog.addCourse(partialCourse);


        catalog.addObserver((Parent) motherA);
        catalog.addObserver((Parent) fatherB);
        catalog.addObserver((Parent)motherC);
        catalog.addObserver((Parent) mother1);
        catalog.addObserver((Parent) father2);
        catalog.addObserver((Parent)mother3);
        catalog.addObserver((Parent) mother4);

        catalog.notifyObservers(gradeA);
        catalog.notifyObservers(gradeB);
        catalog.notifyObservers(gradeC);

        Comparator<Student> comparator = new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                if (student1.equals(student2))
                    return 0;

                else if (student1.getLastName().equals(student2.getLastName())) {
                    return student1.getFirstName().compareTo(student2.getFirstName());
                }
                return student1.getLastName().compareTo(student2.getLastName());
            }
        };

        System.out.println("TESTARE                                                            FULL");
        fullCourse.addGroup("11B", (Assistant) assistantA1, comparator );
        fullCourse.addStudent("11B",(Student)studentD );
        fullCourse.addAssistant("11B",(Assistant)assistantB1);
        System.out.println(fullCourse);
        System.out.println(studentD);
        System.out.println(fullCourse.getGraduatedStudents());

        System.out.println("TESTARE                                                             PARTIAL");
        partialCourse.addGroup("12B",(Assistant)assistantB1,comparator);
        partialCourse.addStudent("12B",(Student) studentD);
        Grade newGradeD = new Grade("Informatica",(Student)studentD,4.0,5.0);
        partialCourse.addGrade(newGradeD);
        System.out.println(partialCourse);
        System.out.println(partialCourse.getGraduatedStudents());

        System.out.println("TESTARE                                                              GROUPE");
        Group group = new Group("10A",(Assistant) assistantB1,comparator);
        System.out.println(group);
        System.out.println(group.getAssistant());
        System.out.println(group.getID());

        System.out.println("TESTARE                                                              CATALOG");
        System.out.println(catalog);

        System.out.println("TESTARE                                                              METODE COURSE");
        System.out.println(fullCourse.getGrades());
        System.out.println(fullCourse.getAssistants());
        System.out.println(fullCourse.getCreditPoints());


        System.out.println("TESTARE                                                              MEMENTO");
        Grade grade2 = new Grade("Matematica",(Student)studentD,5.0,5.0);
        fullCourse.makeBackup();
        System.out.println(fullCourse.grades);
        fullCourse.addGrade(grade2);
        System.out.println(fullCourse.grades);

        System.out.println("FACEM UNDO.");
        fullCourse.undo();
        System.out.println(fullCourse.grades);

        System.out.println("TESTARE                                                               SCOREVISITOR");
        System.out.println("CATALOG                                                               INAITE");
        System.out.println(catalog);
        ScoreVisitor scoreVisitor = new ScoreVisitor();
        System.out.println("\n");
        scoreVisitor.addNewExamScore((Teacher) teacherA, (Student) studentNou1, "Matematica", 200000.0);
        scoreVisitor.addNewExamScore((Teacher) teacherA, (Student) studentNou2, "Matematica", 200000.0);
        scoreVisitor.addNewPartialScore((Assistant) assistantA1, (Student) studentNou1, "Matematica", 200000.0);
        scoreVisitor.addNewPartialScore((Assistant) assistantA1, (Student) studentNou2, "Matematica", 200000.0);

        scoreVisitor.visit((Teacher) teacherA);
        System.out.println("CATALOG                         ACTUALIZARE                           TEACHER");
        System.out.println("Actualizare teacher: " + catalog);
        ((Teacher) teacherA).accept(scoreVisitor);


        System.out.println("CATALOG                         ACTUALIZARE                           ASISTENT");
        scoreVisitor.visit((Assistant) assistantA1);
        System.out.println("Actualizare asistent: " + catalog);
        ((Assistant) assistantA1).accept(scoreVisitor);




    }
}
