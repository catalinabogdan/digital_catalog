import java.util.ArrayList;
import java.util.HashMap;

public class PartialCourse extends Course{
    public PartialCourse(PartialCourseBuilder partialCourseBuilder){
        super(partialCourseBuilder);
    }
    public static class PartialCourseBuilder extends Course.CourseBuilder{
        public PartialCourse buildCourse(){
            return new PartialCourse(this);
        }
    }
    public ArrayList<Student> getGraduatedStudents(){
        ArrayList<Student> students = new ArrayList<Student>();
        double totalScore;
        for(Grade grade: grades){
            totalScore = grade.getPartialScore() + grade.getExamScore();
            if(totalScore  >= 5){
                students.add(grade.getStudent());
            }
        }
        return students;
    }
}
