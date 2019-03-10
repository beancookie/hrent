package cn.edu.jit.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author LuZhong
 * @date 2019/3/1 14:02
 * @description
 */
@Data
@Document(collection = "hrent")
@AllArgsConstructor
@NoArgsConstructor
public class House {
    @Id
    private String id;
    private String title;
    private String imageUrl;
    private String price;
    private String detail;
}
