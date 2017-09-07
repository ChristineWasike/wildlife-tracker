import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public abstract class Animal {
    public String name;
    public String health;
    public String age;
    public int id;
    public String species;

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName()) && this.getHealth().equals(newAnimal.getHealth())
                    && this.getAge().equals(newAnimal.getAge());
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, health, age, species) VALUES (:name, :health, :age, :species)";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name)
                    .addParameter("health", this.health).addParameter("age", this.age).addParameter("species", species)
                    .throwOnMappingFailure(false)
                    .executeUpdate().getKey();
        }
    }

    public int getId() {
        return id;
    }



    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE from animals WHERE id = :id";
            con.createQuery(sql).addParameter("id", id).throwOnMappingFailure(false).executeUpdate();
            String sql2 = "DELETE from sightings WHERE animal_id = :id";
            con.createQuery(sql2).addParameter("id", id).throwOnMappingFailure(false).executeUpdate();
        }
    }

    public static Animal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

}