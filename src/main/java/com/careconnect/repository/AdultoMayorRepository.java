package com.careconnect.repository;

import com.careconnect.model.AdultoMayor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdultoMayorRepository extends JpaRepository<AdultoMayor, Long>{

}

