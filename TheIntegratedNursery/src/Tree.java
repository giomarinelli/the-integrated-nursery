/*
* Tree.java
* @author Gio Marinelli, Ryan Jones
* @version  2024-03-04
*/

import java.time.LocalDate;

public class Tree extends Plant {
    
    growSpeed growingSpeed;
    enum growSpeed
    {
        SLOW,MEDIUM,FAST
    }
    
    /*
    * Parametric Tree Constructor
    * @param: growSpeed enum growSpeed
    * 
    */
    public Tree(String commonName, String genusSpecies, LocalDate dateIntroduced, growSpeed growSpeed){
        super(commonName, genusSpecies, dateIntroduced);
        this.growingSpeed = growSpeed;
        group = plantGroup.GYMNOSPERM;
    }

    /*
    * getDescription
    * @param: none
    * @return: String formatted to describe tree
    */
    public String getDescription(){
        return String.format("a %s growing tree", growingSpeed).toLowerCase();
    }
}
