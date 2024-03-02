import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Driver {

    Private static ArrayList<Plant> plantList = new ArrayList<Plant>();
    Private static Strning refiner;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


         //populatePlantList(); see commented out method below

         setRefiners();
         makePlant(sc);
         printPlantList();
       
        
        sc.close();     
    }

    //method below is sample code for how we might add the pre-made plants. NOTE: the date paramters in these are passed
    //in as strings NOT dates.
    /* 
    private static void populatePlantList(){
        Plant plant1 = new Plant("Supreme Leafer", "Plantimus Maximus", "2013-02-20");
        Plant plant2 = new Flowering("Pretty Boi", "Prettiest Boyicus", "2015-04-21", "bright pink", "the most beautiful bloom");
        Plant plant3 = new Flowering("Ugly Gross Stinky Flower", "Nasticus Bloomicus", "2012-06-18", "slime green", "awful stench")

    }
    */

    /**
    * Sets refiners for evaluating nursery experience with plants.  
    *
    * @param sc The Scanner object for user input.
    */
    private static void setRefiners(Scanner sc){
        System.out.println("What zone are you currently in?");
        //TODO: Add functionality lol
        
        boolean refinerAccepted = false;
        while(!refinerAccepted){
            System.out.println("How should we evaluate nursery experience with plant?  [Enter 'least' or 'most']");
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
        plantList.add(newPlant);

    }

    public static void printPlantList(){
        for(Plant plant : plantList){
            System.out.println(plant.id);
            System.out.println(plant.toString());
            System.out.println(plant.getClass().getSimpleName());
            
            System.out.print(refiner.equals("most") ? "most experience: " : "least experience: ");
            System.out.print(evaluatePlant(plant) + "\n");
            
            //TODO: needs functionality
            System.out.println("good for your zone: ")
        }

    }


    
}
