package com.long1dep.youtuberef11.service.dto.request;
import com.long1dep.youtuberef11.entity.enums.VideoStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UpdateVideoRequest {
    private String id;           // ID bắt buộc để biết cần cập nhật video nào
    private String url;
    private String description;
    private MultipartFile thumbnail; // Nhận file ảnh mới tải lên (nếu có)
    private VideoStatus status;
}