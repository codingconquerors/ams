package com.akk.growbiz.ams.repository;

import com.akk.growbiz.ams.entity.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AMSRepo extends MongoRepository<AppointmentEntity, String> {

    @Query("{appointmentCode:'?0'}")
    AppointmentEntity findAppointmentByCode(String appointmentCode);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
    List<AppointmentEntity> findAll(String category);

    long count();
}
