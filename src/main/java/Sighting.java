import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Sighting implements DatabaseManagement{
    private String location;
    private String rangerName;
    private int id;
    private int animalId;
    private Timestamp timestamp;
            
    public Sighting(String location, String rangerName, int animalId) {
        if(rangerName.equals("")) {
            throw new IllegalArgumentException("Please enter Ranger name.");
        }        //Thrown  an exception for a user to be prompted to enter their Ranger name.
        this.location = location;
        this.rangerName = rangerName;
        this.animalId = animalId;
        // this.save();
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getId() {
        return id;
    }

    public String getTimeSeen(){
        return String.format("%1$TD %1$TR", timestamp);
      }
    
      public void setLocation(String location) {
        this.location = location;
      }
    
      public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
      }
    
      @Override
      public void save() {
        String sql = "INSERT INTO sightings (animalId, location, rangerName, timestamp) VALUES (:animalId, :location, :rangerName, now());";
    
        try (Connection con = DB.sql2o.open()) {
            this.id = (int) con.createQuery(sql, true)
                .addParameter("animalId", this.animalId)
                .addParameter("location", this.location)
                .addParameter("rangerName", this.rangerName)
                .executeUpdate()
                .getKey();
        }
      }
    
      public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings ORDER BY timestamp DESC;";
    
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                .throwOnMappingFailure(false)
                .executeAndFetch(Sighting.class);
        }
      }
    
      public static List<Sighting> allByAnimal(int animalId) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM sightings WHERE animalId = :animalId ORDER BY timestamp DESC";
          return con.createQuery(sql)
            .addParameter("animalId", animalId)
            .executeAndFetch(Sighting.class);
        }
      }
    
      public boolean equals(Object otherSighting){
        if(!(otherSighting instanceof Sighting)){
          return false;
        }else{
          Sighting newSighting = (Sighting) otherSighting;
          return this.getAnimalId()==newSighting.getAnimalId() && this.getRangerName().equals(newSighting.getRangerName());
        }
      }
    
      public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM sightings WHERE id=:id;";
          Sighting sighting = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Sighting.class);
          return sighting;
        } catch (IndexOutOfBoundsException exception) {
          return null;
        }
      }
    
      public void delete(){
        try(Connection con = DB.sql2o.open()) {
          con.createQuery("DELETE FROM sightings WHERE id=:id")
            .addParameter("id",id)
            .executeUpdate();
        }
      }
    
      public void update() {
          String sql = "UPDATE sightings SET location = :location, rangerName = :rangername WHERE id = :id";
    
        try(Connection con = DB.sql2o.open()) {
          con.createQuery(sql)
            .addParameter("location", location)
            .addParameter("rangername", rangerName)
            .addParameter("id", id)
            .throwOnMappingFailure(false)
            .executeUpdate();
        }
      }
    
}