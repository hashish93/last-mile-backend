package com.appzone.lastmile.repository;

import com.appzone.lastmile.model.Audit;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Appzone on 16-Feb-17.
 */
@Repository
public interface AuditRepository extends CassandraRepository<Audit> {


}
