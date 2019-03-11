package cn.edu.jit.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * @author LuZhong
 * @date 2019/3/1 14:02
 * @description
 */
@Data
@Document(collection = "ziroom")
@AllArgsConstructor
@NoArgsConstructor
public class House {
    @Id
    private String id;
    private String title;
    @Field("image_urls")
    private Set<String> imageUrls;
    private String url;
    private String price;
    private String detail;
}
