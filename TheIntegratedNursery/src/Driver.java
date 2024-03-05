/*
* Driver.java
* @author Gio Marinelli, Ryan Jones
* @version  2024-03-04
*/
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Driver {

    private static ArrayList<Plant> plantList = new ArrayList<Plant>();
    private static String refiner;
    static int currentZone = -1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //EXTRA CREDIT  ?
         Plant.evaluators.put("gymnosperm", p -> p.getPlantGroup() == Plant.plantGroup.GYMNOSPERM);

         populatePlantList(); 
         setRefiners(sc);
         makePlant(sc);
         printPlantList();
       
        sc.close();     
    }

    
    
    private static void populatePlantList(){
        plantList.add(new FloweringPlant("Pretty Boi", "Prettiest boyicus", LocalDate.parse("2018-03-14"), "bright pink", "the most beautiful bloom"));
        plantList.add(new FloweringPlant("Ugly Gross Stinky Flower", "Nasticus Bloomicus", LocalDate.parse("2017-03-14"), "slime green", "awful stench"));
        plantList.add(new Tree("Socotra dragon tree", "Dracaena cinnabari", LocalDate.parse("1999-01-01"), Tree.growSpeed.SLOW));
        plantList.add(new Plant("Supreme Leafer", "Plantimus maximus", LocalDate.parse("2019-03-14")));

    }
   
    /*
    * setRefiners - takes user input for zone and evaluator
    * @param: Scanner sc for user input
    * @return: void
    */
    private static void setRefiners(Scanner sc){
        System.out.println("What zone are you currently in? [1-11]"); 
        int input = -1;
        while (currentZone == -1) {
            try {
                input = sc.nextInt();

                if (input > 0 && input < 12) {
                    currentZone = input;
                } else {
                    System.out.println("Incorrect Format. Must be a integer [1-11]");
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect Format. Please enter a valid integer.");
                
                sc.nextLine();
            }
        }
        
        boolean refinerAccepted = false;
        System.out.println("How should we evaluate nursery experience with plant?  [Enter 'least', 'most' or a custom evaluator]");
        while(!refinerAccepted)
        {
            
            refiner = sc.nextLine();
            if (Plant.evaluators.containsKey(refiner))
            {
                refinerAccepted = true;
            }
            
            }
        }
    

    
    /*
    * makePlant - Uses user input to create a Plant
    * @param: Scanner sc for input
    * @return: new Plant with validated fields
    */
    private static void makePlant(Scanner sc)
    {
        
        System.out.println("Enter the common name of the plant:");
        
        String commonName = sc.nextLine();
        while (!(Plant.vaildateCommonName(commonName))) commonName = sc.nextLine();

        System.out.println("Enter the scientific name of the plant:");
        String genusSpecies = sc.nextLine();
        while (!(Plant.validateGenusSpecies(genusSpecies))) genusSpecies = sc.nextLine();

        System.out.println("Enter the date when the plant was first introduced [YYYY-MM-DD]");
        LocalDate date = null;
        while (date == null) {
            try {
                date = LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date. Please follow format [YYYY-MM-DD]");
            }
        
        }
        Plant newPlant = new Plant(commonName, genusSpecies, date);
        newPlant.zones.add(currentZone);
        plantList.add(newPlant);

    }

    public static void printPlantList(){
        for(Plant plant : plantList){
            System.out.println();
            System.out.println(plant.id);
            System.out.println(plant.toString());
            System.out.println(plant.getClass().getSimpleName());
            System.out.printf("introducted on %s\n", plant.getDateIntroduced());

            if(plant.getClass().getSimpleName().equals("FloweringPlant")){
                System.out.println(((FloweringPlant) plant).getDescription());
            }
            if(plant.getClass().getSimpleName().equals("Tree")){
                System.out.println(((Tree) plant).getDescription());
            }
            if (refiner.equals("most") || refiner.equals("least"))
            {
            System.out.println(refiner.equals("most") ? 
            "most experience: " + Plant.evaluators.get("most").test(plant) :
            "least experience: " + Plant.evaluators.get("least").test(plant) );
            }

            if (refiner.equals("gymnosperm"))
            {
                
                System.out.printf("Is a gymnosperm?: %s\n", Plant.evaluators.get("gymnosperm").test(plant) );
            }
            
            
            System.out.printf("good for your zone: %s\n", plant.growsInZone(currentZone));
            Plant.evaluators.get("most").test(plant);
        }

    }

    

    

    
}
