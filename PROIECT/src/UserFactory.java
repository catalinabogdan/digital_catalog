public class UserFactory {
    public UserFactory(){

    }
    public static User getUser(String userType, String firstName, String lastName ){
        if(userType!=null || !userType.isEmpty()){
            if("Student".equals(userType)){
                return new Student(firstName, lastName);
            }
            if("Parent".equals(userType)){
                return new Parent(firstName, lastName);
            }
            if("Assistant".equals(userType)){
                return new Assistant(firstName, lastName);
            }
            if("Teacher".equals(userType)){
                return new Teacher(firstName,lastName);
            }
            return null;
        }
        return null;
    }
}
