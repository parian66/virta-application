package fi.devolon.vitra.web.rest;
import fi.devolon.vitra.domain.Station;
import fi.devolon.vitra.service.StationService;
import fi.devolon.vitra.web.rest.errors.BadRequestAlertException;
import fi.devolon.vitra.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Station.
 */
@RestController
@RequestMapping("/api")
public class StationResource {

    private final Logger log = LoggerFactory.getLogger(StationResource.class);

    private static final String ENTITY_NAME = "station";

    private final StationService stationService;

    public StationResource(StationService stationService) {
        this.stationService = stationService;
    }

    /**
     * POST  /stations : Create a new station.
     *
     * @param station the station to create
     * @return the ResponseEntity with status 201 (Created) and with body the new station, or with status 400 (Bad Request) if the station has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stations")
    public ResponseEntity<Station> createStation(@Valid @RequestBody Station station) throws URISyntaxException {
        log.debug("REST request to save Station : {}", station);
        if (station.getId() != null) {
            throw new BadRequestAlertException("A new station cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Station result = stationService.save(station);
        return ResponseEntity.created(new URI("/api/stations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stations : Updates an existing station.
     *
     * @param station the station to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated station,
     * or with status 400 (Bad Request) if the station is not valid,
     * or with status 500 (Internal Server Error) if the station couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stations")
    public ResponseEntity<Station> updateStation(@Valid @RequestBody Station station) throws URISyntaxException {
        log.debug("REST request to update Station : {}", station);
        if (station.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Station result = stationService.save(station);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, station.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stations : get all the stations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of stations in body
     */
    @GetMapping("/stations")
    public List<Station> getAllStations() {
        log.debug("REST request to get all Stations");
        return stationService.findAll();
    }

    /**
     * GET  /stations/:id : get the "id" station.
     *
     * @param id the id of the station to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the station, or with status 404 (Not Found)
     */
    @GetMapping("/stations/{id}")
    public ResponseEntity<Station> getStation(@PathVariable String id) {
        log.debug("REST request to get Station : {}", id);
        Optional<Station> station = stationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(station);
    }

    /**
     * DELETE  /stations/:id : delete the "id" station.
     *
     * @param id the id of the station to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stations/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable String id) {
        log.debug("REST request to delete Station : {}", id);
        stationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
