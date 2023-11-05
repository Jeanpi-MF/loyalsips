package com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarRepository extends JpaRepository<Bar,Long> {
    Optional<Bar> findByName(String name);

    boolean existByName(String name);

    boolean existsAllByNameAndIdIsNot(String name, Long id );

}
