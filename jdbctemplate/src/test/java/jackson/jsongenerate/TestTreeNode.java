package jackson.jsongenerate;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Dragon
 */
public class TestTreeNode {

    @Test
    public void test12() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jsonGenerator.setCodec(new UserObjectCodecTreeNode());
            jsonGenerator.writeObject(new UserTreeNode(new User()));
        }
    }

}