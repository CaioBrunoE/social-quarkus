package io.github.caiobrunoE.quarkussocial.rest.dto;

import io.github.caiobrunoE.quarkussocial.rest.Entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {

    private String postText;
    private LocalDateTime dateTime;

    public static PostResponseDto fromEntity(Post post){
        PostResponseDto response = new PostResponseDto();

        response.setPostText(post.getPostText());
        response.setDateTime(post.getDateTime());
        return response;
    }
}
