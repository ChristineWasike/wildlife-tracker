import org.sql2o.*;
import java.util.List;

public class EndangeredAnimal extends Animal {
    public static final String  DATABASE_SPECIES = "endangered";

    public EndangeredAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        species = DATABASE_SPECIES;
    }

    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE species='endangered'";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
            .executeAndFetch(EndangeredAnimal.class);
        }
    }
}