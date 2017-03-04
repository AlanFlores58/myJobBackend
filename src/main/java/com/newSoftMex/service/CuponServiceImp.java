package com.newSoftMex.service;

import com.newSoftMex.model.Cupon;
import com.newSoftMex.model.Response;
import com.newSoftMex.repository.CupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Service("cuponService")
public class CuponServiceImp implements CuponService {

    @Autowired
    CupoRepository cupoRepository;

    @Override
    public Response<List<Cupon>> getTicketsByUser(String userName){
        Response<List<Cupon>> response = new Response<>();
        try {
            response.setData(cupoRepository.getTicketsByUser(userName));
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
    public Response<List<Cupon>> getTicketsByUserNotUsed(String userName){
        Response<List<Cupon>> response = new Response<>();
        try {
            response.setData(cupoRepository.getTicketsByUserNotUsed(userName));
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
