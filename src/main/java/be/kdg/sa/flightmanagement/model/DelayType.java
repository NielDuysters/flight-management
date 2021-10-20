package be.kdg.sa.flightmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LukasVandenBossche on 28/09/2021
 * @project flightmanagement
 */
@Entity
@Table(name = "delaytypes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DelayType {
    @Id
    private int id;
    @Column(name = "code")
    private String IATA_code;
    private String description;

    public DelayType(String IATA_code) {
        this.IATA_code = IATA_code;
    }
}


