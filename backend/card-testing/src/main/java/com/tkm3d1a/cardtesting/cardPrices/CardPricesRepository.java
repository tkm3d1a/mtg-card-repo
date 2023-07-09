package com.tkm3d1a.cardtesting.cardPrices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPricesRepository extends JpaRepository<CardPrices, String> {
}
