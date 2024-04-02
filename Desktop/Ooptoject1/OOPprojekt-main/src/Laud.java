import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Laud {
    private List<Kaart> laualOlevadKaardid = new ArrayList<>();

    public void lisaKaart(Kaart kaart) {
        laualOlevadKaardid.add(kaart); // Add a played card to the board
    }

    public List<Kaart> getLaualOlevadKaardid() {
        return laualOlevadKaardid;
    }

    public void prindiLaud() {
        System.out.println("Laual olevad kaardid:");
        if (laualOlevadKaardid.isEmpty()) {
            System.out.println("Laud on tühi.");
        } else {
            for (int i = 0; i < laualOlevadKaardid.size(); i++) {
                System.out.print(laualOlevadKaardid.get(i)+" | ");
            }
        }
    }

    public Kaart viimane(){
        return laualOlevadKaardid.getLast();
    }

    public void tühista(){
        laualOlevadKaardid.removeAll(laualOlevadKaardid);
    }

    public boolean onTühi() {
        return laualOlevadKaardid.isEmpty();
    }


}
