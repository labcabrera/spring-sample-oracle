package org.lab.samples.oracle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to resolve the name of an oracle business entity of a given java bean.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OracleStruct {

	/**
	 * Oracle entity name.
	 * 
	 * @return
	 */
	String value() default "";

}
