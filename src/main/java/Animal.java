import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal{
    public String name;
    public String health;
    public String age;
    public int id;
    public String species;

    // public Animal(String name, String health, String age){
    //     this.name = name;
    //     this.health = health;
    //     this.age = age;
    // }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    
    @Override
    public boolean equals(Object otherAnimal){
      if (!(otherAnimal instanceof Animal)) {
        return false;
      } else {
        Animal newAnimal = (Animal) otherAnimal;
        return this.getName().equals(newAnimal.getName()) &&
               this.getHealth().equals(newAnimal.getHealth()) &&
               this.getAge().equals(newAnimal.getAge());
      }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO animals (name, health, age, species) VALUES (:name, :health, :age, :species)";
          this.id = (int)
          con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("health", this.health)
            .addParameter("age", this.age)   
            .addParameter("species", species)        
            .executeUpdate()
            .getKey();
        }
    }

   

    public int getId() {
        return id;
    }
    

    public static Animal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Animal.class);
            return animal;
        }
    }


}