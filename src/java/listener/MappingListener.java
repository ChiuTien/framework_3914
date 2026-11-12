package listener;

import java.lang.reflect.Method;
import java.util.Map;

import annotation.Controller;
import utils.MethodMap;
import utils.Utilitaire;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MappingListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext context = event.getServletContext();

            String packageName = context.getInitParameter("controller-package");
            if (packageName == null || packageName.isBlank()) {
                packageName = "controller";
            }

            Utilitaire util = new Utilitaire();
            Map<MethodMap, Method> mapping = util.getMappingMethod(
                    context,
                    packageName,
                    Controller.class);

            context.setAttribute("mapping", mapping);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur d'initialisation du mapping", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}