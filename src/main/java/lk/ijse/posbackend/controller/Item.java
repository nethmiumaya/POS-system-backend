package lk.ijse.posbackend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackend.bo.ItemBaoImp;
import lk.ijse.posbackend.dto.ItemDTO;
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
           // itemDTO.setItemId(Util.idGenerate());

            writer.write(itemBoImp.saveItem(itemDTO,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("item saved successfully");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Error saving item",e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("itemId");
        try (var writer = resp.getWriter()){
            var itemBoImp = new ItemBaoImp();

            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");

          if (id != null){
              jsonb.toJson(itemBoImp.getItem(id,connection),writer);
              resp.setStatus(HttpServletResponse.SC_OK);
              logger.info("Item Retrieve successfully");
          } else {
              jsonb.toJson(itemBoImp.getAllItems(connection),writer);
              resp.setStatus(HttpServletResponse.SC_OK);
              logger.info("All Item Retrieve successfully");
          }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error("Error occured while retrieving item");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var itemBoImp = new ItemBaoImp();
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            if (itemBoImp.updateItem(itemDTO,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                logger.info("Item Updated Successfully");
            } else {
                writer.write("update fail");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                logger.info("Item Updated Failed");
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating item",e);
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
                logger.info("Item Deleted Successfully");
            } else {
                writer.write("Delete failed");
                logger.info("Item Deleted Failed");
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting item",e);
            e.printStackTrace();
        }
    }
}
