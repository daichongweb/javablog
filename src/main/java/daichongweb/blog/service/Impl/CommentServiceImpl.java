package daichongweb.blog.service.Impl;

import daichongweb.blog.dao.CommentDao;
import daichongweb.blog.entity.CommentEntity;
import daichongweb.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public void addComment(Integer postId, String sayComment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPostId(postId);
        commentEntity.setContext(sayComment);
        commentEntity.setDeleteAt(null);
        commentEntity.setStatus("normal");
        commentDao.save(commentEntity);
    }
}
