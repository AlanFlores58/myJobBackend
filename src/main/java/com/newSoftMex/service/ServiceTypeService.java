package com.newSoftMex.service;

import com.newSoftMex.model.Response;
import com.newSoftMex.model.ServiceType;

import java.util.List;

/**
 * Created by alan.flores on 2/7/17.
 */
public interface ServiceTypeService {
    Response<List<ServiceType>> getAllServiceType();

    Response<ServiceType> saveNewServiceType(String description, String name, String image);

    Response<ServiceType> getServiceTypeByID(Long serviceTypeId);

    Response<ServiceType> saveNewServiceType(Long serviceTypeId, String description, String name, String image);
}
