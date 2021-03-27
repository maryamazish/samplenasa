package com.azish.nasa.aop;

import com.azish.nasa.service.CreateModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import com.azish.nasa.service.NasaService;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    @Qualifier("NasaServiceImpl")
    NasaService nasaService;

    /**
     * This method uses Around advice which ensures that an advice can run before
     * and after the method execution, to and log the execution time of the method
     * This advice will be be applied to all the method which are annotate with the
     * annotation @LogExecutionTime
     * <p>
     * Any mehtod where execution times need to be measue and log, anotate the method with @LogExecutionTime
     */
    @Around("@annotation(com.azish.nasa.aop.LogExecutionTime)")
    public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        // Get intercepted method details
        //String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        // Measure method execution time
        //StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(methodName);
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //create log in database
        CreateModel logExecutionModel = new CreateModel();
        logExecutionModel.setMethodName(methodName);
        logExecutionModel.setTotalTimeMillis(stopWatch.getTotalTimeMillis());
        nasaService.createLog(logExecutionModel);

        return result;
    }
}