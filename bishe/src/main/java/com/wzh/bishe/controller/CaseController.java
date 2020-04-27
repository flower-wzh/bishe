package com.wzh.bishe.controller;

import com.wzh.bishe.entity.Case;
import com.wzh.bishe.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @RequestMapping("addCase")
    private Case addCase(Case c){
        return caseService.addCase(c);
    }

    @RequestMapping("findOneCase")
    private Case findByAppointmentId(String appointmentId){
        return caseService.findOneByAppointmentId(appointmentId);

    }
}
