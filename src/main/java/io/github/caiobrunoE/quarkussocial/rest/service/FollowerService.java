package io.github.caiobrunoE.quarkussocial.rest.service;

import io.github.caiobrunoE.quarkussocial.rest.Entity.Follower;
import io.github.caiobrunoE.quarkussocial.rest.Entity.User;
import io.github.caiobrunoE.quarkussocial.rest.dto.FollowerDto;
import io.github.caiobrunoE.quarkussocial.rest.dto.FollowerResponse;
import io.github.caiobrunoE.quarkussocial.rest.dto.FollowerUserResponse;
import io.github.caiobrunoE.quarkussocial.rest.repository.FollowerRepository;
import io.github.caiobrunoE.quarkussocial.rest.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class FollowerService {

    @Inject
    FollowerRepository repository;
    @Inject
    UserRepository userRepository;

    public Response uodateFollower(Long userId, FollowerDto dto) {
     if (userId.equals(dto.getFollowerId())){
         return Response.status(Response.Status.CONFLICT).entity("You can't follow yourself!").build();
     }

     User user =  userRepository.findById(userId);
     if (user == null ){
         return Response.status(Response.Status.NOT_FOUND).build();

     }

     User follower =  userRepository.findById(dto.getFollowerId());

    boolean follows = repository.follows(follower, user);

    if (!follows){
        Follower entity = new Follower();
        entity.setUser(user);
        entity.setFollower(follower);
        repository.persist(entity);
    }

     return Response.status(Response.Status.NO_CONTENT).build();
    }

    public Response findAll(Long userId) {
        User user =  userRepository.findById(userId);
        if (user == null ){
            return Response.status(Response.Status.NOT_FOUND).build();

        }
        var list = repository.findByUser(userId);
        FollowerUserResponse responseObject = new FollowerUserResponse();
        responseObject.setFollowersConunt(list.size());

        var followerList = list.stream().map(FollowerResponse::new).collect(Collectors.toList());

         responseObject.setContent(followerList);

         return Response.ok(responseObject).build();
    }

    public Response unfollow(Long userId, Long followerId) {
        var user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        repository.deleteByFollowerAndUser(followerId, userId);

        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
