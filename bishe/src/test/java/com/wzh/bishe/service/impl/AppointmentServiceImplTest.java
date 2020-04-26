package com.wzh.bishe.service.impl;


import com.wzh.bishe.entity.Appointment;
import com.wzh.bishe.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AppointmentServiceImplTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void insert(){
        Appointment appointment = new Appointment("02", "9", "1", new Date(), "oAgzJ5ak7LLr191RiYrbD_5o5ATU", "01", "b","15637145272");
        appointmentService.insert(appointment);
    }
    @Test
    public void findByClinicIdAndDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(new Date());
        Date date =  sdf.parse(s);
        List<Appointment> byClinicIdAndDate = appointmentService.findByClinicIdAndDate("01", date);
        byClinicIdAndDate.forEach(appointment -> System.out.println(appointment));
    }

    @Test
    void findByUserId(){
        List<Appointment> appointmentList = appointmentService.findByUserId("oAgzJ5ak7LLr191RiYrbD_5o5ATU", "no");
        appointmentList.forEach(appointment -> System.out.println(appointment));
    }
}
