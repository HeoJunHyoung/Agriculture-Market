package market.agriculture.controller.apicontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping("/upload")
    public void uploadPost(){

    }

    @PutMapping("/modify")
    public void modifyPost(){

    }

    @DeleteMapping("/delete")
    public void deletePost(){

    }

    @GetMapping("/{post_id}")
    public void getOnePost(@PathVariable("post_id") String post_id){

    }


}
