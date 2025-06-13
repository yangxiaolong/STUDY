package jackson.jsonparser;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class PersonObjectCodec extends ObjectCodec {

    @Override
    public Version version() {
        return null;
    }

    @Override
    public <T> T readValue(JsonParser jsonParser, Class<T> valueType) throws IOException {
        Person person;
        try {
            person = (Person) valueType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

        // 只要还没结束"}"，就一直读
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.currentName();
            if ("name".equals(fieldname)) {
                jsonParser.nextToken();
                person.setName(jsonParser.getText());
            } else if ("age".equals(fieldname)) {
                jsonParser.nextToken();
                person.setAge(jsonParser.getIntValue());
            }
        }

        return (T) person;
    }

    @Override
    public <T> T readValue(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
        return null;
    }

    @Override
    public <T> T readValue(JsonParser p, ResolvedType valueType) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p, Class<T> valueType) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
        return null;
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser p, ResolvedType valueType) throws IOException {
        return null;
    }

    @Override
    public void writeValue(JsonGenerator gen, Object value) throws IOException {

    }

    @Override
    public <T extends TreeNode> T readTree(JsonParser p) throws IOException {
        return null;
    }

    @Override
    public void writeTree(JsonGenerator gen, TreeNode tree) throws IOException {

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
    public JsonParser treeAsTokens(TreeNode n) {
        return null;
    }

    @Override
    public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
        return null;
    }

}