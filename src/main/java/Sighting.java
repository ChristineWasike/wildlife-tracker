import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public class Sighting {
    private String location;
    private String rangerName;
    private int id;
    private int animalId;
            
    public Sighting(String location, String rangerName, int id) {
        this.location = location;
        this.rangerName = rangerName;
        this.id = id;
    }
}