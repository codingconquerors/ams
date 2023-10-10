package com.akk.growbiz.userapp.repository.impl;

import com.akk.growbiz.userapp.entity.UserEntity;
import com.akk.growbiz.userapp.model.User;
import com.akk.growbiz.userapp.repository.UserMongoTemplateRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserMongoTemplateRepositoryImpl implements UserMongoTemplateRepository {

    private final MongoTemplate mongoTemplate;

    public UserMongoTemplateRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User insertCity(User user) {
        return mongoTemplate.insert(user);
    }

    @Override
    public Long updateCityNameUsingUpdateMulti(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);

        UpdateResult updateResult = mongoTemplate.updateMulti(query, updateDefinition, User.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public Long updateCityNameUsingUpdateFirst(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, updateDefinition, User.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public User updateCityNameUsingFindAndModify(String oldCityName, String newCityName) {
        Query query = new Query().addCriteria(Criteria.where("cityName").is(oldCityName));
        Update updateDefinition = new Update().set("cityName", newCityName);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        return mongoTemplate.findAndModify(query, updateDefinition, options, User.class);
    }

    @Override
    public User updateUsingFindAndReplace(String oldUserName, UserEntity userEntity) {
        Query query = new Query().addCriteria(Criteria.where("userName").is(oldUserName));
        FindAndReplaceOptions options = new FindAndReplaceOptions().upsert().returnNew();

        return mongoTemplate.findAndReplace(query, userEntity, options, UserEntity.class, "user", User.class);
    }

    @Override
    public User saveCity(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public String upsertCity(User user) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(user.getId()));
        Update updateDefinition = new Update().set("userName", user.getUserName());

        UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, User.class);
        return updateResult.getUpsertedId().toString();
    }

    @Override
    public List<User> getAllCitiesUsingFindAll() {
        return mongoTemplate.findAll(User.class);
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
        mongoTemplate.findAndRemove(deleteQuery, User.class);
    }

    /**
     * Method to delete documents using 'remove' method.
     * Unlike 'find & remove' method, 'remove' method returns the count of deleted documents.
     *
     * @param userName - user name value to filter the document to be deleted based on 'userName' field
     * @return - count of deleted documents
     */
    @Override
    public Long deleteUserUsingRemove(String userName) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("userName").is(userName));

        DeleteResult deleteResult = mongoTemplate.remove(deleteQuery, UserEntity.class);
        return deleteResult.getDeletedCount();
    }

    /* Need to revisit this method */
    @Override
    public User deleteCityUsingFindAndModify(String id, User user) {
        Query deleteQuery = new Query().addCriteria(Criteria.where("_id").is(id));

        FindAndModifyOptions options = new FindAndModifyOptions().remove(true).returnNew(true);

        Update updateDefinition = new Update();
        updateDefinition.currentDate("12-11-2021");
        //updateDefinition.set("cityName", user.getCityName());
        //updateDefinition.set("pinCode", user.getPinCode());
        return mongoTemplate.findAndModify(deleteQuery, updateDefinition, options, User.class);
    }

    /**
     * This method is used to find all the documents that contain given search text in fields
     * that are indexed with '@TextIndexed' type of index.
     *
     * @param searchText - Text used to search in the TextIndexed fields
     * @return - all documents containing matching search text
     */
    @Override
    public List<User> getCitiesByTextSearch(String searchText) {
        TextCriteria searchCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchText);
        Query textSearchQuery = TextQuery.queryText(searchCriteria);

        return mongoTemplate.find(textSearchQuery, User.class);
    }
}
