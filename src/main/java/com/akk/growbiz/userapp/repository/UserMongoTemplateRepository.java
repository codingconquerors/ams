package com.akk.growbiz.userapp.repository;

import com.akk.growbiz.userapp.entity.UserEntity;
import com.akk.growbiz.userapp.model.User;

import java.util.List;

public interface UserMongoTemplateRepository {
    /* Insert operation */
    User insertCity(User city);

    /* Update operations */
    Long updateCityNameUsingUpdateMulti(String oldCityName, String newCityName);

    Long updateCityNameUsingUpdateFirst(String oldCityName, String newCityName);

    User updateCityNameUsingFindAndModify(String oldCityName, String newCityName);

    User updateUsingFindAndReplace(String oldUserName, UserEntity userEntity);

    /* Update if exists, else Create */
    User saveCity(User city);

    String upsertCity(User city);

    /* Get operation */
    List<User> getAllCitiesUsingFindAll();

    /* Delete operations */
    void deleteCityByIdUsingFindAndRemove(String id);

    Long deleteUserUsingRemove(String userName);

    User deleteCityUsingFindAndModify(String id, User city);

    /* Text search operations */
    List<User> getCitiesByTextSearch(String searchText);
}
