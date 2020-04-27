package com.wzh.bishe.service;

import com.wzh.bishe.entity.Case;

public interface CaseService {

    Case addCase(Case c);
    Case findOneByAppointmentId(String appointmentId);
}
