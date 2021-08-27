package demo.repository.vo;

import demo.repository.po.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAddRequestVO {
    private String tag;
    private String name;
    private String description;

    public Role toRole(){
        return Role.builder()
                .tag(this.getTag())
                .name(this.getName())
                .description(this.getDescription())
                .build();
    }
}
