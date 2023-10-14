package com.akk.growbiz.ams.api;
import com.akk.growbiz.ams.controller.AppointmentApiDelegate;
import com.akk.growbiz.ams.model.Appointment;
import com.akk.growbiz.ams.service.AMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AMSApiDelegateImpl implements AppointmentApiDelegate {

    private AMSService amsService;

    public AMSApiDelegateImpl(AMSService amsService) {
        this.amsService = amsService;
    }

    @Override
    public ResponseEntity<Appointment> getAppointmentByCode(String appointmentCode) {
        log.info("appointmentCode", appointmentCode);
        return ResponseEntity.ok(amsService.getAppointment(appointmentCode));
    }

    @Override
    public ResponseEntity<Appointment> createAppointment(Appointment appointment) {
        return ResponseEntity.ok(amsService.createAppointment(appointment));
    }

    @Override
    public ResponseEntity<List<Appointment>> createAppointmentsWithListInput(List<Appointment> appointments) {
        return ResponseEntity.ok(amsService.createListAppointment(appointments));
    }

    @Override
    public ResponseEntity<Appointment> updateAppointment(String appointmentCode, Appointment user) {
        return ResponseEntity.ok(amsService.updateAppointmentWithCode(appointmentCode, user));
    }

    @Override
    public ResponseEntity<String> deleteAppointment(String appointmentCode) {
        return ResponseEntity.ok(amsService.deleteAppointment(appointmentCode).toString());
    }
}

