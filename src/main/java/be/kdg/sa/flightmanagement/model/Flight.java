package be.kdg.sa.flightmanagement.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author LukasVandenBossche on 28/09/2021
 * @project flightmanagement
 */
@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flight {
    @Id
    @Column(name = "id")
    private UUID UUID;
    private String airCraftType;
    /**
     * actual time of arrival / departure
     */
    private Date ATA;
    private Date ATD;
    /**
     * date
     */
    private Date date;
    /**
     * estimated time of arrival / departure
     */
    private Date ETA;
    private Date ETD;
    private String name;
    /**
     * scheduled time of arrival / departure
     */
    private Date STA;
    private Date STD;

    @ManyToOne
    @JoinColumn(name = "arrival_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Airport departureAirport;

    @OneToMany(mappedBy = "flight")
    @JsonManagedReference
    private List<Delay> delays;

    public Flight(UUID UUID, String name, Date date, Airport departureAirport) {
        this.UUID = UUID;
        this.name = name;
        this.date = date;
        this.departureAirport = departureAirport;
    }

    public Flight(UUID UUID, String name, Date ATA, Date ATD, Date date, Date ETA, Date ETD, Date STA, Date STD, Airport arrivalAirport, Airport departureAirport, List<Delay> delays) {
        this.UUID = UUID;
        this.name = name;
        this.ATA = ATA;
        this.ATD = ATD;
        this.date = date;
        this.ETA = ETA;
        this.ETD = ETD;
        this.STA = STA;
        this.STD = STD;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.delays = delays;
    }
}


