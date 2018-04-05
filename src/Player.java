import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable{

    private String name;
    private int poczatek = 0;
    private int koniec = 7;
    private int odgadniete_slowa = 0;

    private int szanse = 1;

    public Player(String name){
        this.name = name;
    }

    public Player(Player gracz) {
        this.name = gracz.name;
        this.poczatek = gracz.poczatek;
        this.koniec = gracz.koniec;
        this.odgadniete_slowa = gracz.odgadniete_slowa;
        this.szanse = gracz.szanse;
    }

    public void ujemne_szanse(Word slowo) throws FileNotFoundException {
        szanse--;
        wczytaj_wisielca();
        if(koniec >= 50){
            System.out.print("Przegrales bejbe !\n Ukryte slowo to: "+slowo.getWczytane_slowo());
        }
    }

    private void wczytaj_wisielca() throws FileNotFoundException {
        File file = new File("zycia.txt");
        Scanner in = new Scanner(file);
        String linia;
        for(int i = 0;i<koniec; i++){
            linia = in.nextLine();
            if(i>=poczatek&&i<=koniec){

                System.out.println(linia);
            }
        }
        poczatek = koniec;
        koniec +=7;
    }

    public int getSzanse() {
        return szanse;
    }


public void wygrana(String slowo){
        odgadniete_slowa++;
    System.out.println("Brawo odgadles juz "+odgadniete_slowa+" slow !\n Ukryte slowo to: "+slowo);
    poczatek = 0;
    koniec = 7;
}

    public String getName() {
        return name;
    }
}
