package com.iceb.library.dto.publisher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherResponseDto {

    private UUID id;
    private String name;
    private Boolean archived;

}
