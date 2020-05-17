package daichongweb.blog.dao;

import daichongweb.blog.entity.UserEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(@Param("username") String username);
}
