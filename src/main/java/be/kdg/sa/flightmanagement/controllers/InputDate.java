package be.kdg.sa.flightmanagement.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author LukasVandenBossche on 05/10/2021
 * @project flightmanagement
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InputDate {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}


