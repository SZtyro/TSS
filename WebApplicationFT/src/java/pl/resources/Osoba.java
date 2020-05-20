
package pl.resources;

public class Osoba {

    public int id;
    public String imie;
    public String nazwisko;
    public String opis;

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getOpis() {
        return opis;
    }

    public Osoba(int id, String imie, String nazwisko, String opis) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.opis = opis;
    }

   
}
