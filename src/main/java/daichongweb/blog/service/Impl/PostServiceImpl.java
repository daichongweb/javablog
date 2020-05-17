package daichongweb.blog.service.Impl;

import daichongweb.blog.dao.CommentDao;
import daichongweb.blog.dao.PostDao;
import daichongweb.blog.entity.CommentEntity;
import daichongweb.blog.entity.PostEntity;
import daichongweb.blog.result.PostCommentResult;
import daichongweb.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public void createPost(PostEntity postEntity) {
        postEntity.setTitle(postEntity.getTitle());
        postEntity.setAuthor(postEntity.getAuthor());
        postEntity.setTag(postEntity.getTag());
        postEntity.setDescript(postEntity.getDescript());
        postEntity.setContext(postEntity.getContext());
        postDao.save(postEntity);
    }

    @Override
    public void saveContextById(Integer id, String context) {
        postDao.updateContextById(id, context);
    }

    @Override
    public Page<PostEntity> getPostAll(Integer pageNo) {
        PageRequest pageParam = PageRequest.of(pageNo, 2);
        Page<PostEntity> list = postDao.findAll(pageParam);
        return list;
    }

    @Override
    public PostCommentResult getPost(Integer id) {
        PostEntity postEntity = postDao.findById(id).get();
        List<CommentEntity> commentEntityList = commentDao.findByPostIdAndStatus(id, "normal");
        return new PostCommentResult(postEntity, commentEntityList);
    }

    @Override
    public List<PostEntity> getPostLikeTitle(String searchTitle) {
        return postDao.findByTitle(searchTitle);
    }

    @Override
    public void deleteById(int id) {
        postDao.deleteById(id);
    }
}
