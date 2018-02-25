package com.rbc.suspendresume.repository;

import com.rbc.suspendresume.domain.DataObject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataObjectRepositoryImpl implements DataObjectRepository {



    private final HashOperations<String, String, DataObject> hashOps;

    private static String getKey(String appCode){
        return String.format("dataObject:%s", appCode);
    }

    public DataObjectRepositoryImpl(RedisTemplate<String, DataObject> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
    }

    private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T object : iterable) {
            list.add(object);
        }
        return list;
    }

    @Override
    public DataObject save(DataObject dataObject, String appCode) {
        hashOps.put(getKey(appCode), dataObject.getObjectKey(), dataObject);
        return dataObject;
    }

    @Override
    public DataObject findOne(String appCode, String objectCode) {
        return hashOps.get(getKey(appCode), objectCode);
    }

    @Override
    public boolean exists(String appCode, String objectCode) {
        return hashOps.hasKey(getKey(appCode),  objectCode);
    }

    @Override
    public Iterable<DataObject> findAll(String appCode) {
        return hashOps.values(getKey(appCode));
    }

    @Override
    public Iterable<DataObject> findAll(String appCode, Iterable<String> objectCodes) {
        return hashOps.multiGet(getKey(appCode), convertIterableToList(objectCodes));
    }

    @Override
    public long count(String appCode) {
        return hashOps.keys(getKey(appCode)).size();
    }

    @Override
    public void delete(String appCode, String objectCode) {
        hashOps.delete(getKey(appCode), objectCode);
    }
}
