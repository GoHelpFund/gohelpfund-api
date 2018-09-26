package com.gohelpfund.api.v1.specialroutes.services;


import com.gohelpfund.api.v1.specialroutes.exception.NoRouteFound;
import com.gohelpfund.api.v1.specialroutes.model.AbTestingRoute;
import com.gohelpfund.api.v1.specialroutes.repository.AbTestingRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AbTestingRouteService {
    @Autowired
    private AbTestingRouteRepository abTestingRouteRepository;

    public AbTestingRoute getRoute(String serviceName) {
        AbTestingRoute route = abTestingRouteRepository.findByServiceName(serviceName);

        if (route==null){
            throw new NoRouteFound();
        }

        return route;
    }

    public void saveAbTestingRoute(AbTestingRoute route){

        abTestingRouteRepository.save(route);

    }

    public void updateRouteAbTestingRoute(AbTestingRoute route){
        abTestingRouteRepository.save(route);
    }

    public void deleteRoute(AbTestingRoute route){
        abTestingRouteRepository.delete(route.getServiceName());
    }
}
