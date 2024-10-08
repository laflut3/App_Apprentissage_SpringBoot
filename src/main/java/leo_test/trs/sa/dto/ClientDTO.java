package leo_test.trs.sa.dto;

import jakarta.persistence.Column;

public record ClientDTO(
        int id,
        String email,
        String telephone
) {

}
