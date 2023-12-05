package ea.slartibartfast.demospringbootjpa.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionDirection {
    CREDIT("C"), DEBIT("D");

    private final String shortName;

    public static TransactionDirection fromShortName(String shortName) {
        return switch (shortName) {
            case "C" -> TransactionDirection.CREDIT;
            case "D" -> TransactionDirection.DEBIT;
            default -> throw new IllegalArgumentException("ShortName [" + shortName + "] not supported.");
        };
    }
}
