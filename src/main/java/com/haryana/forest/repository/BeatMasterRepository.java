package com.haryana.forest.repository;

import com.haryana.forest.domain.BeatMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the BeatMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeatMasterRepository extends CassandraRepository<BeatMaster, UUID> {

}
