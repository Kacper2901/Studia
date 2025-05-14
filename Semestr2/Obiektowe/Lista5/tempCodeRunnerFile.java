import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Ksiazka implements Serializable {

    ArrayList<Pisarz> autorzy = new ArrayList<>();
    String tytul;
    Pisarz autor;
    int liczba_rozdzialow;

    Ksiazka(String tytul, Pisarz... autor) {
        this.tytul = tytul;

        for (Pisarz p : autor) {
            autorzy.add(p);
        }
    }

    @Override
    public String toString() {
        String tekst = " pt. '" + tytul + "' autorstwa ";

        for (Pisarz p : autorzy) {
            tekst += p + ", ";
        }
        tekst = tekst.substring(0, tekst.length() - 2);
        return tekst;
    }
}

interface Obserwator {
    void powiadomienie(Ksiazka k);

    default int getPriorytet() {
        return 0;
    }
}

class Pisarz implements Serializable {

    String pseudonim;
    int wiek;
    Obserwator w;
    ArrayList<Obserwator> subskrybenci = new ArrayList<Obserwator>();

    Pisarz(String pseudonim, Obserwator w) {
        this.pseudonim = pseudonim;
        subskrybenci.add(w);
    }

    ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>();

    void dodaj_sub(Obserwator w) {
        subskrybenci.add(w);
    }

    void usun_sub(Obserwator w) {
        if (subskrybenci.contains(w))
            subskrybenci.remove(w);
    }

    void napisz(String tytul) {
        Ksiazka ksiazka = new Ksiazka(tytul, this);
        this.ksiazki.add(ksiazka);
        subskrybenci.sort((a, b) -> Integer.compare(a.getPriorytet(), b.getPriorytet()));
        for (Obserwator w : subskrybenci)
            w.powiadomienie(ksiazka);

    }

    static void napiszWspolnie(String tytul, Pisarz... autorzy) {

        Ksiazka ksiazka = new Ksiazka(tytul, autorzy);

        for (Pisarz p : autorzy) {
            p.ksiazki.add(ksiazka);
            p.subskrybenci.sort((a, b) -> Integer.compare(a.getPriorytet(), b.getPriorytet()));

            for (Obserwator o : p.subskrybenci) {
                o.powiadomienie(ksiazka);
            }
            System.out.println();

        }
    }

    public String toString() {
        return pseudonim;
    }
}

class Wydawnictwo implements Obserwator, Serializable {

    char nazwa;
    String adres;

    Wydawnictwo(char nazwa) {
        this.nazwa = nazwa;
    }

    public int getPriorytet() {
        return 1;
    }

    void wydajKsiazke(Ksiazka ksiazka) {
        System.out.println("Wydawnictwo " + nazwa + " wydaje ksiazkę" + ksiazka);
    }

    public void powiadomienie(Ksiazka ksiazka) {
        if (ksiazka.tytul.charAt(0) == this.nazwa)
            this.wydajKsiazke(ksiazka);
    }

    public String toString() {
        return "Wydawnictwo: '" + nazwa + "'";
    }
}

class Czytelnik implements Obserwator, Serializable {

    int wiek;
    String imie;

    public void powiadomienie(Ksiazka k) {
        System.out.println("Nowa ksiazka " + k);
    }

    public int getPriorytet() {
        return 3;
    }

    @Override
    public String toString() {
        return "Czytelnik anonimowy";
    }

}

class Biblioteka implements Obserwator, Serializable {

    ArrayList<Ksiazka> katalog = new ArrayList<>();
    String nazwa;
    int liczba_pieter;

    public Biblioteka(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPriorytet() {
        return 2;
    }

    public void powiadomienie(Ksiazka ksiazka) {
        katalog.add(ksiazka);
        System.out.println("Dodano " + ksiazka.tytul + " do katalogu bibilioteki " + nazwa);
    }

    @Override
    public String toString() {
        return "Biblioteka: " + nazwa;
    }
}

class KlubCzytelnika implements Obserwator {

    String nazwa;
    int liczba_czlonkow;

    public KlubCzytelnika(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPriorytet() {
        return 4;
    }

    public void powiadomienie(Ksiazka ksiazka) {
        System.out.println("Nowa ksiazka" + ksiazka + " będzie omawiana na najbliższym spotkaniu klubu " + nazwa);
    }

    @Override
    public String toString() {
        return "Klub czytelnika: " + nazwa;
    }
}

class DaneLiterackie implements Serializable {
    ArrayList<Pisarz> pisarze;
    ArrayList<Ksiazka> ksiazki;
    ArrayList<Obserwator> obserwatorzy;

    DaneLiterackie(ArrayList<Pisarz> pisarze, ArrayList<Ksiazka> ksiazki, ArrayList<Czytelnik> czytelnik) {
        this.pisarze = pisarze;
        this.ksiazki = ksiazki;
        this.obserwatorzy = obserwatorzy;
    }
}

public class Literatura {

    public static void main(String[] args) {
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
        autor3.dodaj_sub(new Czytelnik());

        System.out.println();
        System.out.println("===================");
        System.out.println();
        Pisarz.napiszWspolnie("Tabaluga", autor1, autor2, autor3);
        System.out.println("===================");
        System.out.println();

        final String PLIK = "dane.ser";
        DaneLiterackie dane;

        File plik = new File(PLIK);
        if (plik.exists()) {
            System.out.println("WYSWIETLANIE");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(plik))) {
                dane = (DaneLiterackie) in.readObject();
                System.out.println("Dane wczytane z pliku:");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

        else {
            System.out.println("ZAPIS");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            Pisarz jkRowling = new Pisarz("J. K. Rowling", lesna);
            Pisarz tolkien = new Pisarz("Tolkien", sloneczna);
            Ksiazka wladcaPierscieni = new Ksiazka("Wladca Pierscieni", jkRowling, tolkien);
            jkRowling.ksiazki.add(wladcaPierscieni);
            tolkien.ksiazki.add(wladcaPierscieni);

            Czytelnik messi = new Czytelnik();
            messi.imie = "messi";
            tolkien.dodaj_sub(messi);
            jkRowling.dodaj_sub(messi);
            ArrayList<Ksiazka> wszystkieKsiazki = new ArrayList<>(tolkien.ksiazki);
            wszystkieKsiazki.addAll(jkRowling.ksiazki);
            ArrayList<Pisarz> wszyscyPisarze = new ArrayList<>(List.of(tolkien, jkRowling));
            ArrayList<Czytelnik> wszyscyCzytelnicy = new ArrayList<>(List.of(messi));
            dane = new DaneLiterackie(wszyscyPisarze, wszystkieKsiazki, wszyscyCzytelnicy);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(plik))) {
                out.writeObject(dane);
                System.out.println("Dane zapisane do pliku.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
