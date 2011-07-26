package edu.mit.cci.turksnet.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


/**
 * User: jintrone
 * Date: 7/19/11
 * Time: 2:45 PM
 */





@Documented
@Constraint(validatedBy = ValidClassValidator.class)
@Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidClass {
 String message() default "{edu_mit_cci_turksnet_constraints_ValidClass_message}";

 Class<?>[] groups() default {};

 Class<? extends Payload>[] payload() default {};
}
