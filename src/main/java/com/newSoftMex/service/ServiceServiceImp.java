package com.newSoftMex.service;

import com.newSoftMex.model.Response;
import com.newSoftMex.model.ServiceType;
import com.newSoftMex.model.User;
import com.newSoftMex.repository.ServiceRepository;
import com.newSoftMex.repository.ServiceTypeRepository;
import com.newSoftMex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Service("serviceService")
public class ServiceServiceImp implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Override
    public Response<com.newSoftMex.model.Service> saveNewService(String description, String name, float price, long serviceTypeId, String userName){
        Response<com.newSoftMex.model.Service> response = new Response<>();
        try {
            User user = userRepository.findByUserName(userName);
            ServiceType serviceType = serviceTypeRepository.findOne(serviceTypeId);
            com.newSoftMex.model.Service service = new com.newSoftMex.model.Service();
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);
            service.setServiceType(serviceType);
            service.setUserService(user);
            response.setData(serviceRepository.save(service));
            response.setStatus("200");
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



    public Response<List<com.newSoftMex.model.Service>> getServicesByUser(String userName){
        Response<List<com.newSoftMex.model.Service>> response = new Response<>();

        try {
            response.setData(serviceRepository.getServicesByUsername(userName));
            response.setErrorMessage(null);
            response.setMessage("They are all the users.");
            response.setSuccess(true);
            response.setStatus("200");
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("403");
            e.printStackTrace();
        }

        return response;
    }

    public Response<com.newSoftMex.model.Service> getServicesByUserAndServiceID(String username, Long serviceID){
        Response<com.newSoftMex.model.Service> response = new Response<>();

        try {
            response.setData(serviceRepository.getServicesByUsernameAndServiceID(username, serviceID));
            response.setErrorMessage(null);
            response.setMessage("They are all the users.");
            response.setSuccess(true);
            response.setStatus("200");
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("403");
            e.printStackTrace();
        }

        return response;
    }

    public Response<List<com.newSoftMex.model.Service>> getServicesByType(Long type){
        Response<List<com.newSoftMex.model.Service>> response = new Response<>();

        try {
            response.setData(serviceRepository.getServicesByType(type));
            response.setErrorMessage(null);
            response.setMessage("They are all the users.");
            response.setSuccess(true);
            response.setStatus("200");
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("403");
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Response<com.newSoftMex.model.Service> updateNewService(Long id,String description, String name, float price, long serviceTypeId, String userName){
        Response<com.newSoftMex.model.Service> response = new Response<>();
        try {
            User user = userRepository.findByUserName(userName);
            ServiceType serviceType = serviceTypeRepository.findOne(serviceTypeId);
            com.newSoftMex.model.Service service = new com.newSoftMex.model.Service();
            service.setId(id);
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);
            service.setServiceType(serviceType);
            service.setUserService(user);
            response.setData(serviceRepository.save(service));
            response.setStatus("200");
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



    public Response<com.newSoftMex.model.Service> getServicesById(Long serviceID){
        Response<com.newSoftMex.model.Service> response = new Response<>();

        try {
            response.setData(serviceRepository.findOne(serviceID));
            response.setErrorMessage(null);
            response.setMessage("They are all the users.");
            response.setSuccess(true);
            response.setStatus("200");
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("403");
            e.printStackTrace();
        }

        return response;
    }
}
