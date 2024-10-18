package servlet;

import dao.EmployeeChangeDAO;
import dao.EmployeeDAO;
import entities.Department;
import entities.Employee;
import entities.EmployeeChange;
import entities.Job;
import enums.UsersRole;
import jakarta.transaction.SystemException;
import service.DepartmentService;
import service.EmployeeChangeService;
import service.EmployeeService;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet()
public class ManageEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;
    private EmployeeChangeService employeeChangeService;
    private DepartmentService departmentService;
    private JobService jobService;

    @Override
    public void init() throws ServletException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeService = new EmployeeService(employeeDAO);
        EmployeeChangeDAO employeeChangeDAO  = new EmployeeChangeDAO();
        employeeChangeService = new EmployeeChangeService(employeeChangeDAO);
        departmentService = new DepartmentService();
        jobService = new JobService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            showAllEmployees(req, resp);
            return;
        }
        switch(action){
            case "add":
                showAddEmployee(req, resp);
                break;
            case "edit":
                ShowEditEmployee(req, resp);
                break;
            default:
                showAllEmployees(req, resp);
        }
    }

    private void showAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeService.getAll();

        req.setAttribute("employees", employeeList);

        req.getRequestDispatcher("views/index.jsp").forward(req, resp);
    }

    private void showAddEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Employee> employeeList = employeeService.getAll();
        List<Department> departmentList = departmentService.getAll();
        List<Job> jobList = jobService.getAll();
        req.setAttribute("employees", employeeList);
        req.setAttribute("departments", departmentList);
        req.setAttribute("jobs", jobList);
        req.getRequestDispatcher("views/addEmployee.jsp").forward(req, resp);
    }
    private void ShowEditEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Department> departmentList = departmentService.getAll();
        List<Job> jobList = jobService.getAll();
        req.setAttribute("departments", departmentList);
        req.setAttribute("jobs", jobList);
        String idParm = req.getParameter("id");
        if (idParm != null) {
            long id = Integer.parseInt(idParm);
            Optional<Employee> employee = employeeService.get(id);
            if (employee.isPresent()) {
                req.setAttribute("employee", employee.get());
                req.getRequestDispatcher("views/editEmployee.jsp").forward(req, resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not found");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");

        switch (method != null ? method.toUpperCase() : "") {
            case "PUT":
                doPut(req, resp);
                break;
            case "DELETE":
                doDelete(req, resp);
                break;
            case "POST":
                createEmployee(req, resp);
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                resp.getWriter().write("Error: Unsupported or invalid method.");
        }
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            String adresse = req.getParameter("adresse");
            LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));
            Integer ussn = Integer.valueOf(req.getParameter("ussn"));
            LocalDate hiringDate = LocalDate.parse(req.getParameter("hiringDate"));
            Double salary = Double.valueOf(req.getParameter("salary"));
            Integer holidayCredit = Integer.valueOf(req.getParameter("holidayCredit"));
            Integer kids = Integer.valueOf(req.getParameter("kids"));

            Optional<Department> department = departmentService.get(Integer.parseInt(req.getParameter("department")));
            Optional<Job> job = jobService.get(Integer.parseInt(req.getParameter("job")));

            Employee employee = new
                    Employee(name, email,password, phone, adresse,
                    UsersRole.EMPLOYEE, birthDate, ussn, hiringDate, salary,
                    holidayCredit, kids, department.orElse(null),
                    job.orElse(null));

            employeeService.save(employee);
            resp.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create employee");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Employee> employeeOptional = employeeService.get(id);

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                Employee oldEmployeeData = employee;
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String phone = req.getParameter("phone");
                String adresse = req.getParameter("adresse");
                LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));
                Integer ussn = Integer.valueOf(req.getParameter("ussn"));
                LocalDate hiringDate = LocalDate.parse(req.getParameter("hiringDate"));
                Double salary = Double.valueOf(req.getParameter("salary"));
                Integer holidayCredit = Integer.valueOf(req.getParameter("holidayCredit"));
                Integer kids = Integer.valueOf(req.getParameter("kids"));

                Optional<Department> department = departmentService.get(Integer.parseInt(req.getParameter("department")));
                Optional<Job> job = jobService.get(Integer.parseInt(req.getParameter("job")));

                employee.setName(name);
                employee.setEmail(email);
                employee.setPassword(password);
                employee.setPhone(phone);
                employee.setAddress(adresse);
                employee.setBirthDate(birthDate);
                employee.setUssn(ussn);
                employee.setHiringDate(hiringDate);
                employee.setSalary(salary);
                employee.setHolidayCredit(holidayCredit);
                employee.setKids(kids);
                employee.setDepartment(department.orElse(null));
                employee.setJob(job.orElse(null));

                Employee newEmployeeData = employee;

                employeeChangeService.getEmployeeChangedFields(oldEmployeeData, newEmployeeData).forEach(
                        change -> {
                            try {
                                employeeChangeService.save(change);
                            } catch (SystemException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
                employeeService.update(employee);
                resp.sendRedirect("/");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update employee");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Employee> employeeOptional = employeeService.get(id);

            if (employeeOptional.isPresent()) {
                employeeService.delete(employeeOptional.get());
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                resp.sendRedirect("/");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete employee");
        }
    }
}
