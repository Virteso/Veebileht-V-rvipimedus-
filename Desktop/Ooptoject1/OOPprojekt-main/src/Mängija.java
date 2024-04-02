import java.util.ArrayList;
import java.util.List;

public class Mängija {
    protected List<Kaart> kaardidKäes = new ArrayList<>();

    public void mängiKaart(int index, Laud laud) {
        Kaart playedCard = kaardidKäes.remove(index);
        System.out.println("Mängisite: " + playedCard);
        laud.lisaKaart(playedCard); // Add the played card to the board
    }

    public void võtaKaardid(Arvuti arvuti, Mängija mängija, Pakk pakk) { // võttavad nii palju kaardi kui nendel on vaja, või niipalju, kui pakkis kokku on.
        int vottakardidArvuti = 6 - arvuti.kaartideArv();
        int vottakardidMängija = 6 - mängija.kaartideArv();
        if (vottakardidMängija + vottakardidArvuti <= pakk.pakiSuurus()) {
            for (int i = 0; i < vottakardidArvuti; i++) {
                arvuti.kaardidKäes.add(pakk.võtaKaart());
            }
            for (int i = 0; i < vottakardidMängija; i++) {
                mängija.kaardidKäes.add(pakk.võtaKaart());
            }
        }else{
            for (int i = 0; i < pakk.pakiSuurus(); i++) {
                arvuti.kaardidKäes.add(pakk.võtaKaart());
                mängija.kaardidKäes.add(pakk.võtaKaart());
            }
        }
    }

    public void lõpeta(boolean mängijaKäik, Laud laud, Arvuti arvuti, Mängija mängija, Pakk pakk) { // lõpetab käiku
        käikukontroll(!mängijaKäik);
        laud.tühista();
        if (pakk.pakiSuurus() > 0) {
            võtaKaardid(arvuti, mängija, pakk);
        }
    }

    public void korja(Laud laud, Arvuti arvuti, Mängija mängija, Pakk pakk) { // mängija korjab kaardid mis on laua peal
        kaardidKäes.addAll(laud.getLaualOlevadKaardid());
        laud.tühista();
        if (pakk.pakiSuurus() > 0) {
            võtaKaardid(arvuti, mängija, pakk);
        }
    }

    public boolean käikukontroll(boolean mängijakäik) { // tagastab kelle käiku on.
        return mängijakäik;
    }

    public boolean juurdePanna(int index, Laud laud) { // kontrollib kas võib juurde panna  või mitte
        boolean onpannav = false;
        for (Kaart kaart1 : laud.getLaualOlevadKaardid()) {
            if (kaart1.getKaardiNumber().equals(kaardidKäes.get(index).getKaardiNumber())) {
                onpannav = true;
                break;
            }
        }
        return onpannav;
    }

    public int kaartideArv() {
        return kaardidKäes.size();
    }


}
