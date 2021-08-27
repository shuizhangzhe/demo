package demo.repository.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}