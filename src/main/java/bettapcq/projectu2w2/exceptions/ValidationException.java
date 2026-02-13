package bettapcq.projectu2w2.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private List<String> errorsMsg;

    public ValidationException(List<String> errorsMsg) {

        super("Sono presenti degli errori nel payload");
        this.errorsMsg = errorsMsg;
    }
}
