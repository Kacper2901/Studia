import java.util.ArrayList;

class Ksiazka{
    ArrayList<Pisarz> autorzy = new ArrayList<>();
    String tytul;
    Pisarz autor;
    Ksiazka(String tytul, Pisarz... autor){
        this.tytul = tytul;
        for(Pisarz p: autor){
            autorzy.add(p);
        }

    }
    @Override
    public String toString(){
        String tekst = " pt. '" + tytul + "' autorstwa ";
        for(Pisarz p: autorzy){
            tekst += p+", ";
        }
        tekst = tekst.substring(0, tekst.length() - 2);

        return tekst;
    }
}

interface Obserwator
{
    void powiadomienie(Ksiazka k);
} 

class Pisarz {
    String pseudonim;
    Obserwator w;
    ArrayList<Obserwator> subskrybenci = new ArrayList<Obserwator>();

    Pisarz (String pseudonim, Obserwator w)
    {
        this.pseudonim = pseudonim;
        subskrybenci.add(w);
    }
    
    ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>();
    void dodaj_sub(Obserwator w){
        subskrybenci.add(w);
    }
    void usun_sub(Obserwator w){
        if(subskrybenci.contains(w)) subskrybenci.remove(w);
    }
    
    void napisz(String tytul){
        Ksiazka ksiazka = new Ksiazka(tytul, this);
        this.ksiazki.add(ksiazka);
        for(Obserwator w: subskrybenci) w.powiadomienie(ksiazka);

    }

    static void napiszWspolnie(String tytul, Pisarz... autorzy) {
        Ksiazka ksiazka = new Ksiazka(tytul, autorzy);
        for (Pisarz p : autorzy) {
            p.ksiazki.add(ksiazka);
            for (Obserwator o : p.subskrybenci) {
                o.powiadomienie(ksiazka);
            }
            System.out.println();

        }
    }

    public String toString(){
        return pseudonim;
    }
}

class Wydawnictwo implements Obserwator {
    char nazwa;
    Wydawnictwo(char nazwa)
    {
        this.nazwa = nazwa;
    }

    void wydajKsiazke(Ksiazka ksiazka) {
        System.out.println("Wydawnictwo "+nazwa+" wydaje ksiazkę" + ksiazka);
    }

    public void powiadomienie(Ksiazka ksiazka)
    {
        if (ksiazka.tytul.charAt(0) == this.nazwa)
            this.wydajKsiazke(ksiazka);
    }

    public String toString(){
        return "Wydawnictwo: '" + nazwa +"'";
    }
}

class Czytelnik implements Obserwator
{
    public void powiadomienie(Ksiazka k){
        System.out.println("Nowa ksiazka " + k);
    }
    @Override
    public String toString() {
        return "Czytelnik anonimowy";
}

}


class Biblioteka implements Obserwator{
    ArrayList<Ksiazka> katalog = new ArrayList<>();
    String nazwa;
    public Biblioteka(String nazwa){
        this.nazwa = nazwa;
    }
    public void powiadomienie(Ksiazka ksiazka){
        katalog.add(ksiazka);
        System.out.println("Dodano "+ksiazka.tytul + " do katalogu bibilioteki "+ nazwa );
    }

    @Override
    public String toString(){
        return "Biblioteka: " + nazwa;
    }
}

class KlubCzytelnika implements Obserwator{
    String nazwa;
    public KlubCzytelnika(String nazwa){
        this.nazwa = nazwa;
    }
    public void powiadomienie(Ksiazka ksiazka){
        System.out.println("Nowa ksiazka"+ksiazka + " będzie omawiana na najbliższym spotkaniu klubu "+ nazwa);
    }
    @Override
    public String toString(){
        return "Klub czytelnika: "+ nazwa;
    }
}

public class Literatura
{

    public static void main(String[] args){
        // Wydawnictwo a = new Wydawnictwo('T');
        // Pisarz pisarz = new Pisarz("Litwos", a);
        // pisarz.napisz("Trylogia");
        Wydawnictwo a = new Wydawnictwo('T');
        Pisarz autor1 = new Pisarz("Adam Mickiewicz", a);
        Biblioteka sloneczna = new Biblioteka("Sloneczna");
        KlubCzytelnika literaki = new KlubCzytelnika("Literaki");
        autor1.dodaj_sub(sloneczna);
        autor1.dodaj_sub(literaki);
        // autor1.napisz("Pan Tadeusz");

    
        Pisarz autor2 = new Pisarz("Juliusz Slowacki", a);
        Pisarz autor3 = new Pisarz("Henryk Sienkiewicz", a);
        Biblioteka morska = new Biblioteka("Morska");
        KlubCzytelnika mol = new KlubCzytelnika("Ksiazkowe mole");
        Biblioteka lesna = new Biblioteka("Lesna");
        KlubCzytelnika kropka = new KlubCzytelnika("Kropka");

        autor2.dodaj_sub(morska);
        autor2.dodaj_sub(mol);
        autor3.dodaj_sub(lesna);
        autor3.dodaj_sub(kropka);

        System.out.println();
        System.out.println("===================");
        System.out.println();
        Pisarz.napiszWspolnie("Tabaluga",autor1,autor2,autor3);
        System.out.println("===================");
    }
}
    
    