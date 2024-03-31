
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parent extends User implements Observer{
    private ArrayList<Notification> notifications ;
    public Parent(String firstName, String lastName ){
        super(firstName,lastName);
        notifications= new ArrayList<Notification>();
    }
    public void update(Notification notification){
        this.notifications.add(notification);
        System.out.println(notification);
    }
    public void getAllNotifications(Observer observer){
        System.out.println(notifications);
    }

}
