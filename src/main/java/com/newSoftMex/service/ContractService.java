package com.newSoftMex.service;

import com.newSoftMex.model.Contract;
import com.newSoftMex.model.Response;

import java.util.Date;
import java.util.List;

/**
 * Created by alan.flores on 2/10/17.
 */
public interface ContractService {
    Response<Contract> saveNewContract(Long idService, String description, float price, String username, Date dateStart, Date dateFinish, Long cuponId);

    Response<List<Contract>> getContractsByUser(String username);

    Response<Contract> getContractByUserAndID(String username,Long contactID);

    Response<Contract> payContract(Long contactID);

    Response<List<Contract>> getContractsByServer(String username);

    Response<Contract> getContractByServerAndID(String username,Long contactID);

    Response<Contract> confirmContract(Long contactID);

    Response<Contract> finishContract(Long contactID);

    Response<List<Contract>> getAllContracts();

    Response<Contract> getContractByID(Long contactID);

    Response<Contract> cancelContract(Long contactID);

    Response<Contract> finishContractByAdmin(Long contactID);
}
