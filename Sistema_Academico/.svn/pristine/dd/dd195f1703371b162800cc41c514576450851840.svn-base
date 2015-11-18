package net.uch.commonService;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author chano
 */
public class ServiceFinder {

    /**
     * Busca un bean en el applicationContext-hibernate
     * de Spring
     * @param beanName
     * @return
     */
    public static Object findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return appContext.getBean(beanName);
    }
}
