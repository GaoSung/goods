package com.my.service;

import com.my.domain.Goods;
import com.my.domain.Stock;
import com.my.domain.Supplier;
import com.my.repository.GoodsRepository;
import com.my.repository.StockRepository;
import com.my.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoxiang on 2017/5/18.
 */
@Service
public class StockService {

    @Autowired
    public GoodsRepository goodsRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAll(){
        return stockRepository.findAll();
    }

    public Stock findOne(Long id){
        return stockRepository.findOne(id);
    }

    public List<Stock> findStocksByGoods(Goods goods){
        List<Goods> goodsList = goodsRepository.findByNameContaining(goods.getName());

        List<Long> ids = null;
        if(goodsList!=null&&goodsList.size()>0){
            ids = new ArrayList<>();
            for(Goods good:goodsList){
                ids.add(good.getId());
            }
        }

        return stockRepository.findAll(ids);
    }

    public Stock save(Stock stock){return stockRepository.save(stock);}

}
