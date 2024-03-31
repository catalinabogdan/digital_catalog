public class Teacher extends User implements Element{
    String firstName, lastName;
    Teacher(String firstName, String lastName){
        super(firstName, lastName);
    }
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
