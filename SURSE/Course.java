import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public abstract class Course {
    public String courseName;
    public Teacher teacher;
    public TreeSet<Assistant> assistants;
    public ArrayList<Grade> grades;
    public HashMap<String,Group> groups = new HashMap<>();
    public Strategy strategy;
    public SnapShot snapShot;
    public int creditPoints ;

    public ArrayList<Grade> getGrades() {
        return grades;
    }
    public TreeSet<Assistant> getAssistants() {
        return assistants;
    }
    public int getCreditPoints(){return creditPoints;}
    public String getCourseName() {
        return courseName;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public HashMap<String, Group> getGroups() {
        return groups;
    }
    public Strategy getStrategy(){ return strategy;}

    public Course(CourseBuilder courseBuilder){
        this.courseName = courseBuilder.courseName;
        this.teacher = courseBuilder.teacher;
        this.assistants = courseBuilder.assistants;
        this.grades = courseBuilder.grades;
        this.groups = courseBuilder.groups;
        this.strategy = courseBuilder.strategy;
        this.creditPoints=courseBuilder.creditPoints;
    }

    public abstract static class CourseBuilder {
        private String courseName;
        private Teacher teacher;
        private TreeSet<Assistant> assistants;
        private ArrayList<Grade> grades;
        private HashMap<String,Group> groups = new HashMap<>();
        private Strategy strategy;
        private int creditPoints;

        public CourseBuilder setStrategy(Strategy strategy){
            this.strategy = strategy;
            return this;
        }

        public CourseBuilder setCreditPoints(int creditPoints) {
            this.creditPoints = creditPoints;
            return this;
        }

        public CourseBuilder setCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseBuilder setTeacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public CourseBuilder setAssistants(TreeSet<Assistant> assistants) {
            this.assistants = assistants;
            return this;
        }

        public CourseBuilder setGrades(ArrayList<Grade> grades) {
            this.grades = grades;
            return this;
        }

        public CourseBuilder setGroups(HashMap<String, Group> groups) {
            this.groups = groups;
            return this;
        }
        public abstract Course buildCourse();

    }
    public void addAssistant(String ID, Assistant assistant){
        if(!this.assistants.contains(assistant)){
            this.assistants.add(assistant);
        }
        this.groups.get(ID).setAssistant(assistant);
    }
    public void addStudent(String ID, Student student){
        for( String groupID : this.groups.keySet()){
            if(groupID.equals(ID)){
                this.groups.get(ID).add(student);
            }
        }
    }
    public void addGroup(Group group){
        this.groups.put(group.getID(), group);
    }
    public void addGroup(String ID, Assistant assistant){
        Group group = new Group(ID,assistant);
        this.groups.put(ID, group);
    }
    public void addGroup(String ID, Assistant assistant, Comparator<Student> comp){
        Group group = new Group(ID,assistant,comp);
        this.groups.put(ID, group);
    }
    public Grade getGrade(Student student){
        for(Grade grade : this.grades){
            if(grade.getStudent().equals(student)){
                return grade;
            }
        }
        return null;
    }
    public void addGrade(Grade grade){
        this.grades.add(grade);
    }
    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> students = new ArrayList<>();
        for(Grade grade : this.grades){
            students.add(grade.getStudent());
        }
        return students;
    }
    public HashMap<Student, Grade> getAllStudentGrades(){
        HashMap<Student, Grade> scholarStatus = new HashMap<Student,Grade>();
        for(Grade grade : this.grades){
            scholarStatus.put(grade.getStudent(), grade);
        }
        return scholarStatus;
    }
    public abstract ArrayList<Student> getGraduatedStudents();

    public String toString(){
        return this.courseName + "\n" + "Teacher: "+ this.teacher + "\n"+
                "Course Assistants: " + this.assistants +"\n"+ "Students: " +
                getAllStudents() +"\n"+ "Grades: " + this.grades +"\n"+ "Credit points: "
                + this.creditPoints ;
    }
    public Student getBestStudent(){
        return this.strategy.getBestStudent(grades);
    }
    private static class SnapShot {
        private ArrayList<Grade> copiedGrades;
        public SnapShot(){
            this.copiedGrades = new ArrayList<>();
        }

        public void setCopiedGrades(ArrayList<Grade> copiedGrades){
            this.copiedGrades = copiedGrades;
        }
        public ArrayList<Grade> getCopiedGrades(){
            return this.copiedGrades;
        }
        public SnapShot(ArrayList<Grade> grades) throws CloneNotSupportedException {
            this();
            for (Grade grade : grades) {
                this.copiedGrades.add((Grade) grade.clone());
            }
        }
    }
    public void makeBackup() {
        try {
            this.snapShot = new SnapShot(grades);
        } catch (CloneNotSupportedException exception){
            exception.printStackTrace();
        }
    }
    public void undo() {
        this.grades = this.snapShot.copiedGrades;
    }
}
