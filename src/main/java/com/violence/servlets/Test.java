package com.violence.servlets;

import com.violence.repository.UserRepository;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@Path(value = "/myResource")
//@ApplicationPath(value = "/resource")
public class Test {

    @Inject
    UserRepository userRepository;


    @GET
    @Path("/sayHallo")
    public String ping() {
        return userRepository.getUserByLogin("violence").getId().toString();
    }
}
