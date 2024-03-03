import java.time.LocalDate;
import java.util.ArrayList;

public class FloweringPlant extends Plant{

    //FIELDS

    ArrayList<String> colors = new ArrayList<String>();
    ArrayList<String> features = new ArrayList<String>();

    /**
     * CONSTRUCTOR 
     * 
     * Constructs a new Flowering plant with the specified attributes, and makes call to superclass constructor Plant()
     *
     * Plant() parameters
     * @param commonName      the common name of the flowering plant.
     * @param genusSpecies    the scientific name (genus and species) of the flowering plant.
     * @param dateIntroduced the date when the flowering plant was introduced.
     * 
     * Flowering() parameters
     * @param flowerColor     the color of the flowers produced by the plant.
     * @param feature         a distinctive feature of the flowering plant.
     */
    public FloweringPlant(String commonName, String genusSpecies, LocalDate dateIntroduced, String flowerColor, String feature){
        super(commonName, genusSpecies, dateIntroduced);
        colors.add(flowerColor);
        features.add(feature);
        plantGroup = plantGroup.ANGIOSPERM;
    }

    //GETTERS

    public ArrayList<String> getFeatures(){
        return features;
    }

    public ArrayList<String> getColors(){
        return colors;
    }

    //SETTERS

    public void setFeatures(ArrayList<String> features){
        this.features = features;
    }

    public void setColors(ArrayList<String> colors){
        this.colors = colors;
    }


    //Methods

    public void addColor(String color){
        colors.add(color);
    }

    public void addFeature(String feature){
        features.add(feature);
    }


    /**
     * Returns a string representation of the colors associated with the flowering plant.
     *
     * @return A string containing the colors of the flowering plant, separated by commas.
     *         Returns an empty string if no colors are available.
     */
    public String colorsToString(){
        if (colors == null || colors.isEmpty()) {
            return "";
        }

        String outputString = "";
        for (int i = 0; i < colors.size(); i++) {
            outputString += String.format("%s%s", colors.get(i), (i < colors.size() - 1) ? ", " : "");
        }

        return outputString;
    }

    /**
    * Returns a string representation of the features associated with the flowering plant.
    *
    * @return A string containing the features of the flowering plant, separated by commas.
    *         Returns an empty string if no features are available.
    */
    public String featuresToString(){
        if (features == null || features.isEmpty()) {
            return "";
        }

        String outputString = "";
        for (int i = 0; i < features.size(); i++) {
            outputString += String.format("%s%s", features.get(i), (i < features.size() - 1) ? ", " : "");
        }

        return outputString;
    }

}



