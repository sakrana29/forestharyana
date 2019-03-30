package com.haryana.forest.repository;

import com.haryana.forest.domain.DivisionMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the DivisionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DivisionMasterRepository extends CassandraRepository<DivisionMaster, UUID> {

}
