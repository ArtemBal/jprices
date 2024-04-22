package com.artem.balan.jprices.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Offer {
    private String crop;
    private String price;
    private String source;
}
