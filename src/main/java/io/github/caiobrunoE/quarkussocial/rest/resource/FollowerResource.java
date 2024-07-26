package io.github.caiobrunoE.quarkussocial.rest.resource;

import io.github.caiobrunoE.quarkussocial.rest.dto.FollowerDto;
import io.github.caiobrunoE.quarkussocial.rest.service.FollowerService;
import io.github.caiobrunoE.quarkussocial.rest.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

    @Inject
    FollowerService followerService;

    @Inject
    UserService userService;

    @PUT
    public Response followUser(@PathParam("userId") Long userId , FollowerDto dto){
      return followerService.uodateFollower(userId , dto);
    }

    @GET
    public Response listFollowers(@PathParam("userId") Long userId ){

        return followerService.findAll(userId);
    }

    @DELETE
    public Response unfollowUser(
            @PathParam("userId") Long userId,
            @QueryParam("followerId")  Long followerId ){
     return followerService.unfollow(userId, followerId);
    }
}
