package pdp.uz.program_41.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
@Autowired
AttachmentService attachmentService;

@PostMapping
    public Result add(MultipartHttpServletRequest request) throws IOException {
    return attachmentService.add(request);
}
@GetMapping
    public Result get(@RequestParam int page){
    return attachmentService.get(page);
}
@GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
    return attachmentService.getById(id);
}
@GetMapping("/contentId/{id}")
    public Result getContentById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
    return attachmentService.getContentById(id, response);
}
@PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
    return attachmentService.edit(id, request);
}
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
    return attachmentService.delete(id);
}
}
