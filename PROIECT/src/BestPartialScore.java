import java.util.ArrayList;
import java.util.Collection;

public class BestPartialScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        double best=0.0;
        Student bestStudent=null;
        for(Grade grade : grades){
            if(grade.getPartialScore().compareTo(best)>=0){
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
