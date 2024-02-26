import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.*;

public class Plant {
    long id;
    String genusSpecies;
    String commonName;
    LocalDate dateIntroduced;
    public static HashMap<Integer, Zone> zones = new HashMap<Integer, Zone>();


    enum plantGroup {
        ANGIOSPERMS,
        GYMNOSPERMS,
        PTERIDOPHYTES,
        BRYOPHTES
    }

    private static Plant mostExperiencedPlant;
    private static Plant leastExperiencedPlant;

    public static HashMap<String, Predicate<Plant>> evaluators;
    static {
        evaluators = new HashMap<>();
        evaluators.put("most_experienced", plant -> plant == mostExperiencedPlant);
        evaluators.put("least_experienced", plant -> plant == leastExperiencedPlant);
    }

    public Plant(String commonName, LocalDate dateIntroduced) {
        this.commonName = commonName;
        this.dateIntroduced = dateIntroduced;

        experienceCheck();
    }

    /*
     * Checks and sets if current Plant is most or least experienced Plant
     */
    public void experienceCheck(){            
        // Update most and least experienced plants based on the earliest dateIntroduced
            if (mostExperiencedPlant == null || dateIntroduced.compareTo(mostExperiencedPlant.dateIntroduced) < 0) {
                mostExperiencedPlant = this;
            }
            if (leastExperiencedPlant == null || dateIntroduced.compareTo(leastExperiencedPlant.dateIntroduced) > 0) {
                leastExperiencedPlant = this;
            }
        }

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
        experienceCheck();
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

// doesnt work if capital letters are sequential. rare case.
    public static boolean validateGenusSpecies(String genusSpecies)
     {
        if ((genusSpecies.length() >= 7 && genusSpecies.length() <= 39)) 
        {

            String regex = "[A-Z]+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(genusSpecies);


            
            while (matcher.find()) 
            {
                if (!((matcher.start()) == 0 && matcher.end() >= 1)) 
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

    public static boolean vaildateCommonName(String commonName)
    {
        String regex = "[A-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(commonName);

        while (matcher.find()) {
            if (((matcher.start()) != 0)) {
                System.err.println("Invalid name. Common name must start with a capital letter.");
                return false;
            } else {
                return true;
            }
            

        }
        return false;
    }
    

}
