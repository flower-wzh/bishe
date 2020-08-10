package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.CaseDao;
import com.wzh.bishe.entity.Cases;
import com.wzh.bishe.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseDao caseDao;

    @Override
    public Cases addCase(Cases c) {
        c.setId(UUID.randomUUID().toString().replace("-",""));
        caseDao.insertSelective(c);
        return c;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Cases findOneByAppointmentId(String appointmentId) {
        return caseDao.selectOne(new Cases().setAppointmentId(appointmentId));
    }
}
