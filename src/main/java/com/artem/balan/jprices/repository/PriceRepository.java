package com.artem.balan.jprices.repository;

import com.artem.balan.jprices.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Offer, Long> {
}
