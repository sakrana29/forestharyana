package com.haryana.forest.repository;

import com.haryana.forest.domain.InchargeMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the InchargeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InchargeMasterRepository extends CassandraRepository<InchargeMaster, UUID> {

}
