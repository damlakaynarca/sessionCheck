package com.example.sessioncheckk;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

// Servlet sınıfını tanımla ve bir URL eşlemesi ver
@WebServlet("/session-check")
public class SessionCheck extends HttpServlet {

    // doGet metodu üzerine yaz
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. İsteği kullanarak oturum nesnesini al
        HttpSession session = request.getSession();

        // 2. "user" adlı oturum özelliğini al ve bir String değişkeninde sakla
        String user = (String) session.getAttribute("user");

        // 3. user değişkeninin null veya boş olup olmadığını kontrol et
        if (user == null || user.isEmpty()) {
            // 4. Eğer evetse, kullanıcıyı giriş sayfasına yönlendir
            response.sendRedirect("login.jsp");
        } else {
            // 5. Eğer hayırsa, istekten kurabiye dizisini al
            Cookie[] cookies = request.getCookies();

            // 6. Oturum kurabiyesinin var olup olmadığını saklamak için bir boolean değişkeni tanımla
            boolean sessionCookiePresent = false;

            // 7. Kurabiye dizisinde döngü yap ve herhangi bir kurabiyenin adının "JSESSIONID" olup olmadığını kontrol et
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    // 8. Eğer evetse, sessionCookiePresent değişkenini true olarak ayarla ve döngüden çık
                    sessionCookiePresent = true;
                    break;
                }
            }

            // 9. Eğer sessionCookiePresent değişkeni false ise, oturumu sonlandır ve kullanıcıyı giriş sayfasına yönlendir
            if (!sessionCookiePresent) {
                session.invalidate();
                response.sendRedirect("login.jsp");
            }
        }
    }
}
// Bir servlet sınıfı tanımlandı ve bir URL eşlemesi verildi. Bu sayede, bu URL’ye gelen istekler bu servlet tarafından işlenebilir.
//doGet metodu üzerine yazıldı. Bu metod, HTTP GET isteklerini karşılamak için kullanılır.
//İsteği kullanarak oturum nesnesi alındı. Oturum nesnesi, sunucu tarafında kullanıcı bilgilerini saklamak için kullanılır.
//“user” adlı oturum özelliği alındı ve bir String değişkeninde saklandı. Bu özellik, kullanıcının kimliğini veya adını tutabilir.
//user değişkeninin null veya boş olup olmadığı kontrol edildi. Eğer evetse, bu demektir ki kullanıcı oturum açmamış veya oturumu sona ermiştir. Bu durumda, kullanıcı giriş sayfasına yönlendirildi.
//Eğer hayırsa, bu demektir ki kullanıcı oturum açmıştır. Bu durumda, istekten kurabiye dizisi alındı. Kurabiyeler, tarayıcı tarafında veri saklamak için kullanılır.
//Oturum kurabiyesinin var olup olmadığını saklamak için bir boolean değişkeni tanımlandı. Bu değişkenin başlangıç değeri false olarak ayarlandı.
//Kurabiye dizisinde döngü yapıldı ve herhangi bir kurabiyenin adının “JSESSIONID” olup olmadığı kontrol edildi. Bu kurabiye, oturum kimliğini tutar ve sunucu ile tarayıcı arasında oturumu eşleştirmek için kullanılır.
//Eğer evetse, bu demektir ki oturum kurabiyesi mevcuttur. Bu durumda, sessionCookiePresent değişkeni true olarak ayarlandı ve döngüden çıkıldı.
//Eğer sessionCookiePresent değişkeni false ise, bu demektir ki oturum kurabiyesi yoktur veya silinmiştir. Bu durumda, oturum sonlandırıldı ve kullanıcı giriş sayfasına yönlendirildi.
