package by.bsuir.lr4;


import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    @EJB
    private ArrayMessageProducer messageProducer;


    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String arrayInput = request.getParameter("integerArray");
        List<Integer> integerList = parseIntegerArray(arrayInput);

        Numbers numbers = new Numbers();
        numbers.setNumbers(integerList);

        messageProducer.sendMessage(numbers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/in.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/in.jsp");
        requestDispatcher.forward(request, response);
    }


    private List<Integer> parseIntegerArray(String arrayInput) {
        String[] integerStrings = arrayInput.split(",");
        return Arrays.stream(integerStrings).map(Integer::parseInt).collect(Collectors.toList());
    }

    public void destroy() {
    }
}