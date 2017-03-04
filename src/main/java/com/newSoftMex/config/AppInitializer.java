package com.newSoftMex.config;

import com.newSoftMex.filter.MyFilter;
import com.newSoftMex.filter.OptionsHeadersFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by alan.flores on 1/2/17.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        /*FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("my-filter", new MyFilter());
        //encodingFilter.setInitParameter("blah", "blah");
        encodingFilter.addMappingForUrlPatterns(null, false, "/filter/*");*/

        super.onStartup(servletContext);
    }

    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[] { AppConfig.class};
    }

    @Override
    protected Class[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new OptionsHeadersFilter()};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
