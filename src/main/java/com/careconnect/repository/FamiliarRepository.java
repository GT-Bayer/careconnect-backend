package com.careconnect.repository;

import com.careconnect.model.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepository extends JpaRepository<Familiar, Long> {
}