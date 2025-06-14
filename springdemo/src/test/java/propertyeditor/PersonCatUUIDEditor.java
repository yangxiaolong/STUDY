package propertyeditor;

import org.springframework.beans.propertyeditors.UUIDEditor;

/**
 * @author Dragon
 */
public class PersonCatUUIDEditor extends UUIDEditor {

    public static final String SUFFIX = "_SUFFIX";

    @Override
    public String getAsText() {
        return super.getAsText().concat(SUFFIX);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.replace(SUFFIX, "");
        super.setAsText(text);
    }
}