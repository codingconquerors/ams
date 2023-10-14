package com.akk.growbiz.ams.mapper;

import com.akk.growbiz.ams.entity.AppointmentEntity;
import com.akk.growbiz.ams.model.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AMSMapper {

    AppointmentEntity toEntity(Appointment appointment);

    Appointment toAppointment(AppointmentEntity target);

    List<AppointmentEntity> toEntityList(List<Appointment> appointment);

    List<Appointment> toAppointmentList(List<AppointmentEntity> target);

}
