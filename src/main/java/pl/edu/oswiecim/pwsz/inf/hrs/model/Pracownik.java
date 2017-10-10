package pl.edu.oswiecim.pwsz.inf.hrs.model;

import javax.persistence.*;

@Entity
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String imie;

    @Column
    private String nazwisko;

    @Column
    private Integer wiek;

    @Column
    private Integer pensja;

    @Column
    private String plec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public Integer getPensja() {
        return pensja;
    }

    public void setPensja(Integer pensja) {
        this.pensja = pensja;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }
}
