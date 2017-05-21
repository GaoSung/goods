package com.my.service;

import com.my.domain.Goods;
import com.my.domain.Supplier;
import com.my.repository.GoodsRepository;
import com.my.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaoxiang on 2017/5/18.
 */
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public Supplier findOne(Long id){
        return supplierRepository.findOne(id);
    }

    public List<Supplier> findAllByName(String name){return supplierRepository.findByNameContaining(name);}

    public Supplier save(Supplier supplier){return supplierRepository.save(supplier);}

    public void delete(Long id){supplierRepository.delete(id);}

}
