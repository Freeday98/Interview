package validation;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.ArrayList;
import java.util.List;

public class CustomValidator {
    private Validator validator;

    public CustomValidator() {
        validator = new Validator();
    }

    public List<String> getViolationMessageList(Object object) {
        List<ConstraintViolation> list = validator.validate(object);
        List<String> violationMessageList = new ArrayList<String>();
        for (ConstraintViolation constraintViolation : list) {
            violationMessageList.add(constraintViolation.getMessage());
        }
        return violationMessageList;
    }


}
