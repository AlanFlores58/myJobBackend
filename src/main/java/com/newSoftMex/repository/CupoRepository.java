package com.newSoftMex.repository;

import com.newSoftMex.model.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.flores on 1/6/17.
 */
@Repository
public interface CupoRepository extends JpaRepository<Cupon,Long>{

    @Query("select c from Cupon c where c.userCupon.username=:username")
    List<Cupon> getTicketsByUser(@Param("username") String username);

    @Query("select c from Cupon c where c.userCupon.username=:username and c.state=true")
    List<Cupon> getTicketsByUserNotUsed(@Param("username") String username);
}
