package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Builder
@ToString
@Setter
@Getter

public class GetAllContactsDTO {
    private List<ContactDTO> contacts;
}
