package com.lotusntp.training.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name= "m_social")
public class Social extends BaseEntity {

    @Column(nullable = false,length = 120)
    private String facebook;

    @Column(nullable = false,length = 120)
    private String line;

    @Column(nullable = false,length = 120)
    private String instagram;

    @Column(nullable = false,length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "m_user_id",nullable = false)
    private User user;
}
