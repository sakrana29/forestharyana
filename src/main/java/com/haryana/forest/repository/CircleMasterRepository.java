package com.haryana.forest.repository;

import com.haryana.forest.domain.CircleMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the CircleMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CircleMasterRepository extends CassandraRepository<CircleMaster, UUID> {

}
