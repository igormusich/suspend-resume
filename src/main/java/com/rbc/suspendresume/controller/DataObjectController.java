package com.rbc.suspendresume.controller;

import com.rbc.suspendresume.domain.DataObject;
import com.rbc.suspendresume.repository.DataObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/dataObjects")
public class DataObjectController {

    private DataObjectRepository repository;

    @Autowired
    public DataObjectController(DataObjectRepository repository)
    {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<DataObject> dataObjects(@RequestHeader("Application-Code") String appCode)
    {
        return repository.findAll(appCode);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public DataObject add(@RequestHeader("Application-Code") String appCode, @RequestBody @Valid DataObject dataObject) {
        return repository.save(dataObject,appCode);
    }

    @RequestMapping(method = RequestMethod.POST)
    public DataObject update(@RequestHeader("Application-Code") String appCode,@RequestBody @Valid DataObject dataObject) {
        return repository.save(dataObject,appCode);
    }

    @RequestMapping(value = "/{objectKey:.+}", method = RequestMethod.GET)
    public DataObject getById(@RequestHeader("Application-Code") String appCode,@PathVariable String objectKey) {
        return repository.findOne(appCode, objectKey);
    }

    @RequestMapping(value = "/{objectKey:.+}", method = RequestMethod.DELETE)
    public void deleteById(@RequestHeader("Application-Code") String appCode,@PathVariable String objectKey) {
        repository.delete(appCode,objectKey);
    }
}