package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.uz.program_41.entity.Attachment;
import pdp.uz.program_41.entity.AttachmentContent;
import pdp.uz.program_41.payload.CategoryDto;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.AttachmentContentRepository;
import pdp.uz.program_41.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result add(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
            if (file!=null){
                String fileOriginalName = file.getOriginalFilename();
                long size = file.getSize();
                String contentType = file.getContentType();
                byte[] bytes = file.getBytes();

boolean existsFileByActiveAndBytes = attachmentRepository.existsFileByActiveAndBytes(true, bytes);
                if(existsFileByActiveAndBytes){
                    return new Result("Such file already exist!",false);
                }
                Attachment attachment =  new Attachment();
                attachment.setName(fileOriginalName);
                attachment.setSize(size);
                attachment.setContentType(contentType);
                Attachment savedAttachment = attachmentRepository.save(attachment);

                AttachmentContent attachmentContent = new AttachmentContent(null, bytes, savedAttachment);
                attachmentContentRepository.save(attachmentContent);
                return new Result("New photo successfully saved.", true);
            }
            return new Result("This file does not have any photo!",false);
        }

public Result get(int page){
        boolean existsAttachmentByActive = attachmentRepository.existsAttachmentByActive(true);
        if(existsAttachmentByActive){
            Pageable pageable = PageRequest.of(page, 10);
            Page<Attachment> page1=attachmentRepository.getAttachmentByActive(true, pageable);
            return new Result(page1);
        }
        return new Result("Attachments not exist yet!", false);
}

public Result getById(Integer id){
        boolean existsAttachmentByIdAndActive = attachmentRepository.existsAttachmentByIdAndActive(id, true);
        if(!existsAttachmentByIdAndActive){
            return new Result("Such attachment id not exist!", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return new Result(optionalAttachment.get());
}



public Result getContentById(Integer id, HttpServletResponse response) throws IOException {
        boolean existsAttachmentByIdAndActive = attachmentRepository.existsAttachmentByIdAndActive(id, true);
        if(!existsAttachmentByIdAndActive){
            return new Result("Such attachment id not exist!", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment = optionalAttachment.get();
AttachmentContent attachmentContent = attachmentContentRepository.getAttachmentContentByAttachmentId(attachment.getId());

response.setHeader("Content-Disposition", "attachment; filename=\""+attachment.getName()+"\"");
response.setContentType(attachment.getContentType());
FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
    return new Result("File successfully send.", true);
    }

    public Result edit(Integer id, MultipartHttpServletRequest request) throws IOException {
        boolean existsAttachmentByIdAndActive = attachmentRepository.existsAttachmentByIdAndActive(id, true);
        if(!existsAttachmentByIdAndActive){
            return new Result("Such attachment id not exist!",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment= optionalAttachment.get();
    AttachmentContent attachmentContent = attachmentContentRepository.getAttachmentContentByAttachmentId(attachment.getId());

    Iterator<String> fileNames = request.getFileNames();
    MultipartFile file = request.getFile(fileNames.next());
    if(file!=null){
        String originalFileName= file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();
        byte[] fileBytes = file.getBytes();

        boolean existsFileByActiveAndBytes = attachmentRepository.existsFileByActiveAndBytes(true, fileBytes);
        if(!existsFileByActiveAndBytes  || Arrays.equals(attachmentContent.getBytes(), fileBytes)){
       attachment.setName(originalFileName);
       attachment.setSize(size);
       attachment.setContentType(contentType);
       attachmentContent.setBytes(fileBytes);
       attachmentRepository.save(attachment);
       attachmentContentRepository.save(attachmentContent);
       return new Result("Given attachment successfully edited.",true);
        }else{
            return new Result("Such file already exist!", false);
        }
    }
    return new Result("This file does not have any photo!", false);
    }

    public Result delete(Integer id){
        boolean existsAttachmentByIdAndActive =attachmentRepository.existsAttachmentByIdAndActive(id, true);
        if(!existsAttachmentByIdAndActive){
            return new Result("Such attachment id not exist!", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment = optionalAttachment.get();
        attachment.setActive(false);
        attachmentRepository.save(attachment);
        return new Result("given attachment successfully deleted.", true);
    }

    @RestController
    @RequestMapping("/category")
    public static class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
        public Result add(@RequestBody CategoryDto categoryDto){
        return categoryService.add(categoryDto);
    }
    @GetMapping
        public Result get(@RequestParam int page){
        return categoryService.get(page);
    }
    @GetMapping("/{id}")
        public Result getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }
    @PutMapping("/{id}")
        public Result edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.edit(id,categoryDto);
    }
    @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }
    }
}
