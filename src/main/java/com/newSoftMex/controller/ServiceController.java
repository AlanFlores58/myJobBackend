package com.newSoftMex.controller;

import com.newSoftMex.model.Credentials;
import com.newSoftMex.model.Response;
import com.newSoftMex.model.Service;
import com.newSoftMex.model.ServiceType;
import com.newSoftMex.security.JWTAuthenticationToken;
import com.newSoftMex.service.ServiceService;
import com.newSoftMex.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alan.flores on 2/7/17.
 */
@RestController
@RequestMapping("/private/api/v1")
public class ServiceController {

    @Autowired
    ServiceTypeService serviceTypeService;

    @Autowired
    ServiceService serviceService;

    @RequestMapping(value = "/getAllServices", method = RequestMethod.GET)
    public Response<List<ServiceType>> getServicesType(){
        return serviceTypeService.getAllServiceType();
    }


    @RequestMapping(value = "/saveNewService/{description}/{name}/{price}/{serviceTypeId}/{userName}", method = RequestMethod.POST)
    public Response<Service> saveServices(@PathVariable("description") String description,
                                          @PathVariable("name") String name,
                                          @PathVariable("price") float price,
                                          @PathVariable("serviceTypeId") long serviceTypeId,
                                          @PathVariable("userName") String userName){
        return serviceService.saveNewService(description, name, price, serviceTypeId, userName);
    }

    @RequestMapping(value = "/updateService/{id}/{description}/{name}/{price}/{serviceTypeId}/{userName}", method = RequestMethod.POST)
    public Response<Service> saveServices(@PathVariable("id") Long id,
                                          @PathVariable("description") String description,
                                          @PathVariable("name") String name,
                                          @PathVariable("price") float price,
                                          @PathVariable("serviceTypeId") long serviceTypeId,
                                          @PathVariable("userName") String userName){
        return serviceService.updateNewService(id, description, name, price, serviceTypeId, userName);
    }

    @RequestMapping(value = "/getServicesByUser/{username}", method = RequestMethod.GET)
    public Response getServicesByUser(@PathVariable("username") String username) {
        return serviceService.getServicesByUser(username);
    }

    @RequestMapping(value = "/getServicesByUserAndId/{username}/{serviceID}", method = RequestMethod.GET)
    public Response getServicesByUserAndId(@PathVariable("username") String username,@PathVariable("serviceID") Long serviceID) {
        return serviceService.getServicesByUserAndServiceID(username, serviceID);
    }

    @RequestMapping(value = "/getServicesByType/{type}", method = RequestMethod.GET)
    public Response getServicesByType(@PathVariable("type") Long type) {
        return serviceService.getServicesByType(type);
    }

    @RequestMapping(value = "/getServicesById/{serviceId}", method = RequestMethod.GET)
    public Response getServicesById(@PathVariable("serviceId") Long serviceId) {
        return serviceService.getServicesById(serviceId);
    }

    @RequestMapping(value = "/saveNewServiceType/{description}/{name}/{image}", method = RequestMethod.PUT)
    public Response<ServiceType> saveNewServiceType(@PathVariable("description") String description,
                                                  @PathVariable("name") String name,
                                                  @PathVariable("image") String image){
        return serviceTypeService.saveNewServiceType(description, name, image);
    }

    @RequestMapping(value = "/saveNewServiceType/{serviceTypeId}/{description}/{name}/{image}", method = RequestMethod.POST)
    public Response<ServiceType> updateNewServiceType(@PathVariable("serviceTypeId") Long serviceTypeId,
                                                      @PathVariable("description") String description,
                                                      @PathVariable("name") String name,
                                                      @PathVariable("image") String image){
        return serviceTypeService.saveNewServiceType(serviceTypeId, description, name, image);
    }


    @RequestMapping(value = "/getServiceTypeByID/{serviceTypeId}", method = RequestMethod.GET)
    public Response<ServiceType> getServiceTypeByID(@PathVariable("serviceTypeId") Long serviceTypeId){
        return serviceTypeService.getServiceTypeByID(serviceTypeId);
    }
}
