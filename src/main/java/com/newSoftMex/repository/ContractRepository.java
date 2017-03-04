package com.newSoftMex.repository;

import com.newSoftMex.model.Contract;
import com.newSoftMex.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.flores on 1/6/17.
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract,Long>{

    @Query("select c from Contract c where c.userContract.username=:username")
    public List<Contract> getContractsByUser(@Param("username") String username);

    @Query("select c from Contract c where c.service.userService.username=:username")
    public List<Contract> getContractsByServer(@Param("username") String username);

    @Query("select c from Contract c where c.userContract.username=:username and c.id=:contactID")
    public Contract getContractByUserAndID(@Param("username") String username, @Param("contactID") Long contactID);


    @Query("select c from Contract c where c.service.userService.username=:username and c.id=:contactID")
    public Contract getContractByServerAndID(@Param("username") String username, @Param("contactID") Long contactID);

}
