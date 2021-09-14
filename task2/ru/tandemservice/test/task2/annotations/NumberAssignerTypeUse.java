package ru.tandemservice.test.task2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ru.tandemservice.test.task2.NumberAssignerType;

/**
 * 
 * @author Melnikov M.M. <a href="mailto:m@lmsu.ru">m@lmsu.ru</a>
 * <p><strong>NumberAssignerType.BY_NODES_TYPE_ASSIGNER</strong> - значение по умолчанию</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NumberAssignerTypeUse
{
	NumberAssignerType value() default NumberAssignerType.SORTING;
}
