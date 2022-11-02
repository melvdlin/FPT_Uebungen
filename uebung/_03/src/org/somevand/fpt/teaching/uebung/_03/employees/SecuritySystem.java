package org.somevand.fpt.teaching.uebung._03.employees;

import java.util.*;

public class SecuritySystem {
    private final Collection<EmployeeID> employees;
    private final Map<String, SecurityAccessLevel> accessMap;

    private SecuritySystem() {
        employees = new HashSet<>();
        accessMap = new HashMap<>();
    }

    public SecuritySystem(EmployeeID... employees) {
        this();
        this.employees.addAll(Arrays.asList(employees));
    }

    public void addEmployee(EmployeeID employee) {
        employees.add(employee);
    }

    public void removeEmployee(EmployeeID employee) {
        employees.remove(employee);
    }

    public void addArea(String areaName, SecurityAccessLevel accessLevel) {
        accessMap.put(areaName, accessLevel);
    }

    public void removeArea(String areaName) {
        accessMap.remove(areaName);
    }

    public boolean isEmployed(EmployeeID employee) {
        return employees.contains(employee);
    }

    public EmployeeID getEmployeeByUID(int UID) {
        for (var employee : employees) {
            if (employee.getUID() == UID) {
                return employee;
            }
        }

        return null;
    }

    public boolean hasAccess(EmployeeID employee, String area) {
        return isEmployed(employee) &&
                accessMap.containsKey(area) &&
                accessMap.get(area).ordinal() <= employee.getAccessLevel().ordinal();
    }

}
