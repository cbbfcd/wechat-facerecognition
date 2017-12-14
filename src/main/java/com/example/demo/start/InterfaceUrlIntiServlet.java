package com.example.demo.start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 项目初始化servlet
 * @time 16:20 2017/11/22
 * @modified by:
 * @modified time:
 */
public class InterfaceUrlIntiServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        InterfaceUrlInti.init();
    }
}
