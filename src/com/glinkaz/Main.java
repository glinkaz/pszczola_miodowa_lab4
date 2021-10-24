package com.glinkaz;

public class Main {

    public static void main(String[] args) {
        Apis ul = new Apis();
        ul.zyciePszczol();
        ul.watkiPszczol();
        ul.wypiszUl();
        ul.dodajPszczole(ul.utworzQueen("Waleria", 7, 0));
        ul.dodajPszczole(new Apis.Truten("Garry", 19, true));
        ul.dodajPszczole(new Apis.Robotnica("Amanda", 8, 22, 48));
        ul.sortujWgSily(ul.getListaPszczol());
        ul.wypiszUl();
        ul.zyciePszczol();
        System.out.println(ul.dodajZolnierza("Helena", 99, 10));

        ul.wypiszUl();
        ul.sortujWgSilyIImienia(ul.getListaPszczol());
        ul.wypiszUl();
    }
}