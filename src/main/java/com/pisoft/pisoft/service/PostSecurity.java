package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.PostEntity;
import com.pisoft.pisoft.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostSecurity {

    private final PostEntityService postEntityService;

    public boolean isOwnerOfThisPost(Long postId){

        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity postEntity = postEntityService.findById(postId);
        return postEntity.getUsers().getId().equals(users.getId());
    }

}
