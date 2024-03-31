
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class Group extends TreeSet<Student> {
    Assistant assistant;
    String ID;
    Comparator<Student> comp;
   public Group(String ID, Assistant assistant, Comparator<Student> comp){
        this.ID=ID;
        this.assistant=assistant;
        this.comp=comp;
    }
    public Group(String ID, Assistant assistant){
        this.assistant = assistant;
        this.ID = ID;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Group " + " ID: " +ID + "Responsisble assistant: " + assistant;
    }
}

