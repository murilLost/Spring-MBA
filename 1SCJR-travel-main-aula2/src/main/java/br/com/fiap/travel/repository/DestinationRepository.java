package br.com.fiap.travel.repository;

import br.com.fiap.travel.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {

    List<DestinationEntity> findAllByCountry(String country);

    @Query("Select d from DestinationEntity d where d.country = :country")
    List<DestinationEntity> filtrarPorPais(String country);

}
