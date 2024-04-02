import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Pakk pakk = new Pakk();
        Mängija mängija = new Mängija();
        Arvuti arvuti = new Arvuti(); // Võib asendada Arvuti klassiga, kui on eraldi loogika
        Laud laud = new Laud();
        Mängukontroll mängukontroll = new Mängukontroll();

        // Jagame mängijale ja arvutile alguses 6 kaarti
        mängija.võtaKaardid(arvuti, mängija, pakk );
        arvuti.võtaKaardid(arvuti, mängija, pakk);
        Scanner scanner = new Scanner(System.in);
        boolean mängijaKäik = true; // True, kui on mängija käik, false arvuti käiguks
        int vooruNumber = 1;

        Kaart trump = pakk.võtaKaart();

        while (true) { // kogu mangi tsukkel
            while (true) { // vooru tsukkel
                System.out.println("Voor: " + vooruNumber + ". Kaarte pakis: " + pakk.pakiSuurus());
                System.out.println("Kaardid käes: " + mängija.kaartideArv() + ". Arvuti kaarte käes: " + arvuti.kaartideArv());
                System.out.println("Trump: " + trump);

                laud.prindiLaud();
                if (mängijaKäik) {
                    int kaardiIndeks = 1;
                    System.out.println();
                    System.out.println("Sinu kaardid: ");
                    for (Kaart kaart : mängija.kaardidKäes) {
                        System.out.println((kaardiIndeks++) + " " + kaart);
                    }
                    if (laud.onTühi()) {
                        //Muuda seda nii, et kui laud on tühi, ss saab mängida kaardi, kui laud pole tühi(ehk kui arvuti on midagi mänginud) siis tuleb eraldi uus while loop kus möngija saab tegutseda(korja, tapa jne)
                        System.out.println("Sinu käik. Vali kaart, mida mängida (1-" + mängija.kaartideArv() + "), või sisesta 'stopp' lõpetamiseks");
                        String input = scanner.nextLine();
                        if ("stopp".equalsIgnoreCase(input)) break;

                        try {
                            int valitudIndex = Integer.parseInt(input) - 1;
                            if (valitudIndex < 0 || valitudIndex >= mängija.kaartideArv()) {
                                System.out.println("Vigane sisend. Palun proovi uuesti.");
                                continue;
                            }
                            mängija.mängiKaart(valitudIndex, laud); // lihtsalt panneb esimene kaart.
                        } catch (NumberFormatException e) {
                            System.out.println("Palun sisesta sobiv number.");
                            continue;
                        }
                    } else {
                        //Muuda seda nii, et kui laud on tühi, ss saab mängida kaardi, kui laud pole tühi(ehk kui arvuti on midagi mänginud) siis tuleb eraldi uus while loop kus möngija saab tegutseda(korja, tapa jne)
                        System.out.println("Sinu käik. Vali kaart, mida mängida (1-" + mängija.kaartideArv() + "), siseta 'lõpeta' seelks et lõpeta  voor, või sisesta 'stopp' lõpetamiseks.");
                        laud.prindiLaud();
                        String input = scanner.nextLine();

                        if ("stopp".equalsIgnoreCase(input)) break;
                        if ("lõpeta".equalsIgnoreCase(input)) {
                            mängija.lõpeta(mängijaKäik, laud, arvuti, mängija, pakk);
                            mängijaKäik = false;
                        }
                        try {
                            int valitudIndex = Integer.parseInt(input) - 1;
                            if (valitudIndex < 0 || valitudIndex >= mängija.kaartideArv()) {
                                System.out.println("Vigane sisend. Palun proovi uuesti.");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Palun sisesta sobiv number.");
                            continue;
                        }
                        int valitudIndex = Integer.parseInt(input) - 1;
                        if (mängija.juurdePanna(valitudIndex, laud)) {
                            mängija.mängiKaart(valitudIndex, laud);
                            int võitja = mängukontroll.võitjaKontroll(arvuti, mängija, pakk);
                            if (võitja != 0) {
                                if (võitja == 1) {
                                    System.out.println("Võitis Arvuti");
                                    break;
                                } else {
                                    System.out.println("Võitis Mängija");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("See kaart ei saa juurde panna");
                            continue;
                        }

                    }

                    arvuti.valiTegevusMängijaKäik(laud, mängijaKäik, mängukontroll, trump, arvuti, mängija, pakk); 

                } else {
                    if (laud.onTühi()) {

                        System.out.println("Arvuti Käik:");
                        arvuti.mangiEsimeneKaart(laud); // arvuti paneb esimene kaart.
                        laud.prindiLaud();

                        int võitja = mängukontroll.võitjaKontroll(arvuti, mängija, pakk);
                        if (võitja != 0) {
                            if (võitja == 1) {
                                System.out.println("Võitis Arvuti");
                                break;
                            } else {
                                System.out.println("Võitis Mängija");
                                break;
                            }
                        }
                    }

                    int kaardiIndeks = 1;
                    System.out.println();
                    System.out.println("Sinu kaardid: ");
                    for (Kaart kaart : mängija.kaardidKäes) {
                        System.out.println((kaardiIndeks++) + " " + kaart);
                    }
                    System.out.println("Sinu käik. Vali kaart, mida mängida (1-" + mängija.kaartideArv() + "),või siseta 'korja' selleks et kaardid võtta.  ,või sisesta 'stopp' lõpetamiseks.");
                    laud.prindiLaud();
                    String input = scanner.nextLine();

                    if ("stopp".equalsIgnoreCase(input)) break;
                    if ("korja".equalsIgnoreCase(input)) {
                        mängija.korja(laud, arvuti, mängija, pakk); // võttab kaardid laualt
                    }
                    try {
                        int valitudIndex = Integer.parseInt(input) - 1;
                        if (valitudIndex < 0 || valitudIndex >= mängija.kaartideArv()) {
                            System.out.println("Vigane sisend. Palun proovi uuesti.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Palun sisesta sobiv number.");
                        continue;
                    }
                    int valitudIndex = Integer.parseInt(input) - 1;
                    if (mängukontroll.kasTapab(mängija.kaardidKäes.get(valitudIndex), laud.viimane(), trump)) { // kontrollib kas kaart tapab või mitte.
                        mängija.mängiKaart(valitudIndex, laud);
                    } else {
                        System.out.println("See kaart ei saa tappa");
                    }
                    if (!laud.onTühi()) {
                        System.out.println("Arvuti Käik:");
                        mängijaKäik = arvuti.valiTegevusArvutiKäik(laud, mängijaKäik, arvuti, mängija, pakk);
                        int võitja = mängukontroll.võitjaKontroll(arvuti, mängija, pakk); // kontrollib kas on juba võitja või mitte;
                        if (võitja != 0) {
                            if (võitja == 1) {
                                System.out.println("Võitis Arvuti");
                                break;
                            } else {
                                System.out.println("Võitis Mängija");
                                break;
                            }
                        }
                    }
                }
            }
            scanner.close();
            System.out.println("Mäng läbi. Aitäh mängimast!");
            break;
        }
    }
}


