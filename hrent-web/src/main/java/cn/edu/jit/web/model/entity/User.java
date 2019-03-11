package cn.edu.jit.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String nickname;
    private String mobile;
    private String email;
    private String password;
    private String avatar;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String status;
    private List<String> collections = new ArrayList<>();
}
