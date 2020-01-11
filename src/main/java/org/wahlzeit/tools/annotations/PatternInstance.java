package org.wahlzeit.tools.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marking Patterns
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface PatternInstance {
    String patternName() default "";
    String[] participants() default {};
}
