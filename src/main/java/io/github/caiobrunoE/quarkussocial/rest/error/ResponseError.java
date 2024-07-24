package io.github.caiobrunoE.quarkussocial.rest.error;

import io.github.caiobrunoE.quarkussocial.rest.dto.UserDto;
import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResponseError {

    public static final int UNPROCESSABLE_ENTITY_STATUS =422;

    private String message;
    private Collection<FieldError> errors;

    public static ResponseError createFromValidation(Set<ConstraintViolation<UserDto>> violations){
        List<FieldError> errors = violations
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());

        String message = "validation Error";

        return  new ResponseError(message, errors);
    }

    public ResponseError(String message, Collection<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<FieldError> errors) {
        this.errors = errors;
    }

    public Response withStatusCode(int code){

        return Response.status(code).entity(this).build();
    }

}
