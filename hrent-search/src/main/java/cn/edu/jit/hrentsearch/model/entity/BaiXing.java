package cn.edu.jit.hrentsearch.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author LuZhong
 * @Date: 2019/2/14 13:08
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiXing extends BaseEntity {
    @Field("update_date")
    private String updateDate;
    private String decoration;
}
