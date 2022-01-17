import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.CompanyNews;
import models.Department;
import models.News;
import models.User;
import org.sql2o.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.route.HttpMethod.*;

public class App {

    public static void main(String[] args) {

        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/organisational_news";
        Sql2o sql2o = new Sql2o(connectionString,  null, null);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn= sql2o.open();

        //POST METHODS
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.save(department);
            res.status(201);
            return gson.toJson(department);
        });

        //Post News to a department
        post("/departments/:departmentId/news/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            Department department = departmentDao.findById(departmentId);
            if(department == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("departmentId")));
            }
            News news = gson.fromJson(req.body(), News.class);
            news.setDepartmentId(departmentId);
            newsDao.save(news);
            departmentDao.saveNewsAndDepartment(department, news);
            res.status(201);
            String success = "News successfully added to department";
            return gson.toJson(success);
        });

        //Post User to a department
        post("/departments/:departmentId/users/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            Department department = departmentDao.findById(departmentId);
            if(department == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("departmentId")));
            }
            User user = gson.fromJson(req.body(), User.class);
            user.setDepartmentId(departmentId);
            userDao.save(user);
            departmentDao.saveUsersAndDepartment(department, user);
            res.status(201);
            String success = "User successfully added to a department";
            return gson.toJson(success);
        });

        //GET METHODS
        get("/", "application/json", (req, res) -> {
            CompanyNews companyNews1 = new CompanyNews("Moringa School Graduate Hired!", "After excelling in their Python Core course, a Moringa School graduate was hired by our company!");
            CompanyNews companyNews2 = new CompanyNews("Training Bootcamp!", "Breaking News as CEO of Scrub Daddy announces that there will be a mandatory training bootcamp for all employees!");
            CompanyNews companyNews3 = new CompanyNews("Entire Sales Department", "The entire sales department fired after a record low sales for the past three months");
            CompanyNews companyNews4 = new CompanyNews("Expansion coming!", "Scrub Daddy set to expand to the continents of Africa and Asia");
            List<CompanyNews> companyNews = new ArrayList<>();
            companyNews.add(companyNews1);
            companyNews.add(companyNews2);
            companyNews.add(companyNews3);
            companyNews.add(companyNews4);
            return gson.toJson(companyNews); // This is the general company news.
        });

        //Get all created departments.
        get("/departments", "application/json", (req, res) -> {
           if(departmentDao.getAll().size() == 0) {
               String noData = "Sorry, there are no departments that have been created yet";
               return gson.toJson(noData);
           }
           return gson.toJson(departmentDao.getAll());
        });

        //Get all created users.
        get("/users", "application/json", (req, res) -> {
            if(userDao.getAll().size() == 0) {
                String noData = "Sorry, there are no users that have been created yet";
                return gson.toJson(noData);
            }
            return gson.toJson(userDao.getAll());
        });

        //Get all created news.
        get("/news", "application/json", (req, res) -> {
            if(newsDao.getAll().size() == 0) {
                String noData = "Sorry, no news has been created yet";
                return gson.toJson(noData);
            }
            return gson.toJson(newsDao.getAll());
        });

        // Department of a specific ID.
        get("/departments/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Department department = departmentDao.findById(id);
            if(department == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(department);
        });

        //All news belonging to a department.
        get("/departments/:departmentId/news", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("departmentId"));
            List<News> savedNews = departmentDao.getAllDepartmentNews(id);
            if(savedNews == null) {
                throw new ApiException(404, String.format("No news has been saved to the department with the id: \"%s\"", req.params("id")));
            }
            return gson.toJson(savedNews);
        });

        //All users belonging to a department.
        get("departments/:departmentId/users", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("departmentId"));
            List<User> savedUsers = departmentDao.getAllDepartmentUsers(id);
            if(savedUsers == null) {
                throw new ApiException(404, String.format("No users have been saved to the department with the id: \"%s\"", req.params("id")));
            }
            return gson.toJson(savedUsers);
        });

        //DELETE
        get("/deleteAllData", "application/json", (req, res) -> {
           departmentDao.clearAllUsersAndDepartments();
           departmentDao.clearAllNewsAndDepartments();
           departmentDao.clearAll();
           newsDao.clearAll();
           userDao.clearAll();
           String clearedData = "All data has been cleared";
           return gson.toJson(clearedData);
        });




        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });


        after((req, res) -> {
            res.type("application/json");
        });
    }
}
