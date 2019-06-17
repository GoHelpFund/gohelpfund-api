package com.gohelpfund.api.v1.specialroutes.repository;

import com.gohelpfund.api.v1.specialroutes.model.AbTestingRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbTestingRouteRepository extends JpaRepository<AbTestingRoute,String>  {
    public AbTestingRoute findByServiceName(String serviceName);
}
