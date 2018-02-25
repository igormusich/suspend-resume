package com.rbc.suspendresume.repository;

import com.rbc.suspendresume.domain.DataObject;

public interface DataObjectRepository {
    DataObject save(DataObject dataObject, String appCode);

    DataObject findOne(String appCode, String objectCode);

    boolean exists(String appCode, String objectCode);

    Iterable<DataObject> findAll(String appCode);

    Iterable<DataObject> findAll(String appCode,Iterable<String> objectCodes);

    long count(String appCode);

    void delete(String appCode, String objectCode);

}
