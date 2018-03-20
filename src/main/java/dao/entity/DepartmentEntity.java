package dao.entity;

import dao.logic.DepartmentDAO;
import net.sf.oval.constraint.*;

public class DepartmentEntity {
    private int idDepartment;
    private int numOfEmployee;
    @NotNull
    @NotEmpty(message = "Field name can't be empty")
    @Length(max = 32)
    @CheckWith(value = DepartmentValidator.class, message = "Department with such name is already exists")
    private String nameDepartment;

    public DepartmentEntity() {
    }

    public DepartmentEntity(int numOfEmployee, String nameDepartment) {
        this.numOfEmployee = numOfEmployee;
        this.nameDepartment = nameDepartment;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getNumOfEmployee() {
        return numOfEmployee;
    }

    public void setNumOfEmployee(int numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public boolean validateNameValue() {
        return true;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "idDepartment=" + idDepartment +
                ", numOfEmployee=" + numOfEmployee +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }

    private static class DepartmentValidator implements CheckWithCheck.SimpleCheck {
        DepartmentDAO worker = new DepartmentDAO();
        public boolean isSatisfied(Object o, Object o1) {
            DepartmentEntity departmentEntity = (DepartmentEntity) o;
            DepartmentEntity checkEntity = worker.getEntityByName(departmentEntity.getNameDepartment());
            if(checkEntity == null) return true;
            else{
                if(checkEntity.getIdDepartment() == departmentEntity.getIdDepartment()) return true;
                else return false;
            }

        }

    }
}
