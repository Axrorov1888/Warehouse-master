package pdp.uz.program_41.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.AttachmentContent;
import pdp.uz.program_41.entity.Measurement;

import java.util.List;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
AttachmentContent getAttachmentContentByAttachmentId(Integer attachment_id);

}
