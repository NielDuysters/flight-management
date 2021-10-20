package be.kdg.sa.flightmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airlines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Airline {
    @Id
    private String id;
    private String country;
    private String name;
}