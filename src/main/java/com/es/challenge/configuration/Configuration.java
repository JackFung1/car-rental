package com.es.challenge.configuration;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;

/**
 * @author fjt
 * @date 2023-02-16
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBean() {
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(Arrays.asList("dozer-mapping.xml"));
        return dozerBean;
    }
}
