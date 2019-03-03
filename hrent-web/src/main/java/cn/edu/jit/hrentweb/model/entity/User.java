package cn.edu.jit.hrentweb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LuZhong
 * @date 2019/3/1 14:02
 * @description
 */
@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String nickname;
    private String mobile;
    private String email;
    private String password;
    private String avatar;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String status;
}
