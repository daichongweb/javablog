package daichongweb.blog.dao;

import daichongweb.blog.entity.PostEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PostDao extends JpaRepository<PostEntity, Integer> {

    @Modifying
    @Query(value = "update blog_post set context = :context where id = :id", nativeQuery = true)
    void updateContextById(@Param("id") int id, @Param("context") String context);

    List<PostEntity> findByTitle(@Param("title") String title);

}
