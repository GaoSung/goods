package com.my.service;

import com.my.domain.Goods;
import com.my.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gaoxiang on 2017/5/18.
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public List<Goods> findAll(){
        return goodsRepository.findAll();
    }

    public Goods findOne(Long id){
        return goodsRepository.findOne(id);
    }

    public List<Goods> findAllByName(String name){
        return goodsRepository.findByNameContaining(name);
    }

    @Transactional
    public Goods save(Goods goods){
        return goodsRepository.save(goods);
    }

}
