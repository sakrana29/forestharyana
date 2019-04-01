package com.haryana.forest.repository;

import com.haryana.forest.domain.InchargeMasterType;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the InchargeMasterType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InchargeMasterTypeRepository extends CassandraRepository<InchargeMasterType, UUID> {

}
