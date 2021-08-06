package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.program_41.entity.Attachment;
import pdp.uz.program_41.entity.Measurement;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    @Query(value = "select count(*) > 0 from attachment join attachment_content on attachment.id=attachment_content.attachment_id where attachment.active=:active and attachment_content.bytes=:bytes", nativeQuery=true)
    boolean existsFileByActiveAndBytes(boolean active, byte[] bytes);

    boolean existsAttachmentByActive(boolean active);
    Page<Attachment> getAttachmentByActive(boolean active, Pageable pageable);
    boolean existsAttachmentByIdAndActive(Integer id, boolean active);

}
