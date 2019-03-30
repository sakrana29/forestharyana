package com.haryana.forest.repository;

import com.haryana.forest.domain.StateMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the StateMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StateMasterRepository extends CassandraRepository<StateMaster, UUID> {

}
