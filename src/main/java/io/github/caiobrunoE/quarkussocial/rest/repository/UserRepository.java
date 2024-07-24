package io.github.caiobrunoE.quarkussocial.rest.repository;

import io.github.caiobrunoE.quarkussocial.rest.Entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
