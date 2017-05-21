package com.my.repository;

import com.my.domain.Goods;
import com.my.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findStocksByGoodsContains(Goods goods);

}