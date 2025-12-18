package ma.enset.glsid.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Before("@annotation(ma.enset.glsid.aspect.Log)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Method Execution: " + joinPoint.getSignature().getName());
    }
}
