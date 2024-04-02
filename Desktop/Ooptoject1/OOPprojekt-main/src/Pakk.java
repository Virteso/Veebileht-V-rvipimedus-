import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pakk {
    private List<Kaart> kaardid;

    public Pakk() {
        kaardid = Kaart.looTaisPakk();
        segaPakk();
    }

    private void segaPakk() {
        Collections.shuffle(kaardid);
    }

    public Kaart võtaKaart() {
            if (!kaardid.isEmpty()) {
                return kaardid.remove(0);
            }

            return null; // Või käsitle tühja paki olukorda
    }

    public int pakiSuurus() {
        return kaardid.size();
    }
}
