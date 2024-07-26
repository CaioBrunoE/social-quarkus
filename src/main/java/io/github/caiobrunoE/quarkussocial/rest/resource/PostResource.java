package io.github.caiobrunoE.quarkussocial.rest.resource;

import io.github.caiobrunoE.quarkussocial.rest.dto.PostDto;
import io.github.caiobrunoE.quarkussocial.rest.service.PostService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    PostService service;

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, PostDto dto){
     return service.createPost(userId, dto);
    }

    @GET
    public Response listPost(@PathParam("userId") Long userId, @HeaderParam("followerId") Long followerId ){
     return service.getAllPosts(userId, followerId);
    }
}
