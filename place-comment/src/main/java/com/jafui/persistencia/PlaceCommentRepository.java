package com.jafui.persistencia;

import com.jafui.entidades.PlaceComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceCommentRepository extends CrudRepository<PlaceComment, String> {

    List<PlaceComment> findByuserEmail(String userEmail);

   List<PlaceComment> findByidPlace(String idPlace);

    Optional<PlaceComment> findById(Long id);
}
