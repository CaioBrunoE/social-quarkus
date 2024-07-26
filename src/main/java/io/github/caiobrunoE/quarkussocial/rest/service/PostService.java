package io.github.caiobrunoE.quarkussocial.rest.service;

import io.github.caiobrunoE.quarkussocial.rest.Entity.Post;
import io.github.caiobrunoE.quarkussocial.rest.Entity.User;
import io.github.caiobrunoE.quarkussocial.rest.dto.PostDto;
import io.github.caiobrunoE.quarkussocial.rest.dto.PostResponseDto;
import io.github.caiobrunoE.quarkussocial.rest.repository.FollowerRepository;
import io.github.caiobrunoE.quarkussocial.rest.repository.PostRepository;
import io.github.caiobrunoE.quarkussocial.rest.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class PostService {

    @Inject
    UserRepository userRepository;

    @Inject
    FollowerRepository followerRepository;

    @Inject
    PostRepository postRepository;

    public Response createPost(Long userId, PostDto dto) {

        User user = userRepository.findById(userId);

        if (user ==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Post post = new Post();
        post.setPostText(dto.getPostText());
        post.setUser(user);
       // post.setDateTime(LocalDateTime.now());
        postRepository.persist(post);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response getAllPosts(Long userId, Long followerId) {
        User user = userRepository.findById(userId);
        if (user ==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (followerId ==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

       User follower = userRepository.findById(followerId);

        if (follower ==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        boolean follows = followerRepository.follows(follower, user);

        if (!follows){
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        PanacheQuery<Post> query = postRepository.find("user", Sort.by("dateTime", Sort.Direction.Descending), user);

        List<Post> posts =query.list();

        var postResposenList = posts
                                    .stream()
                                    .map(p -> PostResponseDto.fromEntity(p))
                                    .collect(Collectors.toList());

        return Response.ok(postResposenList).build();

    }
}
