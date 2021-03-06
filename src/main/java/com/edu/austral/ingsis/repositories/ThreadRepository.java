package com.edu.austral.ingsis.repositories;

import com.edu.austral.ingsis.entities.Thread;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends CrudRepository<Thread, Long> {

  List<Thread> findAll();

  @Query(value = "select * from threads t where t.id in (select tp.thread_id from threads_posts tp where tp.posts_id = ?1)", nativeQuery = true)
  Thread findThreadOfPostById(Long id);

  @Query(value = "select * from threads t where t.id in (select tp.thread_id from threads_posts tp where tp.posts_id in (select p.id from posts p where p.user_id = ?1))", nativeQuery = true)
  List<Thread> getAllThatContainUserPost(Long id);

  @Query(value = "select * from threads t where t.id in (select tp.thread_id from threads_posts tp where tp.posts_id = ?1)", nativeQuery = true)
  Thread getByPostId(Long id);
}
