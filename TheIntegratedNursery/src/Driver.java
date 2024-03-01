import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

         ArrayList<Plant> plantList = new ArrayList<Plant>();

         //populatePlantList(); see commented out method below
         makePlant(sc);
         System.out.println(plantList);
       
        
        sc.close();     
    }

    //method below is sample code for how we might add the pre-made plants. NOTE: the date paramters in these are passed
    //in as strings NOT dates.
    /* 
    private static void populatePlantList(){
        Plant plant1 = new Plant("Supreme Leafer", "Plantimus Maximus", "2013-02-20");
        Plant plant2 = new Flowering("Pretty Boi", "Prettiest Boyicus", "2015-04-21", "bright pink", "the most beautiful bloom");
        Plant plant3 = new Flowering("Ugly Gross Stinky Flower", "Nasticus Bloomicus", )

    }
    */

    
    /*
    * makePlant - Uses user input to create a Plant
    * @param: Scanner sc for input
    * @return: new Plant with validated fields
    */
    private static Plant makePlant(Scanner sc)
    {
        
        System.out.println("Enter the common name of the plant:");
        
        String commonName = sc.nextLine();
        while (!(Plant.vaildateCommonName(commonName))) commonName = sc.nextLine();

        System.out.println("Enter the scientific name of the plant:");
        String genusSpecies = sc.nextLine();
        while (!(Plant.validateGenusSpecies(genusSpecies))) genusSpecies = sc.nextLine();

        System.out.println("Enter the date when the plant was first introduced [YYYY-MM-DD]");
        LocalDate date = LocalDate.parse(sc.nextLine());
        return new Plant(commonName, genusSpecies, date);

    }


    
}
