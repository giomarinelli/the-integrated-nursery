import java.util.Comparator;

public class PlantComparator implements Comparator<Plant> {
    @Override
    public int compare(Plant plant1, Plant plant2) {
        return plant1.getDateIntroduced().compareTo(plant2.getDateIntroduced());
    }
}
