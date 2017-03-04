package com.newSoftMex.service;

import com.newSoftMex.model.Cupon;
import com.newSoftMex.model.Response;

import java.util.List;

/**
 * Created by alan.flores on 2/10/17.
 */
public interface CuponService {
    Response<List<Cupon>> getTicketsByUser(String userName);

    Response<List<Cupon>> getTicketsByUserNotUsed(String userName);
}
