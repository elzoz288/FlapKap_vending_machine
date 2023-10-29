package com.example.FlapKap.s_project.service.impl;

import com.example.FlapKap.s_project.dto.SellerDto;
import com.example.FlapKap.s_project.exceptions.BadRequestException;
import com.example.FlapKap.s_project.exceptions.SellerNotFoundException;
import com.example.FlapKap.s_project.model.Role;
import com.example.FlapKap.s_project.model.Seller;
import com.example.FlapKap.s_project.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceImplTest {
    @Mock
   private SellerRepository sellerRepo;

    @InjectMocks
    private SellerServiceImpl underTest;

    @Test
    void tryToGetAllSeller() {
        List<Seller> sellers= Collections.singletonList(mock(Seller.class));

        given(sellerRepo.findAll()).willReturn(sellers);
        assertThat(underTest.getAllSeller()).isNotNull();
        assertThat(underTest.getAllSeller()).isEqualTo(sellers);
    }

    @Test
    void getSellerByName() {
        Seller seller = mock(Seller.class);
        given(sellerRepo.findByName(seller.getName())).willReturn(Optional.ofNullable(seller));
        assertThat(underTest.getSellerByName(seller.getName())).isEqualTo(seller);
    }

    @Test
    void canAddSeller() {
    SellerDto sellerDto = mock(SellerDto.class);
    given(sellerRepo.existsByName(sellerDto.getName())).willReturn(false);
    underTest.addSeller(sellerDto);
    verify(sellerRepo).save(any());
    }

    @Test
    void willThrowIfSellerNameIsExist(){
        SellerDto sellerDto=mock(SellerDto.class);
        given(sellerRepo.existsByName(sellerDto.getName())).willReturn(true);
        assertThatThrownBy(()->underTest.addSeller(sellerDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("seller is already exist");
    }


    @Test
    void tryToUpdateSeller() {
        SellerDto sellerDto=mock(SellerDto.class);
        Seller seller=mock(Seller.class);
        given(sellerRepo.findById(anyInt())).willReturn(Optional.of(seller));
        assertThat(underTest.updateSeller(sellerDto,anyInt())).isEqualTo("seller updated to "+sellerDto.getName());
        verify(sellerRepo).save(seller);

    }

    @Test
    void willThrowIfSellerWantedToUpdateNotExist(){
        SellerDto sellerDto=mock(SellerDto.class);
        given(sellerRepo.findById(anyInt())).willReturn(Optional.empty());
        assertThatThrownBy(()->underTest.updateSeller(sellerDto,anyInt()))
                .isInstanceOf(SellerNotFoundException.class)
                .hasMessageContaining("there is no seller with this id ");
        verify(sellerRepo,never()).save(any());
    }

    @Test
    void deleteSeller() {
        SellerDto sellerDto=mock(SellerDto.class);
        Seller seller=mock(Seller.class);
        given(sellerRepo.findByName(sellerDto.getName())).willReturn(Optional.of(seller));
        assertThat(underTest.deleteSeller(sellerDto)).isEqualTo("seller "+sellerDto.getName()+" deleted ");
        verify(sellerRepo).delete(seller);
    }

    @Test
    void willThrowIfSellerWantedToDeleteNotExist(){
        SellerDto sellerDto=mock(SellerDto.class);
     Seller seller=mock(Seller.class);
     given(sellerRepo.findByName(sellerDto.getName())).willReturn(Optional.empty());
     assertThatThrownBy(()->underTest.deleteSeller(sellerDto))
             .isInstanceOf(SellerNotFoundException.class)
                     .hasMessageContaining("there is no seller with name "+sellerDto.getName());
     verify(sellerRepo,never()).delete(seller);
    }

}