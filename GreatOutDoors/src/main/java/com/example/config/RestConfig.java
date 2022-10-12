package com.example.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


import com.example.entities.Address;
import com.example.entities.Cart;
import com.example.entities.Order;
import com.example.entities.Product;
import com.example.entities.Query;
import com.example.entities.User;


@Configuration
public class RestConfig implements RepositoryRestConfigurer{
private EntityManager entityManager;
	
	@Autowired
	public RestConfig(EntityManager theEntityManager) {
	entityManager = theEntityManager;
	}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
        HttpMethod[] theUnsupportedActions2 = {HttpMethod.GET, HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        
        // disable HTTP methods for Product: PUT, POST, DELETE and PATCH
        
     
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        config.getExposureConfiguration()
		        .forDomainType(User.class)
		        .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		        .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions2));

        
        config.getExposureConfiguration()
                .forDomainType(Cart.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        config.getExposureConfiguration()
                .forDomainType(Order.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions2));

        config.getExposureConfiguration()
	        .forDomainType(Address.class)
	        .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
	        .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions2));

        config.getExposureConfiguration()
	        .forDomainType(Query.class)
	        .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
	        .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions2));
	        
        //config.exposeIdsFor(User.class,Product.class,Cart.class,Order.class,Address.class,Query.class);
        config.exposeIdsFor(Product.class,Cart.class);

    }
}
