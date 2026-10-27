package org.example;

import org.example.service.PaymentService;
import org.example.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "services.xml", "repository.xml");

        UserService service = context.getBean(UserService.class);
        System.out.println(service.findAll());

//        PaymentService paymentService = context.getBean(PaymentService.class);
    }
}
