package com.wzh.bishe.service;

import com.wzh.bishe.entity.Cases;

public interface CaseService {

    Cases addCase(Cases c);
    Cases findOneByAppointmentId(String appointmentId);
}
