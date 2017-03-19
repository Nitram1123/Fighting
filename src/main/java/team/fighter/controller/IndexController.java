package team.fighter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.fighter.model.Message;
import team.fighter.model.Messages;

/**
 * Contrôleur de l'index du jeu implémentant un petit chat
 */
@WebServlet("/Index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Conteneur des messages du chat
	private Messages messages;

	
    public IndexController() {
        super();
        messages = new Messages();
    }
			
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getParameter("type");
		// S'il y a un paramètre contentType et qu'il est égal à XML
		if (contentType != null && contentType.equalsIgnoreCase("XML")) {
			// Envoie des derniers messages de chat en xml qui seront récupéré en Ajax par le client
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			int lastId;
			try {
				// Récupération de l'id du dernier message du client afin de lui envoyer seulement les derniers 
			lastId = Integer.valueOf(request.getParameter("lastId"));
			} catch (NumberFormatException e) { return; }
			// S'il y a des nouveaux messages
			if (lastId < messages.getLastId()) {
				response.getWriter().write(messages.toXML(lastId));
			}
			return;
		}
		// Envoie de tout les messages de chat à Index.jsp
		request.setAttribute("messages", messages);
		this.getServletContext().getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Méthode post appelé en ajax pour envoyer un message de chat
		Message msg = new Message();
		msg.setPseudo(request.getParameter("pseudo"));
		msg.setBody(request.getParameter("message"));
		messages.add(msg);
	}
}