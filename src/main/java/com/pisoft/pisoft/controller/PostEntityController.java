package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.entity.PostEntity;
import com.pisoft.pisoft.service.PostEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostEntityController {

    private final PostEntityService postEntityService;

    @PostMapping("/createPost")
    @PreAuthorize("hasRole('ADMIN') && hasAnyAuthority('POST_CREATE')")
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity postEntity){
        System.out.println("postEntity : "+postEntity);
        return new ResponseEntity<>(postEntityService.save(postEntity) , HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
//    @Secured("{ROLE_USER}")
    @PreAuthorize("hasRole('ADMIN') && hasAnyAuthority('POST_CREATE')")
    public ResponseEntity<List<PostEntity>> getAllPost(){
        return ResponseEntity.ok(postEntityService.findAll());
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("@postSecurity.isOwnerOfThisPost(#id)")
    public ResponseEntity<PostEntity> findById(@PathVariable Long id){

        return ResponseEntity.ok(postEntityService.findById(id));
    }

}
