package com.artem.balan.jprices.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String crop;
    private String price;
    private String source;
    private LocalDate createDate;
}
