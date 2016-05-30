import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;

public class SessionCounter implements HttpSessionListener {
    private List<String> sessions = new ArrayList<>();

    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.add(session.getId());
        session.setAttribute("session-counter", this);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.remove(session.getId());
        session.setAttribute("session-counter", this);
    }

    public int getActiveSessionNumber() {
        return sessions.size();
    }
}