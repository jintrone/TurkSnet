package edu.mit.cci.turksnet.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: jintrone
 * Date: 7/19/11
 * Time: 2:47 PM
 */
public class ValidClassValidator implements ConstraintValidator<ValidClass, String> {
    @Override
    public void initialize(ValidClass constraintAnnotation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Class.forName(value).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

