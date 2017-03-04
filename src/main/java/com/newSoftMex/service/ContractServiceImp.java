package com.newSoftMex.service;

import com.newSoftMex.model.Contract;
import com.newSoftMex.model.Cupon;
import com.newSoftMex.model.Response;
import com.newSoftMex.repository.ContractRepository;
import com.newSoftMex.repository.CupoRepository;
import com.newSoftMex.repository.ServiceRepository;
import com.newSoftMex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Service("contractService")
public class ContractServiceImp implements ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    CupoRepository cupoRepository;

    @Override
    public Response<Contract> saveNewContract(Long idService, String description, float price, String username, Date dateStart, Date dateFinish, Long cuponId){
        Response<Contract> response = new Response<>();
        Contract contract = new Contract();
        try {
            Cupon cupon = cupoRepository.findOne(cuponId);
            if(cupon != null){
                cupon.setDate(new Date());
                cupon.setState(false);
                cupon = cupoRepository.save(cupon);
            }
            contract.setService(serviceRepository.findOne(idService));
            contract.setDescription(description);
            contract.setPrice(price);
            contract.setUserContract(userRepository.findByUserName(username));
            contract.setDateStart(dateStart);
            contract.setDateFinish(dateFinish);
            contract.setCupon(cupon);
            response.setData(contractRepository.save(contract));
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
    public Response<List<Contract>> getContractsByUser(String username){
        Response<List<Contract>> response = new Response<>();
        try {
            response.setData(contractRepository.getContractsByUser(username));
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
    public Response<Contract> getContractByUserAndID(String username,Long contactID){
        Response<Contract> response = new Response<>();
        try {
            response.setData(contractRepository.getContractByUserAndID(username,contactID));
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
    public Response<Contract> payContract(Long contactID){
        Response<Contract> response = new Response<>();
        try {
            Contract contract = contractRepository.findOne(contactID);
            contract.setPayed(true);
            response.setData(contractRepository.save(contract));
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
    public Response<List<Contract>> getContractsByServer(String username){
        Response<List<Contract>> response = new Response<>();
        try {
            response.setData(contractRepository.getContractsByServer(username));
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
    public Response<Contract> getContractByServerAndID(String username,Long contactID){
        Response<Contract> response = new Response<>();
        try {
            response.setData(contractRepository.getContractByServerAndID(username,contactID));
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
    public Response<Contract> confirmContract(Long contactID){
        Response<Contract> response = new Response<>();
        try {
            Contract contract = contractRepository.findOne(contactID);
            contract.setConfirmed(true);
            response.setData(contractRepository.save(contract));
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
    public Response<Contract> finishContract(Long contactID){
        Response<Contract> response = new Response<>();
        try {
            Contract contract = contractRepository.findOne(contactID);
            contract.setFinish(true);
            response.setData(contractRepository.save(contract));
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
    public Response<List<Contract>> getAllContracts(){
        Response<List<Contract>> response = new Response<>();
        try {
            response.setData(contractRepository.findAll());
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
    public Response<Contract> getContractByID(Long contactID){
        Response<Contract> response = new Response<>();
        try {
            response.setData(contractRepository.findOne(contactID));
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
    public Response<Contract> cancelContract(Long contactID){
        Response<Contract> response = new Response<>();
        try {
            Contract contract = contractRepository.findOne(contactID);
            contract.setCancelled(true);
            if(contract.getCupon() != null){
                Cupon cupon = contract.getCupon();
                cupon.setState(true);
                cupon.setDate(null);
                cupoRepository.save(cupon);
            }
            response.setData(contractRepository.save(contract));
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
    public Response<Contract> finishContractByAdmin(Long contactID){
        Response<Contract> response = new Response<>();
        try {
            Contract contract = contractRepository.findOne(contactID);
            contract.setConfirmAdmin(true);
            response.setData(contractRepository.save(contract));
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
