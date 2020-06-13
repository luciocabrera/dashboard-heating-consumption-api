package com.dashboards.heating.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "devices")
public class Device {

    @Id
    @ApiModelProperty(notes = "The auto-generated if not provided id of the device")
    private String id;

    @NotBlank(message = "The Code can not empty")
    @Size(min = 3, max = 24, message = "The Code must be between 3 and 24 characters long")
    @ApiModelProperty(notes = "The Code of the device, it must be between 3 and 24 characters long")
    private String code;

    @NotBlank(message = "The Name can not empty")
    @Size(min = 3, max = 64, message = "The Name must be between 3 and 64 characters long")
    @ApiModelProperty(notes = "The Name of the device, it must be between 3 and 64 characters long")
    private String name;

    @NotBlank(message = "The Description can not empty")
    @Size(min = 3, max = 128, message = "The Description must be between 12 and 128 characters long")
    @ApiModelProperty(notes = "The Description of the device, it must be between 12 and 128 characters long")
    private String description;

}
