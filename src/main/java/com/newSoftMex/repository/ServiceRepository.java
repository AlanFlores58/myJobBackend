package com.newSoftMex.repository;

import com.newSoftMex.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.flores on 1/6/17.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service,Long>{

    @Query("Select s from Service s where s.userService.username=:username")
    List<Service> getServicesByUsername(@Param("username") String username);

    @Query("Select s from Service s where s.userService.username=:username and s.id=:serviceID")
    Service getServicesByUsernameAndServiceID(@Param("username") String username, @Param("serviceID") Long serviceID);

    @Query("Select s from Service s where s.serviceType.id=:type")
    List<Service> getServicesByType(@Param("type") Long type);
}
