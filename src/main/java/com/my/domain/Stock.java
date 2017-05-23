package com.my.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by gaoxiang on 2017/5/18.
 *
 * 库存实体类
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "stock")
@Data
public class Stock extends AbstractAuditingEntity implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 总共库存量
     */
    @Column(name="total_quantity")
    private Integer totalQuantity;

    /**
     * 剩余库存量
     */
    @Column(name="remain_quantity")
    private Integer remainQuantity;

    /**
     * 商品信息
     */
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="goods_id")
    private Goods goods;


}
