package myservlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationForm extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html";

  /** Process the HTTP Post request
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse
                    response) throws ServletException, IOException {
      response.setContentType(CONTENT_TYPE);
      PrintWriter rpw = response.getWriter();

      //check first and last name fields are properly filled
      if (request.getParameter("firstName").isEmpty()
          || request.getParameter("lastName").isEmpty()) {
          rpw.println("Error: Name not provided.");
          return;
      }

      //obtain input parameters from user
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String gender = request.getParameter("gender");
      String phone = request.getParameter("phone");
      String email = request.getParameter("email");

      /* store user data in local file in the work subdirectory of Apache
      Tomcat's home installation directory */
      String pathToFile = System.getProperty("catalina.base") + "\\work";

      try(PrintWriter fpw = new PrintWriter(new BufferedWriter
         (new FileWriter(pathToFile + "\\user_registration.txt", true)))) {

      fpw.println(firstName + ':' + lastName + ':' + gender + ':' + phone
                  + ':' + email);

      } catch (IOException ioe) {
            System.err.println(ioe);
      }
      System.out.println("pwd: " + System.getProperty("user.dir"));
      rpw.println("Your registration was successful.");
  }
}
