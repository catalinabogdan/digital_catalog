import java.util.ArrayList;
import java.util.Vector;

public class Catalog implements Subject {
    public ArrayList<Course> courses;
    private static Catalog catalog_instance = null;
    public ArrayList<Observer>observers;
    public Catalog(){
        courses = new ArrayList<Course>();
        observers = new ArrayList<Observer>();
    }
    public static Catalog getInstance() {
        if (catalog_instance == null) {
            catalog_instance = new Catalog();
        }

        return catalog_instance;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String toString() {
        return "Catalog: cursuri= " + this.courses ;
    }
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notifyObservers(Grade grade){
        for(Observer observer : observers){
            if(grade.getStudent().isObserver(observer)==1){
                observer.update(new Notification("Your child's final grade is :" + grade.getTotal() + "." , observer));
            }
        }
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
    public Course getCourse(String name){
        for(Course c : courses)
            if(c.courseName.equals(name))
                return c;
        return null;
    }

}
