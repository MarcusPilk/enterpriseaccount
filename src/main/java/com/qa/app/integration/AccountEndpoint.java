package com.qa.app.integration;

import com.qa.app.service.business.AccountRepository;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/acc")
public class AccountEndpoint {

    @Inject
    private AccountRepository accRepo;

    @Path("/all")
    @GET
    @Produces({"application/json"})
    public String getAllAccounts(){
        return accRepo.getAllAccounts();
    }

    @Path("/new")
    @POST
    @Produces({"application/json"})
    public String addAccount(String account){
        return accRepo.createAccount(account);
    }

    @Path("/edit/{id}")
    @PUT
    @Produces({"application/json"})
    public String updateAccount(@PathParam("id") int id, String account){
        return accRepo.updateAccount(id, account);
    }

    @Path("/del/{id}")
    @DELETE
    @Produces({"application/json"})
    public String deleteAccount(@PathParam("id") int id){
        return accRepo.deleteAccount(id);
    }
}
