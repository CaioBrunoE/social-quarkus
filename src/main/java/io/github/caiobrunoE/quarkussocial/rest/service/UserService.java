package io.github.caiobrunoE.quarkussocial.rest.service;

import io.github.caiobrunoE.quarkussocial.rest.Entity.User;
import io.github.caiobrunoE.quarkussocial.rest.dto.UserDto;
import io.github.caiobrunoE.quarkussocial.rest.error.ResponseError;
import io.github.caiobrunoE.quarkussocial.rest.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@ApplicationScoped
@Transactional
public class UserService {

    @Inject
    UserRepository repository;

    @Inject
    Validator validator;

  public Response create(UserDto dto){

      Set<ConstraintViolation<UserDto>> violation = validator.validate(dto);

      if (!violation.isEmpty()){
          return ResponseError.createFromValidation(violation).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
      }
      User user = new User();
      user.setName(dto.getName());
      user.setAge(dto.getAge());

      repository.persist(user);

      return Response
              .status(Response.Status.CREATED.getStatusCode())
              .entity(user)
              .build();
  }
  public Response getAll(){
      PanacheQuery<User> listUser = repository.findAll();
      return Response.ok(listUser.list()).build();
  }

    public Response update( Long id, UserDto dto) {
      User user =  repository.findById(id);
        if (user != null){
            user.setName(dto.getName());
            user.setAge(dto.getAge());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(Long id) {
     User user = repository.findById(id);
      if (user != null){
          repository.delete(user);
          return Response.noContent().build();
      }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response getById(Long id) {
     User user = repository.findById(id);
        if (user != null){
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
