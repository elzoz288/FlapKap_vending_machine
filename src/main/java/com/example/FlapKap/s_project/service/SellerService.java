package com.example.FlapKap.s_project.service;

import com.example.FlapKap.s_project.dto.SellerDto;
import com.example.FlapKap.s_project.model.Seller;

import java.util.List;

public interface SellerService {
    List<Seller> getAllSeller ();
    Seller getSellerByName (String name);
    Seller addSeller(SellerDto seller);
    String updateSeller(SellerDto seller , int id);

    String deleteSeller( SellerDto sellerDto);
}
