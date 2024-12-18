package com.hype360kh.explore.utils.document.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.CUSTOM, property = "error", visible = true)
public class ResponseErrorHandler {
    private HttpStatus status;
    private int statusCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
    private Date timestamp;

    private String message;
    private String debugMessage;

    private ResponseErrorHandler() {
        timestamp = Date.from(Instant.now());
    }

    public ResponseErrorHandler(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public Date getTimestamp() {
        return Objects.nonNull(this.timestamp) ? Date.from(this.timestamp.toInstant()) : null;
    }
}
