package com.basic.mykomework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerReqDTO {
        private Long id;
        private Long age;
        private String email;
        private String name;
        private LocalDateTime entryDate;
}
