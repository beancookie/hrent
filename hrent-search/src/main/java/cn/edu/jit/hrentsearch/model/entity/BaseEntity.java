package cn.edu.jit.hrentsearch.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author LuZhong
 * @Date: 2019/2/14 12:54
 * @Description:
 */
@Data
public class BaseEntity {
    @Id
    private String id;
    private String price;
    private String address;
    private String title;
    @Field("rent_type")
    private String rentType;
    @Field("house_type")
    private String houseType;
    private String area;
    private String orientation;
    private String floor;
    private String tags;
    @Field("image_urls")
    private String image_urls;
    private String deploy;
    private String url;
}
