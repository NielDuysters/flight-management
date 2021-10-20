package be.kdg.sa.flightmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Airport {
    @Id
    private String id;
    private String IATA_CODE;
    private String ISO_Country;
    private String name;

    public Airport(String IATA_CODE, String name) {
        this.IATA_CODE = IATA_CODE;
        this.name = name;
    }

    @Override
    public String toString() {
        return IATA_CODE + " " + name;
    }
}
