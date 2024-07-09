package com.example.monitorio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Metrics {

    @Column(unique = true, nullable = false)
    private Timestamp timestamp;

    @Column(unique = true, nullable = false)
    private Number cpu_usage;

    @Column(unique = true, nullable = false)
    private Number memory_usage;

    @Column(unique = true, nullable = false)
    private Number response_time;

    @Column(unique = true, nullable = false)
    private Number request_count;

}
