package io.github.caiobrunoE.quarkussocial.rest.repository;

import io.github.caiobrunoE.quarkussocial.rest.Entity.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {


}
