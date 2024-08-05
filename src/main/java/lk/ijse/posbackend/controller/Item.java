package lk.ijse.posbackend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackend.bo.CustomerBoImpl;
import lk.ijse.posbackend.bo.ItemBaoImp;
import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dto.ItemDTO;
import lk.ijse.posbackend.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item",loadOnStartup = 2)
public class Item extends HttpServlet {
    static Logger logger = LoggerFactory.getLogger(Item.class);
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
        if (req.getContentType()==null || !req.getContentType().contains("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var itemBoImp = new ItemBaoImp();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            itemDTO.setItemId(Util.idGenerate());

            writer.write(itemBoImp.saveItem(itemDTO,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()){
            var itemBoImp = new ItemBaoImp();
            Jsonb jsonb = JsonbBuilder.create();
            var itemId = req.getParameter("itemId");
            resp.setContentType("application/json");
            jsonb.toJson(itemBoImp.getItem(itemId,connection),writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var itemBoImp = new ItemBaoImp();
            var itemId = req.getParameter("itemId");
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            if (itemBoImp.updateItem(itemId,itemDTO,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
            else {
                writer.write("update fail");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var itemId = req.getParameter("itemId");
            var itemBoImp = new ItemBaoImp();
            if (itemBoImp.deleteItem(itemId,connection)){
                resp.setStatus((HttpServletResponse.SC_NO_CONTENT));
            }else {
                writer.write("Delete failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
