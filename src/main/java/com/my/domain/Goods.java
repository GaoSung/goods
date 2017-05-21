package com.my.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by gaoxiang on 2017/5/18.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "goods")
@Data
public class Goods extends AbstractAuditingEntity implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Double cost;

    @Column
    private Byte status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;


}
