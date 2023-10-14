package com.akk.growbiz.ams.entity;

import com.akk.growbiz.ams.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document("appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {

    @Id
    private String id;

    private LocalDateTime appointmentDate;

    private String service;

    private String staff;

    private String notes;

    private String notification;

    private BigDecimal amount;

    private BigDecimal discount;

    private String duration;

    private String location;

    private Boolean recurring;

    private Integer recurringInstances;

    private String recurringEndDate;

    private String additionalNotes;

    private List<Client> client;
}
