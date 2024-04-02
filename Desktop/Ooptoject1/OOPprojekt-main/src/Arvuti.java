import java.util.Random;

public class Arvuti extends Mängija {


    @Override
    public void mängiKaart(int index, Laud laud) {
        Kaart playedCard = kaardidKäes.remove(index);
        System.out.println("Arvuti mängis: " + playedCard);
        laud.lisaKaart(playedCard); // Add the played card to the board
    }


    // Proovisin arvutile natuke intelekketi lisada, mis kasutab Ranodom, selleks et otsustada mis on vaja teha
    public void mangiEsimeneKaart(Laud laud) { // Kui arvutil on vaja panna esimene kaart laua peal, siis ta väga tõonälikult ei hakka trumpiga mängida. Ainult olukorras kui tal on kõik kaardid trumpid.
        int trumpidKäes = 0;
        for (Kaart kaart : kaardidKäes) {
            if (kaart.isTrump()) {
                trumpidKäes++;
            }
        }
        for (Kaart kaart : kaardidKäes) {
            if (trumpidKäes != 6) {
                if (!kaart.isTrump()) {
                    mängiKaart(kaardidKäes.indexOf(kaart), laud);
                    break;
                }
            } else {
                mängiKaart(kaardidKäes.indexOf(kaart), laud);
                break;
            }

            }
        }


        /******
         *Siin arvutil on kaks võimalused mis saab teha, kas juurde  panna või lõpetada voor
         * Kui tal on võimalus juurde panna, siis suure tõonäsusega ta panneb
         * Aga 20% tõenäosusega või kui sellist v]imalust ei ole, siis lõpeb voor.
        ****/
        public boolean valiTegevusArvutiKäik (Laud laud,boolean mängijaKäik, Arvuti arvuti, Mängija mängija, Pakk pakk){
            Random random = new Random();
            int tõenäosusJuurdePanna = random.nextInt(1, 100);
            int tõenäosusTrumpigamängida = random.nextInt(1, 100);
            int counter = 0;
            for (Kaart kaart : arvuti.kaardidKäes) {
                if (tõenäosusJuurdePanna >= 20) {
                    if (juurdePanna(arvuti.kaardidKäes.indexOf(kaart), laud)) {
                        if (kaart.isTrump()) {
                            if (tõenäosusTrumpigamängida >= 90) {
                                mängiKaart(arvuti.kaardidKäes.indexOf(kaart), laud);
                                System.out.println("Arvuti otsustas juurde panna");
                                return false;
                            }
                        } else {
                            System.out.println("Arvuti otsustas juurde panna: " + arvuti.kaardidKäes.get(arvuti.kaardidKäes.indexOf(kaart)));
                            mängiKaart(arvuti.kaardidKäes.indexOf(kaart), laud);
                            return false;
                        }
                    } else {
                        counter++;
                    }
                } else {
                    System.out.println("Lopeta voor");
                    lõpeta(mängijaKäik, laud, arvuti, mängija, pakk);
                    return true;
                }

            }if (counter == arvuti.kaartideArv()){
                System.out.println("Lopeta voor");
                lõpeta(mängijaKäik, laud, arvuti, mängija, pakk);
                return true;
            }
            return false;
        }


    // sisuliselt sama loogika, aga siin arvuti saab tappa või korjata kõik kaardid mis on laua peal.
        public void valiTegevusMängijaKäik (Laud laud,boolean mängijaKäik, Mängukontroll mängukontroll, Kaart
        trump, Arvuti arvuti, Mängija mängija, Pakk pakk){
            Random random = new Random();
            int tõenäosus = random.nextInt(1, 100);

            int counter = 0;
            for (Kaart kaart : arvuti.kaardidKäes) {
                if (tõenäosus <= 90) {
                    if (mängukontroll.kasTapab(kaart, laud.viimane(), trump)) {
                        System.out.println("Arvuti otsustas tappa kaardiga: " + kaart);
                        mängiKaart(arvuti.kaardidKäes.indexOf(kaart), laud);
                        break;
                    } else {
                        counter++;
                    }
                } else {
                    System.out.println("Arvuti otsustas korjata");
                    korja(laud, arvuti, mängija, pakk);
                    break;

                }
            }
            if (counter == arvuti.kaardidKäes.size()) {
                System.out.println("Arvuti otsustas korjata");
                korja(laud, arvuti, mängija, pakk);
            }
        }
    }
