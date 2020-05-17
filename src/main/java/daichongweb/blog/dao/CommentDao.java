package daichongweb.blog.dao;

import daichongweb.blog.entity.CommentEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CommentDao extends JpaRepository<CommentEntity, Integer> {

    // 查询文章下的所以评论
    List<CommentEntity> findByPostIdAndStatus(@Param("post_id") Integer id, @Param("status") String status);
}
