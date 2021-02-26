package ruan.consumer.pojo.ao;

import lombok.*;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* sql耗时
*
* @author ruan
* @since 2021-02-26
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SqlElapsedTimeAo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * 主键
    */
    private Long id;
    /**
    * sql
    */
    private String sql;
    /**
    * sql耗时
    */
    private Integer elapsedTime;
    /**
    * 创建时间
    */
    private Timestamp createdTime;
    /**
    * 更新时间
    */
    private Timestamp updatedTime;

    public static final String ID = "id";
    public static final String SQL = "sql";
    public static final String ELAPSED_TIME = "elapsed_time";
    public static final String CREATED_TIME = "created_time";
    public static final String UPDATED_TIME = "updated_time";
}
