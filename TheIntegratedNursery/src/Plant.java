import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.*;

public class Plant {

//FIELDS
    long id;
    static long plantsCreated = 0;
    String genusSpecies;
    String commonName;
    LocalDate dateIntroduced;
    plantGroup plantGroup;
    
    enum plantGroup {
        ANGIOSPERMS,
        GYMNOSPERMS,
        PTERIDOPHYTES,
        BRYOPHTES
    }
    
    ArrayList<Zone> zones;
    
    private static Plant mostExperiencedPlant;
    private static Plant leastExperiencedPlant;

    public static HashMap<String, Predicate<Plant>> evaluators;
    static {
        evaluators = new HashMap<>();
        //TODO: check that i didn't mess up the compareTo() method usage here
        evaluators.put("most_experienced", plant -> plant.getDateIntroduced().compareTo(mostExperiencedPlant.getDateIntroduced()) == 0);
        evaluators.put("least_experienced",plant -> plant.getDateIntroduced().compareTo(leastExperiencedPlant.getDateIntroduced()) == 0);
    }

    //Constructor
     public Plant(String commonName, String genusSpecies, LocalDate dateIntroduced) {
        plantsCreated++;
        this.id = plantsCreated;
        this.genusSpecies = genusSpecies;
        this.commonName = commonName;
        this.dateIntroduced = dateIntroduced;
        this.plantGroup = plantGroup.ANGIOSPERMS; // TODO: Figure out what to do with these plantGroup enums lol
        
        

        experienceCheck(this);
    }

    //Temporary empty Plant() constructor to silence errors in Trees and Flowering classes
    public Plant(){
    }
    
//Old Plant comparator method (commented out for backup purposes.)   
/**
    private static final PlantComparator plantComparator = new PlantComparator();
    

    
    public static Predicate<Plant> mostExperienced() 
    {
        return plant -> plant.equals(Collections.max(plantList, plantComparator));
    }

    
    public static Predicate<Plant> leastExperienced() {
        return plant -> plant.equals(Collections.min(plantList, plantComparator));
    }

*/


   
    public long getId() {
        return this.id;
    }

    public String getGenusSpecies() {
        return this.genusSpecies;
    }

    public void setGenusSpecies(String genusSpecies) {
        if (validateGenusSpecies(genusSpecies) == true)
        this.genusSpecies = genusSpecies;

    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        if(vaildateCommonName(commonName) == true)
        this.commonName = commonName;

    }

    public LocalDate getDateIntroduced() {
        return this.dateIntroduced;
    }

    public void setDateIntroduced(LocalDate dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
        experienceCheck(this);
    }

    public HashMap<Integer, Zone> getZones() {
        return zones;
    }

    public void setZones(HashMap<Integer, Zone> zones) {
        this.zones = zones;
      }

    @Override
    public String toString() {
        return String.format("%s (%s)", commonName, genusSpecies);

    }

    public boolean growsInZone(int zoneNumber) {
        if (zones.containsKey(zoneNumber))
            return true;
        return false;
    }


//Methods 

    /*
    * Checks and sets if current Plant is most or least experienced Plant
    */
    public static void experienceCheck(Plant plant){            
        // Update most and least experienced plants based on the earliest dateIntroduced
            if (mostExperiencedPlant == null || plant.dateIntroduced.compareTo(mostExperiencedPlant.dateIntroduced) < 0) {
                mostExperiencedPlant = plant;
            }
            if (leastExperiencedPlant == null || plant.dateIntroduced.compareTo(leastExperiencedPlant.dateIntroduced) > 0) {
                leastExperiencedPlant = plant;
            }
    }
    


// Matcher Declaration
static final String capsString = "[A-Z]+";
static final Pattern capsPattern = Pattern.compile(capsString);

/*
* validateGenusSpecies - validates the length and character requirement of a scientific name (7-9 chars, 1st char caps ONLY)
  Probably very inefficient, but works!
* @param: String genusSpecies to be validated
* @return: true if name is valid
*/
    public static boolean validateGenusSpecies(String genusSpecies)
     {
        if ((genusSpecies.length() >= 7 && genusSpecies.length() <= 39)) 
        {

            Matcher caps = capsPattern.matcher(genusSpecies);
            
            if (!(caps.find())) 
            {
                System.err.println("Invalid. In scientific name, the first letter must be capitalized.");
                return false; 
            }

            while (caps.find())
            {
                if ((caps.end() != 1))
                {
                    System.err.println("Invalid. In scientific name, only the first letter must be capitalized.");
                    return false;
                }

            }

        } 
        else 
        {
            System.err.println("Invalid length. Scientific name must be between 7 and 39 characters inclusive.");

            return false;
        }

        return true;
    }

    /*
    * validateCommonName - checks if name starts with uppercase letter
    * @param: String commonName to be validated
    * @return: true if name is valid
    */
    public static boolean vaildateCommonName(String commonName)
    {
        
        char first = commonName.charAt(0);

        if (Character.isUpperCase(first)) return true;
        
        else
        {
            System.out.println("Invalid. Common name must start with an uppercase letter.");
            return false;
        }
        
    }
    

}
