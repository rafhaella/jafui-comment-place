package com.jafui.rest;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.jafui.entidades.PlaceComment;
import com.jafui.negocio.PlaceCommentService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "place_comment")
public class PlaceCommentController {

    private final PlaceCommentService placeCommentService;

    public PlaceCommentController(PlaceCommentService placeCommentService){
        this.placeCommentService=placeCommentService;
    }

    @GetMapping
    public List<PlaceComment> getComments() {

        return placeCommentService.getComments();
    }

    @GetMapping(value="/by_place/{idPlace}")
    public List<PlaceComment> getCommentByPlaceId(@PathVariable String idPlace) throws Exception{
        if(!ObjectUtils.isEmpty(idPlace)){
           return placeCommentService.getCommentByPlaceId(idPlace);
        }
        throw new Exception("Comentários do lugar com  "+idPlace+" nao encontrados");
    }

    @GetMapping(value="{id}")
    public PlaceComment getCommentById(@PathVariable Long id) throws Exception {
        if (!ObjectUtils.isEmpty(id)) {
            return placeCommentService.getCommentById(id);
        }
        throw new Exception("Comentário com ID " + id + " não encontrado");
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceComment addComment(@RequestBody @NotNull PlaceComment placeComment) throws Exception {
        return placeCommentService.saveComment(placeComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceComment> updateComment(@PathVariable Long id, @RequestBody PlaceComment placeComment) {
        try {
            if (placeComment.getComment() != null && !placeComment.getComment().isEmpty()) {
                placeCommentService.updateComment(id, placeComment.getComment());
            }
            if (placeComment.getUserEmail() != null && !placeComment.getUserEmail().isEmpty()) {
                placeCommentService.updateUserEmail(id, placeComment.getUserEmail());
            }
            if (placeComment.getUserName() != null && !placeComment.getUserName().isEmpty()) {
                placeCommentService.updateUserName(id, placeComment.getUserName());
            }
            if (placeComment.getIdPlace() != null && !placeComment.getIdPlace().isEmpty()) {
                placeCommentService.updateIdPlace(id, placeComment.getIdPlace());
            }
           
            PlaceComment updateComment = (PlaceComment) placeCommentService.getCommentById(id);
            return ResponseEntity.ok(updateComment);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean deleteUser(@PathVariable String id) throws Exception {
        placeCommentService.deleteComment(id);
        return true;
    }
    
}
