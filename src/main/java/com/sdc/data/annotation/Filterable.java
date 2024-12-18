package com.sdc.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Esta anotacion permite filtrar elementos por sus atributos,
 * el nombre del campo debe ser igual al pasado por parámetro enviado en la URL
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Filterable {

    String[] fields() default {};
    String[] types() default {};
}
