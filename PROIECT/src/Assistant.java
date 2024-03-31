public class Assistant extends User implements Comparable{
    String firstName,lastName;
    public Assistant(String firstName, String lastName){
        super(firstName, lastName);
    }
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    public int compare(Assistant a1, Assistant a2){
        if(a1.equals(a2)){
            return 0;
        }
        else if(a1.getLastName() == a2.getLastName()){
            return a1.getFirstName().compareTo(a2.getFirstName());
        }
        else return a1.getLastName().compareTo(a2.getLastName());
    }

    @Override
    public int compareTo(Object o){
        Assistant assistant = (Assistant) o;
        if(this.equals(assistant)){
            return 0;
        }
        else if(this.getLastName() == assistant.getLastName()){
            return this.getFirstName().compareTo(assistant.getFirstName());
        }
        else return this.getLastName().compareTo(assistant.getLastName());
    }
}
