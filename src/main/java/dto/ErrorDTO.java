package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class ErrorDTO {

    private int status;
    private String error;
    private Object message;
    private String path;
}
