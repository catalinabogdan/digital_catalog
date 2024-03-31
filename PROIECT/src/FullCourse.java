import java.util.ArrayList;

public class FullCourse extends Course{
    public FullCourse(FullCourseBuilder fullCourseBuilder){
        super(fullCourseBuilder);
    }
    public static class FullCourseBuilder extends Course.CourseBuilder{
        @Override
        public FullCourse buildCourse(){
            return new FullCourse(this);
        }
    }
    public ArrayList<Student> getGraduatedStudents(){
        ArrayList<Student> students = new ArrayList<>();
        for(Grade grade : grades){
            if(grade.getPartialScore() >=3 && grade.getExamScore() >=2){
                students.add(grade.getStudent());
            }
        }
        return students;
    }
}
