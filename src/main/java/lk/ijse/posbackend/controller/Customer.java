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
import lk.ijse.posbackend.util.Util;
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
            logger.error("DB connection not init" );
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
           // customerDTO.setCustId(Util.idGenerate());

            writer.write(customerBoImp.saveCustomer(customerDTO,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
                logger.info("Customer Found Successfully");
            }else {
               jsonb.toJson(customerBoImp.getAllCustomers(connection),writer);
               resp.setStatus(HttpServletResponse.SC_OK);
               logger.info("Customer Found Successfully");
            }

        } catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Error occurred while getting customer",e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            var customerBoImp = new CustomerBoImpl();
            var custId = req.getParameter("custId");
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            if (customerBoImp.updateCustomer(custId, customerDTO, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

            } else {
                writer.write("Update failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
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
            }else {
                writer.write("Delete failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
