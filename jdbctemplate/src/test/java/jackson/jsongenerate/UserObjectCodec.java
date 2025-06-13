package jackson.jsongenerate;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Dragon
 */
// 自定义ObjectCodec解码器 用于把User写为JSON
// 因为本例只关注write写，因此只需要实现此这一个方法即可
public class UserObjectCodec extends ObjectCodec {
    @Override
    public void writeValue(JsonGenerator gen, Object value) throws IOException {
        User user = User.class.cast(value);

        gen.writeStartObject();
        gen.writeStringField("name", user.getName());
        gen.writeNumberField("age", user.getAge());
        gen.writeEndObject();
    }

    @Override
    public Version version() {
        return null;
    }

    @Override
    public <T> T readValue(JsonParser jsonParser, Class<T> aClass) throws IOException {
        return null;
    }

    @Override
    public <T> T readValue(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
        return null;
    }

    @Override
    public <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser jsonParser, Class<T> aClass) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
        return null;
    }

    @Override
    public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException {
        return null;
    }

    @Override
    public void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException {

    }

    @Override
    public TreeNode createObjectNode() {
        return null;
    }

    @Override
    public TreeNode createArrayNode() {
        return null;
    }

    @Override
    public JsonParser treeAsTokens(TreeNode treeNode) {
        return null;
    }

    @Override
    public <T> T treeToValue(TreeNode treeNode, Class<T> aClass) throws JsonProcessingException {
        return null;
    }
}