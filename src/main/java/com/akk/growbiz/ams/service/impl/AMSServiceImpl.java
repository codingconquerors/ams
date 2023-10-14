package com.akk.growbiz.ams.service.impl;

import com.akk.growbiz.ams.entity.AppointmentEntity;
import com.akk.growbiz.ams.mapper.AMSMapper;
import com.akk.growbiz.ams.model.Appointment;
import com.akk.growbiz.ams.repository.AMSMongoTemplateRepository;
import com.akk.growbiz.ams.repository.AMSRepo;
import com.akk.growbiz.ams.service.AMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AMSServiceImpl implements AMSService {

    private AMSRepo amsRepo;
    private AMSMongoTemplateRepository amsMongoTemplateRepository;

    @Autowired
    AMSMapper amsMapper;

    public AMSServiceImpl(AMSRepo amsRepo, AMSMongoTemplateRepository amsMongoTemplateRepository) {
        this.amsRepo = amsRepo;
        this.amsMongoTemplateRepository = amsMongoTemplateRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {

        AppointmentEntity appointmentEntity = amsMapper.toEntity(appointment);

        return amsMapper.toAppointment(amsRepo.save(appointmentEntity));

    }

    @Override
    public List<Appointment> createListAppointment(List<Appointment> appointment) {
        List<AppointmentEntity> appointmentEntity = amsMapper.toEntityList(appointment);

        return amsMapper.toAppointmentList(amsRepo.saveAll(appointmentEntity));
    }

    @Override
    public Appointment getAppointment(String appointmentCode) {

        return amsMapper.toAppointment(amsRepo.findUserByUserName(appointmentCode));

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
