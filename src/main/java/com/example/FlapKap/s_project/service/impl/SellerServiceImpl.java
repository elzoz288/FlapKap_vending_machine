package com.example.FlapKap.s_project.service.impl;

import com.example.FlapKap.s_project.dto.SellerDto;
import com.example.FlapKap.s_project.exceptions.BadRequestException;
import com.example.FlapKap.s_project.exceptions.SellerNotFoundException;
import com.example.FlapKap.s_project.model.Role;
import com.example.FlapKap.s_project.model.Seller;
import com.example.FlapKap.s_project.repository.SellerRepository;
import com.example.FlapKap.s_project.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
   private final SellerRepository sellerRepo;
    @Override
    public List<Seller> getAllSeller() {
        return sellerRepo.findAll();
    }

    @Override
    public Seller getSellerByName(String name) {
        Seller seller=sellerRepo.findByName(name).orElseThrow(()->new SellerNotFoundException("there is no seller with this name"));

        //if (sellerRepo.existsByName(name)) throw new SellerNotFoundException("there is no seller with name");
        return seller;
    }

    @Override
    public Seller addSeller(SellerDto sellerDto) {
      if (sellerRepo.existsByName(sellerDto.getName())) throw new BadRequestException("seller is already exist");
      Seller seller= Seller.builder().name(sellerDto.getName()).role(Role.SELLER).build();
      return sellerRepo.save(seller);
    }


    @Override
    public String updateSeller(SellerDto sellerDto, int id) {
        Seller testSeller=sellerRepo.findById(id).
                orElseThrow(()->new SellerNotFoundException("there is no seller with this id "));
        testSeller.setName(sellerDto.getName());
        sellerRepo.save(testSeller);
        return "seller updated to "+sellerDto.getName();
    }

    @Override
    public String deleteSeller(SellerDto sellerDto) {
        Seller seller=sellerRepo.findByName(sellerDto.getName())
                .orElseThrow(()->new SellerNotFoundException("there is no seller with name "+sellerDto.getName()));
        sellerRepo.delete(seller);
        return "seller "+sellerDto.getName()+" deleted ";
    }
}
