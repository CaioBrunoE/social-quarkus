package io.github.caiobrunoE.quarkussocial.rest.dto;

import io.github.caiobrunoE.quarkussocial.rest.Entity.Follower;
import lombok.Data;

import java.util.List;

@Data
public class FollowerUserResponse {

    private Integer FollowersConunt;
    private List<FollowerResponse> content;

}
