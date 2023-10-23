package com.akk.growbiz.ams.service;

import com.akk.growbiz.ams.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AMSService {
    Appointment createAppointment(Appointment appointment);

    List<Appointment> createListAppointment(List<Appointment> appointment);
    List<Appointment> getScheduledAppointmentsBeforeDate(LocalDateTime localDateTime);

    Appointment getAppointment(String appointmentCode);
    Long deleteAppointment(String appointmentCode);

    Appointment updateAppointmentWithCode(String appointmentCode, Appointment appointment);
}
