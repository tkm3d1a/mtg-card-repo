package com.tkm3d1a.cardtesting.defaultCardData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultCardDataRepository extends JpaRepository<DefaultCardData, String> {
}
