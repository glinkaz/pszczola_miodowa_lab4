package com.glinkaz;

import java.util.*;
import java.util.LinkedList;

public class Apis {
    private List<Pszczola> listaPszczol; //czemu miało byc to final czy to legalne?? przecoez potem dodaję do tego elementy
    private static KrolowaMatka krolowa;

    public Apis() {
        krolowa = new KrolowaMatka("Alicja", 8, 0);
        listaPszczol = new LinkedList<>();
        listaPszczol.add(krolowa);
        listaPszczol.add(new Robotnica("Maja", 15, 10, 102));
        listaPszczol.add(new Robotnica("Basia", 10, 12, 23));
        listaPszczol.add(new Robotnica("Marta", 12, 6, 30));
        listaPszczol.add(new Truten("Gucio", 16, true));
        listaPszczol.add(new Truten("Tadek", 4, false));
        listaPszczol.add(new Truten("Radek", 16, true));
    }


    public List<Pszczola> getListaPszczol() {
        return listaPszczol;
    }

    public void zyciePszczol() {
        int i = 0;
        int j = 0;
        for (Pszczola pszczola : listaPszczol) {

            if (pszczola instanceof Truten && i < 2) {
                i++;
                ((Truten) pszczola).zaplodnienie(krolowa);
            }


            if (pszczola instanceof Robotnica && j < 2) {
                j++;
                Random random = new Random();
                ((Robotnica) pszczola).zbierajNektar(random.nextInt(20));
            }

        }
    }

    //    public void sortuj(){
//        Comparator comparator =  new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                if(o1 instanceof Pszczola && o2 instanceof Pszczola){
//                    if(((Pszczola) o1).silaAtaku == ((Pszczola) o2).silaAtaku){
//                        return ((Pszczola) o1).getImie().compareTo(((Pszczola) o2).getImie());
//                    }else{
//                        return ((Pszczola) o1).getSilaAtaku() - ((Pszczola) o2).getSilaAtaku();
//                    }
//                }else{
//                    return 0;
//                }
//            }
//        };
//        listaPszczol.sort(comparator);
//    }
    // to jest chyba lokalna a nie anonimowa
    // jak zrobić anonimową???
    //dobra chyba teraz już anonimowa jest - hura
    // Czy to ma sens??
    private static class PorownanieSily implements Comparator<Pszczola> {
        @Override
        public int compare(Pszczola o1, Pszczola o2) {
            return o1.silaAtaku - o2.silaAtaku;
        }
    }

    public void sortujWgSily(List<Pszczola> pszczoly) {
        pszczoly.sort(new PorownanieSily());
    }

    public void sortujWgSilyIImienia(List<Pszczola> pszczoly) {

        pszczoly.sort(new Comparator<>() {

            @Override
            public int compare(Pszczola o1, Pszczola o2) {
                if (o1.silaAtaku != o2.silaAtaku) {
                    return o1.silaAtaku - o2.silaAtaku;
                } else {
                    return o1.imie.compareTo(o2.imie);
                }
            }
        });

    }
//                Comparator<Pszczola> sortuj = new Comparator<>(){
//
//                @Override
//                public int compare(Pszczola o1, Pszczola o2) {
//                    if(o1.silaAtaku != o2.silaAtaku){
//                        return o1.silaAtaku - o2.silaAtaku;
//                    }else{
//                        return o1.imie.compareTo(o2.imie);
//                    }
//                }
//            };

    public Pszczola dodajZolnierza(String imie, int silaAtaku, int wiek) {
        System.out.println("Zołnierz: ");
        listaPszczol.add(new Pszczola(imie, silaAtaku, wiek) {
            @Override
            public void run() {
                System.out.println("Walka to moje życie!!!");
            }

            @Override
            public String toString() {
                return "Zołnierz " + imie + " (atak: " + silaAtaku + "), " + " żyję " + wiek + " dni i potrafię użądlić!";
            }
        });
        return listaPszczol.get(listaPszczol.size() - 1);
    }


    public void watkiPszczol() {
        System.out.println("Watki: ");
        for (Pszczola pszczola : listaPszczol) {
            pszczola.run();
        }
    }

    public void dodajPszczole(Pszczola pszczola) {
        listaPszczol.add(pszczola);
    }

    public void wypiszUl() {
        System.out.println("W ulu jest " + listaPszczol.size() + " pszczol");
        for (Pszczola pszczola : listaPszczol) {
            System.out.println(pszczola);
        }
    }

    public abstract static class Pszczola implements Runnable {
        private String imie;
        private int silaAtaku;
        private int wiek;

        public Pszczola() {
            imie = "Pszczola";
            silaAtaku = 0;
            wiek = 0;
        }

        public Pszczola(String imie, int silaAtaku, int wiek) {
            this.imie = imie;
            this.silaAtaku = silaAtaku;
            this.wiek = wiek;
        }

        public Pszczola(String imie, int wiek) {
            this.imie = imie;
            this.silaAtaku = 0;
            this.wiek = wiek;
        }

        public void setSilaAtaku(int silaAtaku) {
            this.silaAtaku = silaAtaku;
        }

        public String getImie() {
            return imie;
        }

        public int getWiek() {
            return wiek;
        }

        public int getSilaAtaku() {
            return silaAtaku;
        }

        public void setImie(String imie) {
            this.imie = imie;
        }

        public void setWiek(int wiek) {
            this.wiek = wiek;
        }

    }

    // czemu Krolowa ma byc statyczna - kiedy klasa ma byc statyczna

    public Pszczola utworzQueen(String name, int age, int eggs){
//        return new KrolowaMatka(name, age, eggs);
        class Queen extends Pszczola {

            @Override
            public void run() {
                System.out.println("Hi from local class");
            }
        }
        return new Queen();
    }

    public Pszczola utworzAnonimQueen(String name, int age, int eggs){
        return new Pszczola() {
            @Override
            public void run() {
                System.out.println("Hi from anonim class");
            }
        };
    }

    public final class KrolowaMatka extends Pszczola {
        private int iloscJaj;

        public KrolowaMatka() {
            setImie("Krolowa");
            setWiek(0);
            setSilaAtaku(100);
            iloscJaj = 0;
        }

        public KrolowaMatka(String imie, int wiek, int iloscJaj) {
            super(imie, wiek);
            this.setSilaAtaku(100);
            this.iloscJaj = iloscJaj;
        }

        private void zaplodnienie() {
            this.iloscJaj += 1000;
        }

        @Override
        public String toString() {
            return "Krolowa " + getImie() + " (atak: " + getSilaAtaku() + "), żyję " + getWiek() + " dni i będę matką dla " +
                    iloscJaj + " młodych pszczółek";
        }

        @Override
        public void run() {
            System.out.println("Lot godowy...");
        }
    }

    public static final class Truten extends Pszczola {
        private boolean przydatny;

        public Truten() {
            setImie("Truten");
            setWiek(0);
            setSilaAtaku(0);
            przydatny = false;
        }

        public Truten(String imie, int wiek, boolean przydatny) {
            super(imie, wiek);
            setSilaAtaku(0);
            this.przydatny = przydatny;
        }

        private void zaplodnienie(KrolowaMatka krolowa) {
            przydatny = false;
            krolowa.zaplodnienie();
            System.out.println(getImie() + " - byłem z Królową!!! Można umierać...");
        }

        @Override
        public String toString() {
            if (przydatny) {
                return "Truten " + getImie() + " (atak: " + getSilaAtaku() + "), spelnilem swoje zadanie :(";
            } else {
                return "Truten " + getImie() + " (atak: " + getSilaAtaku() + "), żyję " + getWiek() + " dni";
            }
        }

        @Override
        public void run() {
            if (Math.random() < 0.5) {
                this.zaplodnienie(Apis.krolowa);
            }
        }
    }

    public static final class Robotnica extends Pszczola {
        private int iloscWyprodukowanegoMiodu;

        public Robotnica() {
            setImie("Robotnica");
            setWiek(0);
            Random random = new Random();
            setSilaAtaku(random.nextInt(21));
            iloscWyprodukowanegoMiodu = 0;
        }

        public Robotnica(String imie, int silaAtaku, int wiek, int iloscWyprodukowanegoMiodu) {
            super(imie, silaAtaku, wiek);
            this.iloscWyprodukowanegoMiodu = iloscWyprodukowanegoMiodu;
        }

        private void zbierajNektar(int nektar) {
            iloscWyprodukowanegoMiodu += nektar;
            System.out.println(getImie() + " - kolejna porcja miodu do kubelka");
        }

        @Override
        public String toString() {
            return "Robotnica " + getImie() + " (atak: " + getSilaAtaku() + "), żyję " + getWiek() + " dni i zrobiłam " +
                    iloscWyprodukowanegoMiodu + " barylek miodu :)";
        }

        @Override
        public void run() {
            Random random = new Random();
            zbierajNektar(random.nextInt(21));
        }
    }
}
