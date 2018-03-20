package dao.entity;

import dao.logic.EmployeeDAO;
import net.sf.oval.constraint.*;

public class EmployeeEntity {

    private Integer idEmployee;
    @NotNull
    @NotEmpty(message = "field 'Имя' can't be empty")
    @MaxLength(value = 16, message = "field 'Имя' is too long")
    private String firstNameEmployee;
    @NotNull
    @NotEmpty(message = "field 'Фамилия' can't be empty")
    @MaxLength(value = 16, message = "field 'Фамилия' is too long")
    private String secondNameEmployee;
    @NotNull
    @NotEmpty(message = "field 'Почта' can't be empty")
    @CheckWith(value = EmployeeValidator.class, message = "Employee with such mail is already exists", profiles = {"Creation profile"})
    @MaxLength(value = 32)
    private String contactMailEmployee;
    @NotEmpty
    @Min(value = 0, message = "salary can't be sub zero value")
    private int salaryEmployee;
    @NotEmpty
    private java.sql.Date birthDateEmployee;
    private int departmentEmployee;

    public EmployeeEntity(String firstNameEmployee, String secondNameEmployee, String contactMailEmployee, int salaryEmployee, java.sql.Date birthDateEmployee, int departmentEmployee) {
        this.firstNameEmployee = firstNameEmployee;
        this.secondNameEmployee = secondNameEmployee;
        this.contactMailEmployee = contactMailEmployee;
        this.salaryEmployee = salaryEmployee;
        this.birthDateEmployee = birthDateEmployee;
        this.departmentEmployee = departmentEmployee;
    }

    public EmployeeEntity() {
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFirstNameEmployee() {
        return firstNameEmployee;
    }

    public void setFirstNameEmployee(String firstNameEmployee) {
        this.firstNameEmployee = firstNameEmployee;
    }

    public String getSecondNameEmployee() {
        return secondNameEmployee;
    }

    public void setSecondNameEmployee(String secondNameEmployee) {
        this.secondNameEmployee = secondNameEmployee;
    }

    public String getContactMailEmployee() {
        return contactMailEmployee;
    }

    public void setContactMailEmployee(String contactMailEmployee) {
        this.contactMailEmployee = contactMailEmployee;
    }

    public int getSalaryEmployee() {
        return salaryEmployee;
    }

    public void setSalaryEmployee(int salaryEmployee) {
        this.salaryEmployee = salaryEmployee;
    }

    public java.sql.Date getBirthDateEmployee() {
        return birthDateEmployee;
    }

    public void setBirthDateEmployee(java.sql.Date birthDateEmployee) {
        this.birthDateEmployee = birthDateEmployee;
    }

    public int getDepartmentEmployee() {
        return departmentEmployee;
    }

    public void setDepartmentEmployee(int departmentEmployee) {
        this.departmentEmployee = departmentEmployee;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "idEmployee=" + idEmployee +
                ", firstNameEmployee='" + firstNameEmployee + '\'' +
                ", secondNameEmployee='" + secondNameEmployee + '\'' +
                ", contactMailEmployee='" + contactMailEmployee + '\'' +
                ", salaryEmployee=" + salaryEmployee +
                ", birthDateEmployee=" + birthDateEmployee +
                ", departmentEmployee=" + departmentEmployee +
                '}';
    }

    private static class EmployeeValidator implements CheckWithCheck.SimpleCheck {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        public boolean isSatisfied(Object o, Object o1) {
            EmployeeEntity employeeEntity = (EmployeeEntity) o;
            EmployeeEntity checkEntity = employeeDAO.getEntityByName(employeeEntity.getContactMailEmployee());
            if (checkEntity == null) return true;
            else {
                if (checkEntity.getIdEmployee() == employeeEntity.getIdEmployee()) return true;
                else return false;
            }
        }
    }


}
