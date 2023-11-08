package com.example.sessioncheckk;

// Gerekli kütüphaneleri içe aktar
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

// Servlet sınıfını tanımla ve bir URL eşlemesi ver
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // doPost metodu üzerine yaz
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Kullanıcı adı ve şifreyi istekten al
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kullanıcı adı ve şifreyi doğrula (burada basitçe sabit değerlerle karşılaştırıldı, ancak gerçekte veritabanı veya başka bir kaynak kullanılabilir)
        if (username.equals("admin") && password.equals("1234")) {
            // Doğrulama başarılı ise, oturum nesnesi oluştur ve kullanıcı adını oturum özelliği olarak ata
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // Kullanıcıyı başka bir sayfaya yönlendir (burada usertaskpage.jsp olarak varsayıldı, ancak istediğiniz sayfayı verebilirsiniz)
            response.sendRedirect("usertaskpage.jsp");
        } else {
            // Doğrulama başarısız ise, kullanıcıyı hata mesajı ile birlikte giriş sayfasına geri yönlendir
            response.sendRedirect("login.jsp?error=Invalid username or password");
        }
    }
}

