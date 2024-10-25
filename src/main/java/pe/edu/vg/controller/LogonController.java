package pe.edu.vg.controller;

import pe.edu.vg.dto.UsuarioDto;
import pe.edu.vg.service.LogonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Logon","/Salir"})
public class LogonController extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch(path){
            case "/Logon":
                logon(request,response);
                break;
        }
    }

    private void logon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Datos
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        // Proceso
        String destino;
        try {
            LogonService service = new LogonService();
            UsuarioDto bean = service.validarUsuario(usuario,clave);
            request.getSession().setAttribute("usuario", bean);
            destino = "main.jsp";
        }catch(Exception e){
            request.setAttribute("mensaje",e.getMessage());
            destino = "index.jsp";
        }
        // Retorno
        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request,response);
    }
}
