package cn.edu.jit.web.model.dto;

import cn.edu.jit.web.model.entity.User;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @author LuZhong
 */
@Data
public class UserDTO extends User {
    @Transient
    private String code;

    @Transient
    private String houseUrl;

    @Transient
    private String userId;
}
