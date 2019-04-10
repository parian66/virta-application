package fi.devolon.vitra.repository;

import fi.devolon.vitra.domain.Station;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Station entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StationRepository extends MongoRepository<Station, String> {

}
