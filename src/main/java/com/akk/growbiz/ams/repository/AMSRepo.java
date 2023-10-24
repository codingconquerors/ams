package com.akk.growbiz.ams.repository;

import com.akk.growbiz.ams.entity.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AMSRepo extends MongoRepository<AppointmentEntity, String> {

    @Query("{appointmentCode:'?0'}")
    Optional<AppointmentEntity> findAppointmentByCode(String appointmentCode);

    @Query("{$and :[{status: 'SCHEDULED'},{appointmentDate:{gt:?0}}] }")
    List<AppointmentEntity> findScheduledAppointmentsBeforeDate(@Param("now") LocalDateTime now);

    @Query("{'appointmentDate' : { $gte: ?0 } }")
    List<AppointmentEntity> findAppointmentsBeforeDate(LocalDateTime from);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
    List<AppointmentEntity> findAll(String category);

    long count();
}
