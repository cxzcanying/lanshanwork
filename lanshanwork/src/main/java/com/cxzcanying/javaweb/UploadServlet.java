package com.cxzcanying.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Paths;

import static java.lang.System.out;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filepart = req.getPart("file");
        String filename = Paths.get(filepart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filepart.getInputStream();
        String uploadPath = "C:\\Users\\21311\\Desktop\\work\\javaStudy\\servlet\\lanshanwork\\web\\upload";
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        File savedfile = new File(uploadDir, filename);
        try(OutputStream out=new FileOutputStream(savedfile)){
            byte[] buffer=new byte[4096];
            int bytesRead=-1;
            while ((bytesRead=fileContent.read(buffer))!=-1){
                out.write(buffer,0,bytesRead);
            }
        }

        //显示消息
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Upload Successful!</h1>");
        out.println("</body></html>");

    }
}
