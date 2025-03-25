package market.agriculture.exception.custom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter @Setter
public class ErrorResult {
    private String code;
    private String message;

    public ErrorResult() {
    }

    public ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    
}
