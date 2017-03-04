package com.newSoftMex.service;

import com.newSoftMex.model.Response;
import com.newSoftMex.model.Service;

import java.util.List;

/**
 * Created by alan.flores on 2/8/17.
 */
public interface ServiceService {
    Response<Service> saveNewService(String description, String name, float price, long serviceTypeId, String userName);

    Response<List<Service>> getServicesByUser(String userName);

    Response<Service> getServicesByUserAndServiceID(String username, Long serviceID);

    Response<com.newSoftMex.model.Service> updateNewService(Long id,String description, String name, float price, long serviceTypeId, String userName);

    Response<List<com.newSoftMex.model.Service>> getServicesByType(Long type);

    Response<com.newSoftMex.model.Service> getServicesById(Long serviceID);
}
