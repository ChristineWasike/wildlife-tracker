import org.sql2o.*;
import java.util.List;

public class RegularAnimal extends Animal {
    public static final String  DATABASE_SPECIES = "regular";

    public RegularAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        species = DATABASE_SPECIES;
    }

    public static List<RegularAnimal> all() {
        String sql = "SELECT * FROM animals WHERE species='regular';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
            .executeAndFetch(RegularAnimal.class);
        }
    }
}