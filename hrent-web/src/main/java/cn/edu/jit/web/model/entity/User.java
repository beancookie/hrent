package cn.edu.jit.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<String> collections = new HashSet<>();
}
