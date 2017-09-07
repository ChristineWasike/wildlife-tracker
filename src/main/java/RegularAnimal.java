import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class RegularAnimal extends Animal {

    public static final String DATABASE_SPECIES = "regular";

    public RegularAnimal(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        this.name = name;
        species = DATABASE_SPECIES;
    }

    public static List<RegularAnimal> all() {
        String sql = "SELECT * FROM animals WHERE species='regular';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(RegularAnimal.class);
        }
    }
}