package com.my.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by gaoxiang on 2017/5/18.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "supplier")
@Data
public class Supplier {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_phone")
    private String contactPhone;

}
