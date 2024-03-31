public class Notification {
    String notification;
    Observer observer;
    public Notification(String notification, Observer observer){
        this.notification=notification;
        this.observer=observer;
    }

    @Override
    public String toString() {
        return "Recieved by: " +observer +" " +notification + " " ;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
