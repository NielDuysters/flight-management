package be.kdg.sa.flightmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "delays")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Delay {
    @Id
    @Column(name = "id")
    private UUID UUID;
    private String delayDescription;
    private boolean departureDelay;
    private int minutes;

    @ManyToOne
    @JoinColumn(name = "delay_type_id")
    private DelayType delayType;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;

    public Delay(java.util.UUID UUID, String delayDescription, boolean departureDelay, int minutes, DelayType delayType) {
        this.UUID = UUID;
        this.delayDescription = delayDescription;
        this.departureDelay = departureDelay;
        this.minutes = minutes;
        this.delayType = delayType;
    }

    @Override
    public String toString() {
        return "Delay{" +
                "UUID=" + UUID +
                ", delayDescription='" + delayDescription + '\'' +
                ", departureDelay=" + departureDelay +
                ", minutes=" + minutes +
                ", delayType=" + delayType +
                ", flight=" + flight +
                '}';
    }
}
