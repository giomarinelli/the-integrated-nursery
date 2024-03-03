import java.time.LocalDate;

public class Tree extends Plant {
    
    growSpeed growSpeed;
    enum growSpeed
    {
        SLOW,MEDIUM,FAST
    }
    
    /*
    * Parametric Tree Constructor
    * @param: growSpeed enum growSpeed
    * 
    */
    public Tree(String commonName, String genusSpecies, LocalDate dateIntroduced){
        super(commonName, genusSpecies, dateIntroduced);
        this.growSpeed = growSpeed.MEDIUM;
        plantGroup = plantGroup.GYMNOSPERM;
    }
}
