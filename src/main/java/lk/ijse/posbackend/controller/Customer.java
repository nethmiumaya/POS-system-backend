package lk.ijse.posbackend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackend.bo.CustomerBoImpl;
import lk.ijse.posbackend.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer",loadOnStartup = 2)
public class Customer extends HttpServlet {
    static Logger logger = LoggerFactory.getLogger(Customer.class);
    Connection connection;

    @Override
    public void init() throws ServletException {
        logger.info("Init method invoked");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/cusReg");
            this.connection = pool.getConnection();
            logger.debug("Connection initialized",this.connection);
        }catch (SQLException | NamingException e){
            logger.error("DB connection initialization failed" );
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var customerBoImp = new CustomerBoImpl();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            String output = customerBoImp.saveCustomer(customerDTO,connection);
            writer.write(output);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("customer saved successfully");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Error saving customer",e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var custId = req.getParameter("custId");
        try (var writer = resp.getWriter()){
            var customerBoImp = new CustomerBoImpl();
            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");
            if(custId != null){
                jsonb.toJson(customerBoImp.getCustomer(custId,connection),writer);
                resp.setStatus(HttpServletResponse.SC_OK);
                logger.info("Customer Retrieved Successfully");
            }else {
               jsonb.toJson(customerBoImp.getAllCustomers(connection),writer);
               resp.setStatus(HttpServletResponse.SC_OK);
               logger.info("All Customers Retrieved Successfully");
            }
        } catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Error occurred while retrieving customer",e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            var customerBoImp = new CustomerBoImpl();
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            if (customerBoImp.updateCustomer(customerDTO, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                logger.info("Customer Updated Successfully");
            } else {
                writer.write("Update failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                logger.info("Customer Updated Failed");
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating customer",e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var custId = req.getParameter("custId");
            var customerBoImp = new CustomerBoImpl();
            if (customerBoImp.deleteCustomer(custId,connection)){
                resp.setStatus((HttpServletResponse.SC_NO_CONTENT));
                logger.info("Customer Deleted Successfully");
            } else {
                writer.write("Delete failed");
                logger.info("Customer Deleted Failed");
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting customer",e);
            e.printStackTrace();
        }
    }
}
