package org.example.blogsystem.repository;

import lombok.NonNull;
import lombok.experimental.NonFinal;
import org.example.blogsystem.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post>findByUser_UserName(String username);
    @NonNull
  Optional<Post>findById(@NonNull Long id);


}
