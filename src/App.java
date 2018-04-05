import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App{


    static ObjectOutputStream oos;
    static FileInputStream fi;
    static ObjectInputStream ois;
    static FileOutputStream fs;
    static {
        try {

            fs = new FileOutputStream("zapis.bin",true);
            fi = new FileInputStream("zapis.bin");
            oos = new ObjectOutputStream(fs);
            ois = new ObjectInputStream(fi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Player gracz1 = null;
        char znak;
        String nazwa;
        wypisz_menu();
        znak = (char)System.in.read();



        switch (znak){
            case '1':
                System.out.println("Podaj nazwe gracza");
                nazwa = new Scanner(System.in).next();
                gracz1 = new Player(nazwa);
                gra(gracz1);
                break;
            case '2':

                ArrayList<Player> gracze = (ArrayList<Player>)ois.readObject();
                System.out.println("Podaj nazwe gracza");
                nazwa = new Scanner(System.in).toString();
                for(Player gracz: gracze){
                    if(ois.readObject().toString().contains(nazwa))
                        gracz = (Player) ois.readObject();
                    gracz1 = new Player(gracz);
                    break;
                }
                System.out.println("Wczytano gre gracza "+gracz1.getName());
                gra(gracz1);
                break;
            case '3':
                System.out.println("Wychodzenie");
                break;
            default:
                break;

        }








    }

    private static void gra(Player gracz1) throws FileNotFoundException {
        while (gracz1.getSzanse()>0){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            char znak;
            Word slowo = new Word(wczytaj_slowo());
            System.out.println(slowo.getWczytane_slowo());
            System.out.println(slowo.getUkryte_slowo());
            while (!slowo.czyWin){


                try {

                    znak = (char)br.read();;
                    if(znak == '1'){
                        oos.writeObject(gracz1);
                    }
                    else{

                        znak = Character.toUpperCase(znak);


                        slowo.sprawdz_znaki(znak);
                        if(!slowo.czy_byla)
                            gracz1.ujemne_szanse(slowo);
                        else
                            System.out.println(slowo.getUkryte_slowo());
                    }


    

                   // System.in.skip(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(slowo.czyWin){
                gracz1.wygrana(slowo.getUkryte_slowo());
            }
        }
    }

    private static void wypisz_menu() {
        System.out.println("WISIELEC - GRA DLA GUPKUF");
        System.out.println("1. Nowa gra");
        System.out.println("2. Wczytaj gre");
        System.out.println("3. Wyjscie");
    }

    private static String wczytaj_slowo() throws FileNotFoundException {
        File file = new File("slowa.txt");
        try (Scanner in = new Scanner(file)) {
            Random generator = new Random();
            String line32 = Files.readAllLines(Paths.get("slowa.txt")).get(generator.nextInt(58000)+1);
            return line32;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Blabla";
    }

}
