package entities;

import com.example.S2D5.enums.StatoViaggio;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String destinazione;

    private LocalDate dataViaggio;

    private StatoViaggio statoViaggio;

    @OneToMany(mappedBy = "viaggio")
    private List<GestionePrenotazioni> prenotazioni;


    public Viaggio() {
    }

    public Viaggio(int id, String destinazione, LocalDate dataViaggio, StatoViaggio statoViaggio) {
        this.id = id;
        this.destinazione = destinazione;
        this.dataViaggio = dataViaggio;
        this.statoViaggio = statoViaggio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public LocalDate getDataViaggio() {
        return dataViaggio;
    }

    public void setDataViaggio(LocalDate dataViaggio) {
        this.dataViaggio = dataViaggio;
    }

    public StatoViaggio getStatoViaggio() {
        return statoViaggio;
    }

    public void setStatoViaggio(StatoViaggio statoViaggio) {
        this.statoViaggio = statoViaggio;
    }

    public List<GestionePrenotazioni> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<GestionePrenotazioni> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Viaggio{" +
                "id=" + id +
                ", destinazione='" + destinazione + '\'' +
                ", dataViaggio=" + dataViaggio +
                ", statoViaggio=" + statoViaggio +
                '}';
    }
}
