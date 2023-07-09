package com.tkm3d1a.cardtesting.cardLeagalities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardLegalitiesRepository extends JpaRepository<CardLegalities, String> {
}
