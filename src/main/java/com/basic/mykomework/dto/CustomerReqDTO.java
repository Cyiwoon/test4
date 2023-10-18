package com.basic.mykomework.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerReqDTO {
        private Long id;
        @NotNull(message = "age는 필수 입력 항목입니다")   // 공백 안 됨
        private Long age;
        @Email(message = "email 형식이 아닙니다")
        @NotBlank(message = "email은 필수 입력 항목입니다")   // 공백 안 됨
        private String email;
        @NotEmpty(message = "name은 필수 입력 항목입니다")   // 공백 허용
        private String name;
        private LocalDateTime entryDate;
}
