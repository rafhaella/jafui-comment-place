package com.jafui.negocio;

import com.amazonaws.services.kms.model.NotFoundException;
import com.jafui.entidades.PlaceComment;
import com.jafui.persistencia.PlaceCommentRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceCommentService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PlaceCommentRepository placeCommentRepo;

    public PlaceCommentService(PlaceCommentRepository placeCommentRepo) {
        this.placeCommentRepo = placeCommentRepo;
    }

    public List<PlaceComment> getComments() {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os comentários..");
        }
        Iterable<PlaceComment> lista = this.placeCommentRepo.findAll();

        if (lista == null) {
            return new ArrayList<PlaceComment>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public List<PlaceComment> getCommentByPlaceId(String idPlace){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os comentários");
        }
        Iterable<PlaceComment> lista = this.placeCommentRepo.findByidPlace(idPlace);
        if (lista == null) {
            return new ArrayList<PlaceComment>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public PlaceComment getCommentById(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando o comentário por ID: " + id);
        }

        Optional<PlaceComment> comentario = this.placeCommentRepo.findById(id);

        return comentario.orElse(null);
    }



    public PlaceComment saveComment(PlaceComment placeComment){
        if (logger.isInfoEnabled()) {
            logger.info("Salvando comentário com os detalhes {}", placeComment.toString());
        }
        return this.placeCommentRepo.save(placeComment);
    }

    public void updateComment(Long id, String comment) {
        PlaceComment placeComment = placeCommentRepo.findById(id).orElseThrow(() -> 
        new NotFoundException("Não foi possível atualizar o comentário com "+ id));
        placeComment.setComment(comment);
        placeCommentRepo.save(placeComment);
    }

    public void updateUserEmail(Long id, String userEmail) {
        PlaceComment placeComment = placeCommentRepo.findById(id).orElseThrow(() -> 
        new NotFoundException("Não foi possível atualizar o comentário com "+ id));
        placeComment.setUserEmail(userEmail);
        placeCommentRepo.save(placeComment);
    }

    public void updateUserName(Long id, String userName) {
        PlaceComment placeComment = placeCommentRepo.findById(id).orElseThrow(() -> 
        new NotFoundException("Não foi possível atualizar o comentário com "+ id));
        placeComment.setUserEmail(userName);
        placeCommentRepo.save(placeComment);
    }

    public void updateIdPlace(Long id, String idPlace) {
        PlaceComment placeComment = placeCommentRepo.findById(id).orElseThrow(() -> 
        new NotFoundException("Não foi possível atualizar o comentário com "+ id));
        placeComment.setUserEmail(idPlace);
        placeCommentRepo.save(placeComment);
    }

    public void deleteComment(String id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo comentário com id {}",id);
        }
        this.placeCommentRepo.deleteById(id);
    }
}
