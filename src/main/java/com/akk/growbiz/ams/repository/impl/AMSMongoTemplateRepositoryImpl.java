package com.akk.growbiz.ams.repository.impl;

import com.akk.growbiz.ams.entity.AppointmentEntity;
import com.akk.growbiz.ams.model.Appointment;
import com.akk.growbiz.ams.repository.AMSMongoTemplateRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AMSMongoTemplateRepositoryImpl implements AMSMongoTemplateRepository {

    private final MongoTemplate mongoTemplate;

    public AMSMongoTemplateRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Appointment insertCity(Appointment user) {
        return mongoTemplate.insert(user);
    }

    @Override
    public Long updateCityNameUsingUpdateMulti(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);

        UpdateResult updateResult = mongoTemplate.updateMulti(query, updateDefinition, Appointment.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public Long updateCityNameUsingUpdateFirst(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, updateDefinition, Appointment.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public Appointment updateCityNameUsingFindAndModify(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        return mongoTemplate.findAndModify(query, updateDefinition, options, Appointment.class);
    }

    @Override
    public Appointment updateUsingFindAndReplace(String oldUserName, AppointmentEntity userEntity) {
        Query query = new Query().addCriteria(Criteria.where("userName").is(oldUserName));
        FindAndReplaceOptions options = new FindAndReplaceOptions().upsert().returnNew();

        return mongoTemplate.findAndReplace(query, userEntity, options, AppointmentEntity.class, "user", Appointment.class);
    }

    @Override
    public Appointment saveCity(Appointment user) {
        return mongoTemplate.save(user);
    }

    @Override
    public String upsertCity(Appointment user) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(user.getId()));
        Update updateDefinition = new Update().set("userName", user.getAppointmentCode());

        UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, Appointment.class);
        return updateResult.getUpsertedId().toString();
    }

    @Override
    public List<Appointment> getAllCitiesUsingFindAll() {
        return mongoTemplate.findAll(Appointment.class);
    }

    /**
     * Method to delete documents using 'find & remove' method.
     * This method does not return any value
     *
     * @param id - id value to filter the document to be deleted based on 'id' field
     */
    @Override
    public void deleteCityByIdUsingFindAndRemove(String id) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.findAndRemove(deleteQuery, Appointment.class);
    }

    /**
     * Method to delete documents using 'remove' method.
     * Unlike 'find & remove' method, 'remove' method returns the count of deleted documents.
     *
     * @param appointmentCode - Appointment Code value to filter the document to be deleted based on 'appointmentCode' field
     * @return - count of deleted documents
     */
    @Override
    public Long deleteAppointmentUsingRemove(String appointmentCode) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("appointmentCode").is(appointmentCode));

        DeleteResult deleteResult = mongoTemplate.remove(deleteQuery, AppointmentEntity.class);
        return deleteResult.getDeletedCount();
    }

    /* Need to revisit this method */
    @Override
    public Appointment deleteCityUsingFindAndModify(String id, Appointment user) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("_id").is(id));

        FindAndModifyOptions options = new FindAndModifyOptions().remove(true).returnNew(true);

        Update updateDefinition = new Update();
        updateDefinition.currentDate("12-11-2021");
        //updateDefinition.set("cityName", user.getCityName());
        //updateDefinition.set("pinCode", user.getPinCode());
        return mongoTemplate.findAndModify(deleteQuery, updateDefinition, options, Appointment.class);
    }

    /**
     * This method is used to find all the documents that contain given search text in fields
     * that are indexed with '@TextIndexed' type of index.
     *
     * @param searchText - Text used to search in the TextIndexed fields
     * @return - all documents containing matching search text
     */
    @Override
    public List<Appointment> getCitiesByTextSearch(String searchText) {
        TextCriteria searchCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchText);
        Query textSearchQuery = TextQuery.queryText(searchCriteria);

        return mongoTemplate.find(textSearchQuery, Appointment.class);
    }
}
