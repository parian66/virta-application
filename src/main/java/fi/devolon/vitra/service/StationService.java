package fi.devolon.vitra.service;

import fi.devolon.vitra.domain.Station;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Station.
 */
public interface StationService {

    /**
     * Save a station.
     *
     * @param station the entity to save
     * @return the persisted entity
     */
    Station save(Station station);

    /**
     * Get all the stations.
     *
     * @return the list of entities
     */
    List<Station> findAll();


    /**
     * Get the "id" station.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Station> findOne(String id);

    /**
     * Delete the "id" station.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
