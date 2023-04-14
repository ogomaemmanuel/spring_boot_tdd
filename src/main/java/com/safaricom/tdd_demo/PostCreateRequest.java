package com.safaricom.tdd_demo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PostCreateRequest {
    @NotBlank(message = "Content is required")
    @Size(min = 50)
    private  String content;
    private LocalDate createdAt;
}
