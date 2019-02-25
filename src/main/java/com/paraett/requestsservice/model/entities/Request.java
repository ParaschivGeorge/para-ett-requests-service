package com.paraett.requestsservice.model.entities;

import com.paraett.requestsservice.model.enums.RequestType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_tbl")
public class Request {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private Date date;

    @Column
    private RequestType type;

    @Column
    private Boolean approved;

    public Request() {
    }
}
