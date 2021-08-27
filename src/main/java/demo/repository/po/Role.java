package demo.repository.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Role {
    private Long id;
    private String tag;
    private String name;
    private String description;

    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}