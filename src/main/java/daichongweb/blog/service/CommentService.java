package daichongweb.blog.service;

public interface CommentService {

    // 新增评论
    void addComment(Integer postId, String sayComment);
}
