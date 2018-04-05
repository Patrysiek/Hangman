public class Word {


    private String wczytane_slowo;
    private String ukryte_slowo;
    boolean czyWin = false;
    boolean czy_byla = false;
    private StringBuilder sb = new StringBuilder("");
    private StringBuilder literkiKtoreByly = new StringBuilder("");

    public Word(String slowo){
        wczytane_slowo = slowo.toUpperCase();
        ukryj_slowo();

    }

    public void ukryj_slowo(){

        for(int i=0; i<wczytane_slowo.length(); i++){
            sb.insert(i,'-');
        }
        ukryte_slowo = sb.toString();
    }



    public void sprawdz_znaki( char x){

        czy_byla = false;

        if(literkiKtoreByly.toString().contains(String.valueOf(x))){
            System.out.println("Podawales juz ta literke !");
            czy_byla = true;
        }
        else{
            literkiKtoreByly.append(x);
            for(int i=0; i<wczytane_slowo.length(); i++) {
                if (wczytane_slowo.charAt(i) == x){
                    sb.deleteCharAt(i);
                    sb.insert(i,x);
                    czy_byla = true;
                }
            }
            ukryte_slowo = sb.toString();

            if(wczytane_slowo.equals(ukryte_slowo))
                czyWin = true;
        }





    }


    public String getWczytane_slowo() {
        return wczytane_slowo;
    }

    public String getUkryte_slowo() {
        return ukryte_slowo;
    }
}
