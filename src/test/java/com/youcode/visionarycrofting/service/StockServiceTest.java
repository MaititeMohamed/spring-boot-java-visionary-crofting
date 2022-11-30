package com.youcode.visionarycrofting.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StockService.class})
@ExtendWith(SpringExtension.class)
class StockServiceTest {
    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);

        Stock stock1 = new Stock();
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertSame(stock, stockService.insertStock(stock1));
        verify(stockRepository).save((Stock) any());
    }

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock2() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);
        Stock stock1 = mock(Stock.class);
        when(stock1.getPhone()).thenReturn("4105551212");
        when(stock1.getPassword()).thenReturn("iloveyou");
        when(stock1.getAddress()).thenReturn("42 Main St");
        when(stock1.getEmail()).thenReturn("jane.doe@example.org");
        when(stock1.getName()).thenReturn("Name");
        doNothing().when(stock1).setAddress((String) any());
        doNothing().when(stock1).setEmail((String) any());
        doNothing().when(stock1).setId((Long) any());
        doNothing().when(stock1).setName((String) any());
        doNothing().when(stock1).setPassword((String) any());
        doNothing().when(stock1).setPhone((String) any());
        doNothing().when(stock1).setProductList((List<Product>) any());
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertSame(stock, stockService.insertStock(stock1));
        verify(stockRepository).save((Stock) any());
        verify(stock1).getAddress();
        verify(stock1).getEmail();
        verify(stock1).getName();
        verify(stock1).getPassword();
        verify(stock1).getPhone();
        verify(stock1).setAddress((String) any());
        verify(stock1).setEmail((String) any());
        verify(stock1).setId((Long) any());
        verify(stock1).setName((String) any());
        verify(stock1).setPassword((String) any());
        verify(stock1).setPhone((String) any());
        verify(stock1).setProductList((List<Product>) any());
    }

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock3() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);
        Stock stock1 = mock(Stock.class);
        when(stock1.getPhone()).thenThrow(new IllegalStateException());
        when(stock1.getPassword()).thenReturn("iloveyou");
        when(stock1.getAddress()).thenReturn("42 Main St");
        when(stock1.getEmail()).thenReturn("jane.doe@example.org");
        when(stock1.getName()).thenReturn("Name");
        doNothing().when(stock1).setAddress((String) any());
        doNothing().when(stock1).setEmail((String) any());
        doNothing().when(stock1).setId((Long) any());
        doNothing().when(stock1).setName((String) any());
        doNothing().when(stock1).setPassword((String) any());
        doNothing().when(stock1).setPhone((String) any());
        doNothing().when(stock1).setProductList((List<Product>) any());
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> stockService.insertStock(stock1));
        verify(stock1).getAddress();
        verify(stock1).getEmail();
        verify(stock1).getName();
        verify(stock1).getPassword();
        verify(stock1).getPhone();
        verify(stock1).setAddress((String) any());
        verify(stock1).setEmail((String) any());
        verify(stock1).setId((Long) any());
        verify(stock1).setName((String) any());
        verify(stock1).setPassword((String) any());
        verify(stock1).setPhone((String) any());
        verify(stock1).setProductList((List<Product>) any());
    }

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock4() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);
        Stock stock1 = mock(Stock.class);
        when(stock1.getPhone()).thenReturn("");
        when(stock1.getPassword()).thenReturn("iloveyou");
        when(stock1.getAddress()).thenReturn("42 Main St");
        when(stock1.getEmail()).thenReturn("jane.doe@example.org");
        when(stock1.getName()).thenReturn("Name");
        doNothing().when(stock1).setAddress((String) any());
        doNothing().when(stock1).setEmail((String) any());
        doNothing().when(stock1).setId((Long) any());
        doNothing().when(stock1).setName((String) any());
        doNothing().when(stock1).setPassword((String) any());
        doNothing().when(stock1).setPhone((String) any());
        doNothing().when(stock1).setProductList((List<Product>) any());
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> stockService.insertStock(stock1));
        verify(stock1).getAddress();
        verify(stock1).getEmail();
        verify(stock1).getName();
        verify(stock1).getPassword();
        verify(stock1).getPhone();
        verify(stock1).setAddress((String) any());
        verify(stock1).setEmail((String) any());
        verify(stock1).setId((Long) any());
        verify(stock1).setName((String) any());
        verify(stock1).setPassword((String) any());
        verify(stock1).setPhone((String) any());
        verify(stock1).setProductList((List<Product>) any());
    }

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock5() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);
        Stock stock1 = mock(Stock.class);
        when(stock1.getPhone()).thenReturn("4105551212");
        when(stock1.getPassword()).thenReturn("");
        when(stock1.getAddress()).thenReturn("42 Main St");
        when(stock1.getEmail()).thenReturn("jane.doe@example.org");
        when(stock1.getName()).thenReturn("Name");
        doNothing().when(stock1).setAddress((String) any());
        doNothing().when(stock1).setEmail((String) any());
        doNothing().when(stock1).setId((Long) any());
        doNothing().when(stock1).setName((String) any());
        doNothing().when(stock1).setPassword((String) any());
        doNothing().when(stock1).setPhone((String) any());
        doNothing().when(stock1).setProductList((List<Product>) any());
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> stockService.insertStock(stock1));
        verify(stock1).getAddress();
        verify(stock1).getEmail();
        verify(stock1).getName();
        verify(stock1).getPassword();
        verify(stock1).setAddress((String) any());
        verify(stock1).setEmail((String) any());
        verify(stock1).setId((Long) any());
        verify(stock1).setName((String) any());
        verify(stock1).setPassword((String) any());
        verify(stock1).setPhone((String) any());
        verify(stock1).setProductList((List<Product>) any());
    }

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock6() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);
        Stock stock1 = mock(Stock.class);
        when(stock1.getPhone()).thenReturn("4105551212");
        when(stock1.getPassword()).thenReturn("iloveyou");
        when(stock1.getAddress()).thenReturn("");
        when(stock1.getEmail()).thenReturn("jane.doe@example.org");
        when(stock1.getName()).thenReturn("Name");
        doNothing().when(stock1).setAddress((String) any());
        doNothing().when(stock1).setEmail((String) any());
        doNothing().when(stock1).setId((Long) any());
        doNothing().when(stock1).setName((String) any());
        doNothing().when(stock1).setPassword((String) any());
        doNothing().when(stock1).setPhone((String) any());
        doNothing().when(stock1).setProductList((List<Product>) any());
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> stockService.insertStock(stock1));
        verify(stock1).getAddress();
        verify(stock1).getEmail();
        verify(stock1).getName();
        verify(stock1).setAddress((String) any());
        verify(stock1).setEmail((String) any());
        verify(stock1).setId((Long) any());
        verify(stock1).setName((String) any());
        verify(stock1).setPassword((String) any());
        verify(stock1).setPhone((String) any());
        verify(stock1).setProductList((List<Product>) any());
    }

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock7() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);
        Stock stock1 = mock(Stock.class);
        when(stock1.getPhone()).thenReturn("4105551212");
        when(stock1.getPassword()).thenReturn("iloveyou");
        when(stock1.getAddress()).thenReturn("42 Main St");
        when(stock1.getEmail()).thenReturn("");
        when(stock1.getName()).thenReturn("Name");
        doNothing().when(stock1).setAddress((String) any());
        doNothing().when(stock1).setEmail((String) any());
        doNothing().when(stock1).setId((Long) any());
        doNothing().when(stock1).setName((String) any());
        doNothing().when(stock1).setPassword((String) any());
        doNothing().when(stock1).setPhone((String) any());
        doNothing().when(stock1).setProductList((List<Product>) any());
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> stockService.insertStock(stock1));
        verify(stock1).getEmail();
        verify(stock1).getName();
        verify(stock1).setAddress((String) any());
        verify(stock1).setEmail((String) any());
        verify(stock1).setId((Long) any());
        verify(stock1).setName((String) any());
        verify(stock1).setPassword((String) any());
        verify(stock1).setPhone((String) any());
        verify(stock1).setProductList((List<Product>) any());
    }

    /**
     * Method under test: {@link StockService#insertStock(Stock)}
     */
    @Test
    void testInsertStock8() {
        Stock stock = new Stock();
        stock.setAddress("42 Main St");
        stock.setEmail("jane.doe@example.org");
        stock.setId(123L);
        stock.setName("Name");
        stock.setPassword("iloveyou");
        stock.setPhone("4105551212");
        stock.setProductList(new ArrayList<>());
        when(stockRepository.save((Stock) any())).thenReturn(stock);
        Stock stock1 = mock(Stock.class);
        when(stock1.getPhone()).thenReturn("4105551212");
        when(stock1.getPassword()).thenReturn("iloveyou");
        when(stock1.getAddress()).thenReturn("42 Main St");
        when(stock1.getEmail()).thenReturn("jane.doe@example.org");
        when(stock1.getName()).thenReturn("");
        doNothing().when(stock1).setAddress((String) any());
        doNothing().when(stock1).setEmail((String) any());
        doNothing().when(stock1).setId((Long) any());
        doNothing().when(stock1).setName((String) any());
        doNothing().when(stock1).setPassword((String) any());
        doNothing().when(stock1).setPhone((String) any());
        doNothing().when(stock1).setProductList((List<Product>) any());
        stock1.setAddress("42 Main St");
        stock1.setEmail("jane.doe@example.org");
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPassword("iloveyou");
        stock1.setPhone("4105551212");
        stock1.setProductList(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> stockService.insertStock(stock1));
        verify(stock1).getName();
        verify(stock1).setAddress((String) any());
        verify(stock1).setEmail((String) any());
        verify(stock1).setId((Long) any());
        verify(stock1).setName((String) any());
        verify(stock1).setPassword((String) any());
        verify(stock1).setPhone((String) any());
        verify(stock1).setProductList((List<Product>) any());
    }
}

