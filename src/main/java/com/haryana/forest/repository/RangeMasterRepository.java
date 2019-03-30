package com.haryana.forest.repository;

import com.haryana.forest.domain.RangeMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the RangeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RangeMasterRepository extends CassandraRepository<RangeMaster, UUID> {

}
