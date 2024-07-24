package io.github.caiobrunoE.quarkussocial.rest.resource;

import io.github.caiobrunoE.quarkussocial.rest.dto.UserDto;
import io.github.caiobrunoE.quarkussocial.rest.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;


    @POST
    @Transactional
    public Response createUser( UserDto dto){
       return service.create(dto);
    }
    @GET
    public Response listAllUsers(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id")  Long id){
        return service.getById(id);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUsers( @PathParam("id") Long id, @Valid UserDto dto ){
        return service.update(id, dto);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser( @PathParam("id") Long id){
        return service.delete(id);
    }
}
