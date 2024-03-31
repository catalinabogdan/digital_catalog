public class Student extends User implements Comparable{
    private Parent mother;
    private Parent father;
    public Student(String firstName, String lastName){
        super(firstName, lastName);
    }
    public Parent getMother(Parent mother){
        return this.mother;
    }

    public Parent getFather() {
        return father;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public void setFather(Parent father) {
        this.father = father;
    }
    public int isObserver(Observer observer){
        if(observer.equals(father) || observer.equals(mother)){
            return 1;
        }
        return 0;
    }
    @Override
    public int compareTo(Object o){
        Student student = (Student) o;
        if(this.equals(student)){
            return 0;
        }
        else if(this.getLastName().equals(student.getLastName())){
            return this.getFirstName().compareTo(student.getFirstName());
        }
        else return this.getLastName().compareTo(student.getLastName());
    }

    @Override
    public String toString() {
        return super.toString() ;
    }
}
