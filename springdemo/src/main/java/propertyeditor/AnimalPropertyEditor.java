package propertyeditor;

import java.beans.PropertyEditorSupport;

/**
 * @author Dragon
 */
public class AnimalPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return null;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    }

}