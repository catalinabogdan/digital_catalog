public class Grade implements java.lang.Comparable<Grade>, java.lang.Cloneable {
    private Double partialScore, examScore;
    private Student student;
    private String course;

    Grade(){}
    //initializare student fara note
    public Grade(String course, Student student){
        this.partialScore = 0.0;
        this.examScore = 0.0;
        this.course = course;
        this.student = student;
    }
    //initializare student care primeste punctaj partial si total
    public Grade(String course, Student student, Double partialScore, Double examScore) {
        this.partialScore = partialScore;
        this.examScore = examScore;
        this.course = course;
        this.student = student;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }

    public Double getPartialScore() {
        return partialScore;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public Double getTotal(){
        return this.partialScore+this.examScore;
    }
    public int compareTo(Grade grade){
      return this.getTotal().compareTo(grade.getTotal());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Grade gradeClone =(Grade) super.clone();
        if(this.partialScore == null || this.examScore == null || this.course == null || this.student == null){
            throw new CloneNotSupportedException("Invalid grade");
        }else {
            gradeClone.course = this.course;
            gradeClone.student = this.student;
            gradeClone.partialScore = this.partialScore;
            gradeClone.examScore = this.examScore;
            return gradeClone;
        }
    }

    @Override
    public String toString() {
        return "\n"+ "partialScore= " + partialScore + " "+
                "examScore= " + examScore + " "+
                "student= " + student + " "+
                "course= " + course ;
    }
}
