package com.haryana.forest.repository;

import com.haryana.forest.domain.BlockMaster;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the BlockMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlockMasterRepository extends CassandraRepository<BlockMaster, UUID> {

}
