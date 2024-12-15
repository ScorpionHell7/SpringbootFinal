package com.eazybytes.eazyschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EazySchoolInfoContributor  implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder){
        Map<String, String> eazyMap = new HashMap<String, String>();
        eazyMap.put("AppName" ,"EazySchool");
        eazyMap.put("App description" ,"EazySchool web application for students and admin");
        eazyMap.put("App Version" ,"1.0.0");
        eazyMap.put("Contact Email" ,"info@EazySchool.com");
        eazyMap.put("Contact mobile" ,"8291641452");
        builder.withDetail("eazyschool-info",eazyMap);
    }
}
