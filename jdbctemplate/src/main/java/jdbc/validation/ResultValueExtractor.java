package jdbc.validation;

import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.ValueExtractor;

/**
 * @author Dragon
 */
public class ResultValueExtractor implements ValueExtractor<Result<@ExtractedValue ?>> {

    @Override
    public void extractValues(Result<?> originalValue, ValueReceiver receiver) {
        receiver.value(null, originalValue.getData());
    }
}
