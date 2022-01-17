import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import models.CompanyNews;
import models.Department;
import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;

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

        get("/", "application/json", (req, res) -> {
            CompanyNews companyNews1 = new CompanyNews("Moringa School Graduate Hired!", "After excelling in their Python Core course, a Moringa School graduate was hired by a leading software development company!");
            CompanyNews companyNews2 = new CompanyNews("Training Bootcamp!", "Breaking News as CEO of Scrub Daddy announces that there will be a mandatory training bootcamp for all employees!");
            CompanyNews companyNews3 = new CompanyNews("Entire Sales Department", "The entire sales department fired after a record low sales for the past three months");
            CompanyNews companyNews4 = new CompanyNews("Expansion coming!", "Scrub Daddy set to expand to the continents of Africa and Asia");
            List<CompanyNews> companyNews = new ArrayList<>();
            companyNews.add(companyNews1);
            companyNews.add(companyNews2);
            companyNews.add(companyNews3);
            companyNews.add(companyNews4);
            return gson.toJson(companyNews);
        });

        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.save(department);
            res.status(201);
            return gson.toJson()
        });


        after((req, res) -> {
            res.type("application/json");
        });
    }
}
