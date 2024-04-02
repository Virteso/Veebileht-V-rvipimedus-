import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Kaart {
    private String kaardiMast;
    private String kaardiNumber;
    private boolean trump;
    public Kaart(String mast, String number) {
        this.kaardiMast = mast;
        this.kaardiNumber = number;
    }
    public String getKaardiMast() {
        return kaardiMast;
    }
    public String getKaardiNumber() {
        return kaardiNumber;
    }
    public String toString() {
        return kaardiMast + ":" + kaardiNumber;
    }

    public boolean isTrump() {
        return trump;
    }

    public static List<Kaart> looTaisPakk() {
        String[] masts = {"Ärtu", "Ruutu", "Risti", "Poti"};
        // String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Poiss", "Emand", "Kuningas", "Äss"};
        String[] numbers = {"Poiss", "Emand", "Kuningas", "Äss"};
        List<Kaart> pakk = new ArrayList<>();
        for (String mast : masts) {
            for (String number : numbers) {
                pakk.add(new Kaart(mast, number));
            }
        }
        return pakk;
    }

    public boolean trumpKontroll(Kaart trumpMangus,Kaart kaart){ // kontrollib kas see kart on trump või mitte.
        trump =  kaart.getKaardiMast().equals(trumpMangus.getKaardiMast());
        return trump;
    }
    public static void segaPakk(List<Kaart> pakk) {
        Collections.shuffle(pakk);
    }



}
