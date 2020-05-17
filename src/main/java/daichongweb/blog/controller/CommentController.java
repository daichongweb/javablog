package daichongweb.blog.controller;

import daichongweb.blog.service.CommentService;
import daichongweb.blog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/sayPost")
    public JsonResult sayPost(Integer postId, String sayComment) {
        commentService.addComment(postId, sayComment);
        return new JsonResult<>(200, "评论成功");
    }
}
