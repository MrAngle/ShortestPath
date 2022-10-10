package com.lippio.shortest_path.errors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DTOResponse {

    private final String message;
    private final Integer status;

}
