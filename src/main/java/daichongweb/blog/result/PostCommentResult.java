package daichongweb.blog.result;

import daichongweb.blog.entity.CommentEntity;
import daichongweb.blog.entity.PostEntity;

import java.util.List;

public class PostCommentResult {

    public PostEntity postInfo;

    public List<CommentEntity> commentList;

    public PostCommentResult(PostEntity postEntity, List<CommentEntity> commentEntityList) {
        this.commentList = commentEntityList;
        this.postInfo = postEntity;
    }
}
