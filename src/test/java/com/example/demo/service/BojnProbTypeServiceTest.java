package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class BojnProbTypeServiceTest {

    @Test
    void save() {
    }

    @Test
    public void BigDecimalRoundDown(){
        BigDecimal bd = new BigDecimal(1000026);
        BigDecimal result = bd.divide(BigDecimal.TEN,0,BigDecimal.ROUND_DOWN);

        assertEquals(bd.divide(BigDecimal.TEN,0, RoundingMode.DOWN), result);
    }
}