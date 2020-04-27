package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.CaseDao;
import com.wzh.bishe.entity.Case;
import com.wzh.bishe.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseDao caseDao;

    @Override
    public Case addCase(Case c) {
        caseDao.insertSelective(c);
        return c;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Case findOneByAppointmentId(String appointmentId) {
        return caseDao.selectOne(new Case().setAppointmentId(appointmentId));
    }
}
