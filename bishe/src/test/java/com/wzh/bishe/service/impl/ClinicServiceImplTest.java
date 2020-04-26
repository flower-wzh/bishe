package com.wzh.bishe.service.impl;

import com.wzh.bishe.BisheApplication;
import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.service.ClinicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = BisheApplication.class)
class ClinicServiceImplTest {

    @Autowired
    private ClinicService clinicService;

    @Test
    void findByLatLng() {
        List<Clinic> byLatLng = clinicService.findByLatLng(34.576556, 113.862104, 20000.0);
        for (Clinic clinic : byLatLng) {
            System.out.println(clinic);
        }
    }

    @Test
    void queryAllByLimit(){
        List<Clinic> clinics = clinicService.queryAllByLimit(34.576556, 113.862104, 0, 1);
        for (Clinic clinic : clinics) {
            System.out.println(clinic);
        }
    }
}