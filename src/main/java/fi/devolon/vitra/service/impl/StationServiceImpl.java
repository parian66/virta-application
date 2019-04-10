package fi.devolon.vitra.service.impl;

import fi.devolon.vitra.service.StationService;
import fi.devolon.vitra.domain.Station;
import fi.devolon.vitra.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Station.
 */
@Service
public class StationServiceImpl implements StationService {

    private final Logger log = LoggerFactory.getLogger(StationServiceImpl.class);

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    /**
     * Save a station.
     *
     * @param station the entity to save
     * @return the persisted entity
     */
    @Override
    public Station save(Station station) {
        log.debug("Request to save Station : {}", station);
        return stationRepository.save(station);
    }

    /**
     * Get all the stations.
     *
     * @return the list of entities
     */
    @Override
    public List<Station> findAll() {
        log.debug("Request to get all Stations");
        return stationRepository.findAll();
    }


    /**
     * Get one station by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Station> findOne(String id) {
        log.debug("Request to get Station : {}", id);
        return stationRepository.findById(id);
    }

    /**
     * Delete the station by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Station : {}", id);
        stationRepository.deleteById(id);
    }
}
