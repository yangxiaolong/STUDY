package jackson.jsongenerate;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Dragon
 */
@Data
public class User {
    private String name = "YourBatman";
    private Integer age = 18;

    @Test
    public void test11() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jsonGenerator.setCodec(new UserObjectCodec());

            jsonGenerator.writeObject(new User());
        }
    }

}