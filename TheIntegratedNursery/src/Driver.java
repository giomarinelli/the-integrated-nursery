import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;




public class Driver {

    private static ArrayList<Plant> plantList = new ArrayList<Plant>();
    private static String refiner;
    static int currentZone;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
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
   
    /**
    * Sets refiners for evaluating nursery experience with plants.  
    *
    * @param sc The Scanner object for user input.
    */
    private static void setRefiners(Scanner sc){
        System.out.println("What zone are you currently in?");
        
        currentZone = sc.nextInt();
        
        boolean refinerAccepted = false;
        System.out.println("How should we evaluate nursery experience with plant?  [Enter 'least' or 'most']");
        while(!refinerAccepted)
        {
            
            refiner = sc.nextLine();
            if (refiner.equals("most") || refiner.equals("least")){
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
        LocalDate date = LocalDate.parse(sc.nextLine());
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
            
            System.out.println(refiner.equals("most") ? 
            "most experience: " + Plant.evaluators.get("most_experienced").test(plant) :
            "least experience: " + Plant.evaluators.get("least_experienced").test(plant) );
            
            System.out.printf("good for your zone: %s\n", plant.growsInZone(currentZone));
        }

    }


    
}
