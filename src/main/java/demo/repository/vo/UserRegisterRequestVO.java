package demo.repository.vo;

import demo.repository.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestVO {
    private String userName;
    private String password;
    private String fullName;

    public User toUser(){
        return User.builder()
                .userName(this.getUserName())
                .fullName(this.getFullName())
                .enabled(true).build();
    }
}
