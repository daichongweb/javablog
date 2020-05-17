package daichongweb.blog.controller;

import daichongweb.blog.entity.PostEntity;
import daichongweb.blog.result.PostCommentResult;
import daichongweb.blog.service.PostService;
import daichongweb.blog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Transactional
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/postSave")
    public JsonResult save(@RequestBody PostEntity postEntity) {
        postService.createPost(postEntity);
        return new JsonResult<>("200", "修改成功");
    }

    @PostMapping("/postSaveContext")
    public JsonResult saveContext(@RequestBody PostEntity postEntity) {
        postService.saveContextById(postEntity.getId(), postEntity.getContext());
        return new JsonResult<>("200", "修改成功");
    }

    @PostMapping("/getPost")
    public JsonResult getPostById(Integer id) {
        PostCommentResult postInfo = postService.getPost(id);
        return new JsonResult<>(postInfo, "查询成功");
    }

    @PostMapping("/getPostAll")
    public JsonResult getPostAll(Integer pageNo, String title) {
        List<PostEntity> list = new ArrayList<>();
        if (title != null) {
            list = postService.getPostLikeTitle(title);
            return new JsonResult<>(list, "查询成功");
        } else {
            Page<PostEntity> list1 = postService.getPostAll(pageNo);
            Map<String, Object> map = new HashMap<>(3);
            map.put("data", list1.getContent());
            map.put("count", list1.getTotalElements());
            map.put("pageCount", list1.getTotalPages());
            return new JsonResult<>(map, "查询成功");
        }
    }

    @PostMapping("/deletePost")
    public JsonResult delete(@RequestBody PostEntity postEntity) {
        postService.deleteById(postEntity.getId());
        return new JsonResult<>("200", "删除成功");
    }
}
