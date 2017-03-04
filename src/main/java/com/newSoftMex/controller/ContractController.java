package com.newSoftMex.controller;

import com.newSoftMex.model.Contract;
import com.newSoftMex.model.Response;
import com.newSoftMex.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alan.flores on 2/10/17.
 */
@RestController
@RequestMapping("/private/api/v1")
public class ContractController {

    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/saveNewContract/{idService}/{description}/{price}/{username}/{dateStart}/{dateFinish}/{cuponId}", method = RequestMethod.POST)
    public Response<Contract> saveServices(@PathVariable("idService") Long idService,
                                           @PathVariable("description") String description,
                                           @PathVariable("price") float price,
                                           @PathVariable("username") String username,
                                           @PathVariable("dateStart") String dateStart,
                                           @PathVariable("dateFinish") String dateFinish,
                                           @PathVariable("cuponId") Long cuponId) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return contractService.saveNewContract(idService, description, price,username, formatter.parse(dateStart), formatter.parse(dateFinish), cuponId);
    }

    @RequestMapping(value = "/getContractsByUser/{username}", method = RequestMethod.GET)
    public Response<List<Contract>> getTicketsByUserNotUsed(@PathVariable("username") String username){
        return contractService.getContractsByUser(username);
    }

    @RequestMapping(value = "/getAllContracts", method = RequestMethod.GET)
    public Response<List<Contract>> getAllContracts(){
        return contractService.getAllContracts();
    }

    @RequestMapping(value = "/getContractsByServer/{username}", method = RequestMethod.GET)
    public Response<List<Contract>> getContractsByServer(@PathVariable("username") String username){
        return contractService.getContractsByServer(username);
    }

    @RequestMapping(value = "/getContractByUserAndID/{username}/{contactID}", method = RequestMethod.GET)
    public Response<Contract> getContractByUserAndID(@PathVariable("username") String username,@PathVariable("contactID")Long contactID){
        return contractService.getContractByUserAndID(username, contactID);
    }

    @RequestMapping(value = "/getContractByID/{contactID}", method = RequestMethod.GET)
    public Response<Contract> getContractByID(@PathVariable("contactID")Long contactID){
        return contractService.getContractByID(contactID);
    }

    @RequestMapping(value = "/getContractByServerAndID/{username}/{contactID}", method = RequestMethod.GET)
    public Response<Contract> getContractByServerAndID(@PathVariable("username") String username,@PathVariable("contactID")Long contactID){
        return contractService.getContractByServerAndID(username, contactID);
    }

    @RequestMapping(value = "/payContract/{contactID}", method = RequestMethod.POST)
    public Response<Contract> payContract(@PathVariable("contactID") Long contactID){
        return contractService.payContract(contactID);
    }

    @RequestMapping(value = "/confirmContract/{contactID}", method = RequestMethod.POST)
    public Response<Contract> confirmContract(@PathVariable("contactID") Long contactID){
        return contractService.confirmContract(contactID);
    }

    @RequestMapping(value = "/finishContract/{contactID}", method = RequestMethod.POST)
    public Response<Contract> finishContract(@PathVariable("contactID") Long contactID){
        return contractService.finishContract(contactID);
    }


    @RequestMapping(value = "/cancelContract/{contactID}", method = RequestMethod.POST)
    public Response<Contract> cancelContract(@PathVariable("contactID") Long contactID){
        return contractService.cancelContract(contactID);
    }

    @RequestMapping(value = "/finishContractByAdmin/{contactID}", method = RequestMethod.POST)
    public Response<Contract> finishContractByAdmin(@PathVariable("contactID") Long contactID){
        return contractService.finishContractByAdmin(contactID);
    }
}
