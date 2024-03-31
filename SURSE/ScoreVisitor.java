import java.util.ArrayList;
import java.util.HashMap;

public class ScoreVisitor implements Visitor{
    private HashMap<Teacher, ArrayList<Tuple<Student,String,Double>>> examScores;

    private HashMap<Assistant,ArrayList<Tuple<Student,String,Double>>> partialScores;
    public HashMap<Teacher, ArrayList<Tuple<Student, String, Double>>> getExamScores() {
        return examScores;
    }
    public HashMap<Assistant, ArrayList<Tuple<Student, String, Double>>> getPartialScores() {
        return partialScores;
    }
    public void setExamScores(HashMap<Teacher, ArrayList<Tuple<Student, String, Double>>> examScores) {
        this.examScores = examScores;
    }

    public void setPartialScores(HashMap<Assistant, ArrayList<Tuple<Student, String, Double>>> partialScores) {
        this.partialScores = partialScores;
    }
    private static class Tuple<K,V1,V2>{
        private K student;
        private V1 courseName;
        private V2 grade;
        public Tuple(K student, V1 courseName, V2 grade){
            this.student=student;
            this.courseName=courseName;
            this.grade=grade;
        }
        public K getStudent(){
            return this.student;
        }

        public V1 getCourseName() {
            return this.courseName;
        }

        public V2 getGrade() {
            return this.grade;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "student=" + student +
                    ", courseName=" + courseName +
                    ", grade=" + grade +
                    '}';
        }
    }
    public void addNewExamScore(Teacher teacher, Student student,String course,Double newExamScore){
        if(this.examScores == null){
            this.examScores = new HashMap<>();
        }
        if(!this.examScores.containsKey(teacher)){
            this.examScores.put(teacher,new ArrayList<>());
        }
        this.examScores.get(teacher).add(new Tuple<>(student,course,newExamScore));
    }

    public void addNewPartialScore(Assistant assistant, Student student, String course, Double newPartialScore){
        if(this.partialScores == null){
            this.partialScores = new HashMap<>();
        }
        if(!this.partialScores.containsKey(assistant)){
            this.partialScores.put(assistant,new ArrayList<>());
        }
        this.partialScores.get(assistant).add(new Tuple<>(student,course,newPartialScore));
    }

    public void visit(Assistant assistant){
        ArrayList<Tuple<Student, String, Double>> gradesList = partialScores.get(assistant);
        for(Tuple<Student, String, Double> gr : gradesList) {
            for(Course course : Catalog.getInstance().getCourses()){
                if(course.getCourseName().equals(gr.getCourseName())) {
                    boolean exists = false;
                    for (Grade grade : course.grades) {
                        Student s = gr.getStudent();
                        if (s.equals(grade.getStudent())) {
                            exists=true;
                            grade.setPartialScore(gr.getGrade());
                            Catalog.getInstance().notifyObservers(grade);
                        }
                    }
                    if (!exists) {
                        Grade newGrade = new Grade( course.courseName,gr.getStudent(), gr.getGrade(), 0.0);
                        course.addGrade(newGrade);
                        Catalog.getInstance().notifyObservers(newGrade);
                    }
                }
            }
        }
    }
    public void visit(Teacher teacher) {
        ArrayList<Tuple<Student, String, Double>> tuples = examScores.get(teacher);
        for (Tuple<Student, String, Double> tuple : tuples) {
            Course course = Catalog.getInstance().getCourse(tuple.getCourseName());
            boolean exists= false;
            for (Grade grade : course.grades) {
                Student student = tuple.getStudent();
                if (student.equals(grade.getStudent())) {
                    exists = true;
                    grade.setPartialScore(tuple.getGrade());
                    Catalog.getInstance().notifyObservers(grade);
                }
            }
            if (!exists) {
                Grade newGrade = new Grade( course.courseName,tuple.getStudent(), 0.0, tuple.getGrade());
                course.addGrade(newGrade);
                Catalog.getInstance().notifyObservers(newGrade);
            }
        }
    }
}
