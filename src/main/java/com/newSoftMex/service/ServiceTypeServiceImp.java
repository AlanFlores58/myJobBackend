package com.newSoftMex.service;

import com.newSoftMex.model.Response;
import com.newSoftMex.model.ServiceType;
import com.newSoftMex.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alan.flores on 2/7/17.
 */

@Service("serviceTypeService")
public class ServiceTypeServiceImp implements ServiceTypeService {

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Override
    public Response<List<ServiceType>> getAllServiceType(){
        Response<List<ServiceType>> response = new Response<>();

        try {
            response.setStatus("200");
            response.setData(serviceTypeRepository.findAll());
            response.setErrorMessage(null);
            response.setMessage("Successfully.");
            response.setSuccess(true);
        } catch (Exception e){
            e.printStackTrace();
            response.setErrorMessage("Get error: " + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("401");
        }

        return response;
    }

    @Override
    public Response<ServiceType> saveNewServiceType(String description, String name, String image){
        Response<ServiceType> response = new Response<>();

        try {
            ServiceType serviceType = new ServiceType();
            serviceType.setDescription(description);
            serviceType.setName(name);
            serviceType.setImage(image.replace("--","/"));
            response.setStatus("200");
            response.setData(serviceTypeRepository.save(serviceType));
            response.setErrorMessage(null);
            response.setMessage("Successfully.");
            response.setSuccess(true);
        } catch (Exception e){
            e.printStackTrace();
            response.setErrorMessage("Get error: " + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("401");
        }

        return response;
    }

    @Override
    public Response<ServiceType> getServiceTypeByID(Long serviceTypeId){
        Response<ServiceType> response = new Response<>();
        try {
            response.setStatus("200");
            response.setData(serviceTypeRepository.findOne(serviceTypeId));
            response.setErrorMessage(null);
            response.setMessage("Successfully.");
            response.setSuccess(true);
        } catch (Exception e){
            e.printStackTrace();
            response.setErrorMessage("Get error: " + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("401");
        }

        return response;
    }

    @Override
    public Response<ServiceType> saveNewServiceType(Long serviceTypeId, String description, String name, String image){
        Response<ServiceType> response = new Response<>();

        try {
            ServiceType serviceType = serviceTypeRepository.findOne(serviceTypeId);
            serviceType.setDescription(description);
            serviceType.setName(name);
            serviceType.setImage(image.replace("--","/"));
            response.setStatus("200");
            response.setData(serviceTypeRepository.save(serviceType));
            response.setErrorMessage(null);
            response.setMessage("Successfully.");
            response.setSuccess(true);
        } catch (Exception e){
            e.printStackTrace();
            response.setErrorMessage("Get error: " + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("401");
        }

        return response;
    }



}
