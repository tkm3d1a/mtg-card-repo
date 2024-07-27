package com.tkm3d1a.cardtesting.repository;

import com.tkm3d1a.cardtesting.entity.DefaultCardData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultCardDataRepository extends JpaRepository<DefaultCardData, String> {
}
