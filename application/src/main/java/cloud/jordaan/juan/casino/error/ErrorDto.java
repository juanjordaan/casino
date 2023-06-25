package cloud.jordaan.juan.casino.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    // TODO - Use pre defined codes and messages
    String code = "1234";
    String message = "Hi Player, here is a beautiful formatted error message";
    String developerMessage;

    public static ErrorDto INSTANCE(String developerMessage) {
        ErrorDto dto = new ErrorDto();
        dto.developerMessage = developerMessage;

        return dto;
    }
}
