import java.util.Collection;

public class BestExamScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades){
        double best=0.0;
        Student bestStudent=null;
        for(Grade grade : grades){
            if(grade.getExamScore().compareTo(best)>=0){
                best=grade.getPartialScore();
                bestStudent=grade.getStudent();
            }
        }
        return bestStudent;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
