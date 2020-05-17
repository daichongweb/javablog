package daichongweb.blog.service;

import daichongweb.blog.entity.PostEntity;
import daichongweb.blog.result.PostCommentResult;
import org.springframework.data.domain.Page;
import java.util.List;

public interface PostService{

    // 添加文章
    void createPost(PostEntity postEntity);

    // 根据id修改文章内容
    void saveContextById(Integer id, String context);

    // 根据id查询文章
    PostCommentResult getPost(Integer id);

    // 获取全部文章
     Page<PostEntity> getPostAll(Integer pageNo);

    // 搜索标题
     List<PostEntity> getPostLikeTitle(String searchTitle);

    // 删除
    void deleteById(int id);



}
