package com.haryana.forest.repository;

import com.haryana.forest.domain.NurseryMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the NurseryMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NurseryMasterRepository extends CassandraRepository<NurseryMaster, UUID> {

}
