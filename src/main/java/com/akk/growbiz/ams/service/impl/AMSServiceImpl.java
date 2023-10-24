package com.akk.growbiz.ams.service.impl;

import com.akk.growbiz.ams.entity.AppointmentEntity;
import com.akk.growbiz.ams.mapper.AMSMapper;
import com.akk.growbiz.ams.model.Appointment;
import com.akk.growbiz.ams.repository.AMSMongoTemplateRepository;
import com.akk.growbiz.ams.repository.AMSRepo;
import com.akk.growbiz.ams.service.AMSService;
import com.akk.growbiz.ams.util.UniqueStrGenerator;
import com.akk.growbiz.ams.exception.AppointmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AMSServiceImpl implements AMSService {

    private final AMSRepo amsRepo;
    private final AMSMongoTemplateRepository amsMongoTemplateRepository;

    private final UniqueStrGenerator uniqueStrGenerator;

    @Autowired
    AMSMapper amsMapper;

    public AMSServiceImpl(AMSRepo amsRepo, AMSMongoTemplateRepository amsMongoTemplateRepository, UniqueStrGenerator uniqueStrGenerator) {
        this.amsRepo = amsRepo;
        this.amsMongoTemplateRepository = amsMongoTemplateRepository;
        this.uniqueStrGenerator = uniqueStrGenerator;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {

        AppointmentEntity appointmentEntity = amsMapper.toEntity(appointment);
        appointmentEntity.setAppointmentCode(uniqueStrGenerator.getAppointmentCode());
        return amsMapper.toAppointment(amsRepo.save(appointmentEntity));

    }

    @Override
    public List<Appointment> createListAppointment(List<Appointment> appointment) {
        List<AppointmentEntity> appointmentEntity = amsMapper.toEntityList(appointment);

        return amsMapper.toAppointmentList(amsRepo.saveAll(appointmentEntity));
    }

    @Override
    public List<Appointment> getScheduledAppointmentsBeforeDate(LocalDateTime localDateTime) {
        return amsMapper.toAppointmentList(amsRepo.findAppointmentsBeforeDate(localDateTime));
    }

    @Override
    public Appointment getAppointment(String appointmentCode) {

        return amsMapper.toAppointment(amsRepo.findAppointmentByCode(appointmentCode).orElseThrow(AppointmentNotFoundException::new));

    }

    @Override
    public Appointment updateAppointmentWithCode(String appointmentCode, Appointment appointment) {
        AppointmentEntity appointmentEntity = amsMapper.toEntity(appointment);
        return amsMongoTemplateRepository.updateUsingFindAndReplace(appointmentCode, appointmentEntity);

    }

    @Override
    public Long deleteAppointment(String appointmentCode) {
        return amsMongoTemplateRepository.deleteAppointmentUsingRemove(appointmentCode);
    }
}
