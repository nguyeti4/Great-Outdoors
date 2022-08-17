package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OutdoorsAspect {
			
			//@Before(value="execution(* com.example.service.EmployeeServiceImpl.*(..))")
			public void beforeAdvice(JoinPoint jp) {
				System.out.println(jp.getSignature());
			}
			
			//@After(value="execution(* com.example.service.EmployeeServiceImpl.*(..))")
			public void afterAdvice(JoinPoint jp) {
				System.out.println(jp.getSignature());
			}
		
			@Around(value="execution(* com.example.security.services.EmailServiceImpl.*(..))")
			public void around(ProceedingJoinPoint jp) {
				long intime = System.currentTimeMillis();
				Object[] obj = jp.getArgs();
				//obj[0] is the arguement passed into .addEmployee()
				/*Employee e = (Employee) obj[0];
				e.setName(e.getName()+" Ben");*/
				for(Object o:obj) {
					System.out.println(o);
				}
				try {
					jp.proceed();
				} catch (Throwable ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				long outtime = System.currentTimeMillis();
				long duration = outtime - intime;
				System.out.println("It took "+ duration + " milliseconds to send a simple email");
			}
}
