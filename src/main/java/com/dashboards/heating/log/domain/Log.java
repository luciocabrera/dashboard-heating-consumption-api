package com.dashboards.heating.log.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "logs")
public class Log {

    @Id
    @ApiModelProperty(notes = "The auto-generated if not provided id of the log entry")
    private String id;

    @NotBlank(message = "The Device id can not empty")
    @Size(min = 3, max = 50, message = "The Code must be between 3 and 50 characters long")
    @ApiModelProperty(notes = "The Code of the device, it must be between 3 and 50 characters long")
    private String deviceId;

    @NotBlank(message = "The Reading can not empty")
    @ApiModelProperty(notes = "It is the value taken from the device")
    private Integer  reading;

    @NotBlank(message = "The entry timestamp can not empty")
    @ApiModelProperty(notes = "The entry timestamp indicates the date and time of the entry")
    private Timestamp timestamp;


    @Size(min = 3, max = 128, message = "The Comment must be between 12 and 128 characters long")
    @ApiModelProperty(notes = "The comment helps to add anything related to the entry log registered, it must be between 12 and 128 characters long")
    private String comment;

}