public class Mängukontroll {


    public boolean kasTapab(Kaart manginudKaart, Kaart laualOlevKaart, Kaart trump) { // kontrollib kas see kaart tappab või miite.
        int manginudKaartnumber = 0;
        int laulaolevkaartnumber = 0;
        String[] numbrid = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Poiss", "Emand", "Kuningas", "Äss"};

        for (int i = 0; i < numbrid.length; i++) {
            if (manginudKaart.getKaardiNumber().equals(numbrid[i])) {
                manginudKaartnumber = i + 2;
            }
            if (laualOlevKaart.getKaardiNumber().equals(numbrid[i])) {
                laulaolevkaartnumber = i + 2;
            }
        }
        if (!laualOlevKaart.trumpKontroll(trump, laualOlevKaart)) {
            if (manginudKaart.trumpKontroll(trump, manginudKaart)) {
                return true;
            }
            if (manginudKaart.getKaardiMast().equals(laualOlevKaart.getKaardiMast()) && manginudKaartnumber > laulaolevkaartnumber) {
                return true;
            }
        }
        if (laualOlevKaart.trumpKontroll(trump, laualOlevKaart)) {
            return manginudKaart.trumpKontroll(trump, manginudKaart) && manginudKaartnumber > laulaolevkaartnumber;
        }
        return false;
    }


    public int võitjaKontroll(Arvuti arvuti, Mängija mängija, Pakk pakk) { // Meetod mis kontrollib kas on praegu võitja või mitte, ja kui jah, siis kes see on.;
        if (pakk.pakiSuurus() == 0) {
            if (arvuti.kaardidKäes.isEmpty() && !mängija.kaardidKäes.isEmpty()) {
                return 1;
            } if(!arvuti.kaardidKäes.isEmpty() && mängija.kaardidKäes.isEmpty())
                return 2;
            }
        return 0;
    }
}




