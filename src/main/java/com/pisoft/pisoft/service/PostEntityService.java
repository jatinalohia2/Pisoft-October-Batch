package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.PostEntity;
import com.pisoft.pisoft.entity.Users;
import com.pisoft.pisoft.exception.ResourceNotFound;
import com.pisoft.pisoft.repository.PostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostEntityService {

    private final PostEntityRepository postEntityRepository;

    public PostEntity save(PostEntity postEntity){

        // save user :

        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postEntity.setUsers(users);
        return postEntityRepository.save(postEntity);
    }

    public List<PostEntity> findAll() {
        return postEntityRepository.findAll();
    }

    public PostEntity findById(Long id){
        return postEntityRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("Post not found with Id : "+id));
    }
}
