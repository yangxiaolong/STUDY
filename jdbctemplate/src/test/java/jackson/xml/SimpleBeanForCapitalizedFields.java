package jackson.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @auther yangxiaolong
 * @create 2025/8/1
 */
@Data
public class SimpleBeanForCapitalizedFields {
    @JsonProperty("x")
    private int x = 1;
    private int y = 2;

}
