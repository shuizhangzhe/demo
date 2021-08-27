package demo.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestVO {
    private String userName;
    private String password;
    private String fullName;
    private Boolean enabled;
}
