package com.z4.zhazha.forum.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class RestApplication extends ResourceConfig{
    public RestApplication () {  
        
        //自己写的服务  
        register(ZUserRestService.class);
        register(ZGroupRestService.class);
        register(ZHistoryRestService.class);
        register(ZRelationRestService.class);
        register(ZUserGroupRestService.class);
 
        //用 Jackson JSON 的提供者来解释 JSON  
        register(JacksonFeature.class);  
        
        //Spring filter 提供了 JAX-RS 和 Spring 请求属性之间的桥梁  
        register(RequestContextFilter.class);  
  }  

}
