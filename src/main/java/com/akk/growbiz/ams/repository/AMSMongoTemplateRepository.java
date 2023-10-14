package com.akk.growbiz.ams.repository;

import com.akk.growbiz.ams.entity.AppointmentEntity;
import com.akk.growbiz.ams.model.Appointment;

import java.util.List;

public interface AMSMongoTemplateRepository {
    /* Insert operation */
    Appointment insertCity(Appointment city);

    /* Update operations */
    Long updateCityNameUsingUpdateMulti(String oldCityName, String newCityName);

    Long updateCityNameUsingUpdateFirst(String oldCityName, String newCityName);

    Appointment updateCityNameUsingFindAndModify(String oldCityName, String newCityName);

    Appointment updateUsingFindAndReplace(String oldUserName, AppointmentEntity userEntity);

    /* Update if exists, else Create */
    Appointment saveCity(Appointment city);

    String upsertCity(Appointment city);

    /* Get operation */
    List<Appointment> getAllCitiesUsingFindAll();

    /* Delete operations */
    void deleteCityByIdUsingFindAndRemove(String id);

    Long deleteAppointmentUsingRemove(String appointmentCode);

    Appointment deleteCityUsingFindAndModify(String id, Appointment city);

    /* Text search operations */
    List<Appointment> getCitiesByTextSearch(String searchText);
}
