package com.example.FlapKap.s_project.controller;

import com.example.FlapKap.s_project.dto.SellerDto;
import com.example.FlapKap.s_project.model.Seller;
import com.example.FlapKap.s_project.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/seller")
public class SellerController {
  private final SellerService sellerService;

  @GetMapping("/get/All")
  public ResponseEntity<List<Seller>> getAllSeller() {
        return new ResponseEntity<>(sellerService.getAllSeller(), HttpStatus.OK);
    }
  @GetMapping("/get/{name}")
  public ResponseEntity<Seller> getSellerByName(@PathVariable String name){
      return new ResponseEntity<>(sellerService.getSellerByName(name), HttpStatus.OK);
  }
@PutMapping("/add")
public ResponseEntity<Seller> addSeller(SellerDto seller){
      return new ResponseEntity<>(sellerService.addSeller(seller),HttpStatus.OK);
}
@PostMapping("/update/{id}")
public ResponseEntity<String> updateSeller (@RequestBody SellerDto sellerDto,@PathVariable int id){
     return new ResponseEntity<> (sellerService.updateSeller(sellerDto,id),HttpStatus.OK);
}
@DeleteMapping("/delete")
public ResponseEntity<String> deleteSeller(@RequestBody SellerDto sellerDto){
      return new ResponseEntity<>(sellerService.deleteSeller(sellerDto),HttpStatus.OK);
}


}
