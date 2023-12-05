package ea.slartibartfast.demospringbootjpa.model.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionDirectionConverter implements AttributeConverter<TransactionDirection, String> {

    @Override
    public String convertToDatabaseColumn(TransactionDirection direction) {
        return direction.getShortName();
    }

    @Override
    public TransactionDirection convertToEntityAttribute(String dbData) {
        return TransactionDirection.fromShortName(dbData);
    }

}