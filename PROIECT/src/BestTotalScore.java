import java.util.Collection;

public class BestTotalScore implements Strategy {
    @Override
    public Student getBestStudent(Collection<Grade> grades){
    double best=0.0;
    Student bestStudent=null;
        for(Grade grade : grades){
        if(grade.getTotal().compareTo(best)>=0){
            best=grade.getTotal();
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
