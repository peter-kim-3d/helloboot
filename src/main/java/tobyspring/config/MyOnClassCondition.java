package tobyspring.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

public class MyOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attrs = metadata.getAnnotationAttributes(ConditionalMyOnClass.class.getName());
        String value = (String) attrs.get("value");
        System.out.println("MyOnClassCondition: " + value);
        if(metadata instanceof AnnotationMetadata) {
            AnnotationMetadata annotationMetadata = (AnnotationMetadata) metadata;
            String className = annotationMetadata.getClassName();
            try {
                Class<?> aClass = Class.forName(className);
                System.out.println("------------>" + aClass.getName());
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
        return ClassUtils.isPresent(value, context.getClassLoader());
    }
}
