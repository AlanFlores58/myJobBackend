package com.newSoftMex.repository;

import com.newSoftMex.model.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alan.flores on 1/6/17.
 */
@Repository
public interface CupoRepository extends JpaRepository<Cupon,Long>{
}
