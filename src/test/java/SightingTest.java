import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting("Zone B","Michael", 1 );
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test 
  public void getLocation_sightingInstantiatesWithLocation_ZoneB() {
    Sighting testSighting = new Sighting("Zone B","Michael", 1 );
    assertEquals("Zone B", testSighting.getLocation());
  }
}