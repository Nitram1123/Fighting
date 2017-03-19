package team.fighter.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.fighter.model.Fighter;
import team.fighter.model.Game;
import team.fighter.model.Message;
import team.fighter.model.Messages;

@WebServlet("/Game")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Liste de tout les personnage jouant sur le serveur
	private ConcurrentHashMap<String, Fighter> fighters;
	// Liste des parties en cour
	private ConcurrentHashMap<String, Game> games;
	// Queue d'attente lorsqu'il n'y a pas un autre joueur
	private ConcurrentLinkedQueue<Fighter> waitFighters;
       
    public GameController() {
        super();
        fighters = new ConcurrentHashMap<String, Fighter>();
        games = new ConcurrentHashMap<String, Game>();
        waitFighters = new ConcurrentLinkedQueue<Fighter>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// action permettant de savoir quelle méthode appeler
		String action = request.getParameter("action");
		if (action == null) {
			// S'il n'y a pas de paramètre action, le joueur entre sur la page de jeu
			// Récupération de la clé permettant de retrouver sa partie
			String game = (String)request.getSession().getAttribute("game");
			Messages messages = games.get(game).getMessages();
			request.setAttribute("messages", messages);
			this.getServletContext().getRequestDispatcher("/Game.jsp").forward(request, response);
			return;
		}
		
		switch (action) {
		case "createFighter":
			getCreateFighter(request, response);
			break;
		case "wait":
			getWait(request, response);
			break;
		case "synchronizeMessages":
			getSynchronizeMessages(request, response);
			break;
		case "synchronizeCards":
			getSynchronizeCards(request, response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			postCreateFighter(request, response);
			return;
		}
		switch (action) {
		case "sendMessage":
			postSendMessage(request, response);
			break;
		case "sendCard":
			postSendCard(request, response);
			break;
		default:
			break;
		}
	}
	
	protected void getCreateFighter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Envoie du formulaire pour créer un personnage
		this.getServletContext().getRequestDispatcher("/CreateFighter.jsp").forward(request, response);
	}
	
	protected void postCreateFighter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Traitement du formulaire de création de personnage
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		// S'il y a déja un personnage avec ce pseudo on retourne le formulaire
		if(fighters.containsKey(name)) {
			doGet(request, response);
		} else {
			// Création d'un nouveau personnage
			Fighter fighter = new Fighter(name, description);
			// On ajoute le perso dans la liste
			fighters.put(name, fighter);
			// Ajout de la clé en session pour retrouver l'objet Fighter à travers les différentes requêtes
			request.getSession().setAttribute("name", fighter.getName());
			if(waitFighters.isEmpty()) {
				// S'il n'y a pas d'autre joueur pour jouer avec le joueur courant
				// On l'ajoute à la file d'attente
				waitFighters.add(fighter);
				// On enregistre la clé de partie en session
				request.getSession().setAttribute("game", fighter.getName());
				// On envoie le joueur sur une page d'attente
				this.getServletContext().getRequestDispatcher("/Wait.jsp").forward(request, response);
			} else {
				// S'il y a un joueur dans la file d'attente
				Fighter waitFighter = waitFighters.poll();
				request.getSession().setAttribute("game", waitFighter.getName());
				ArrayList<Fighter> gamers = new ArrayList<Fighter>();
				gamers.add(waitFighter);
				gamers.add(fighter);
				// Création de la nouvelle partie
				games.put(waitFighter.getName(), new Game(gamers));
				// Redirection sur la page de jeu
				response.sendRedirect("Game");
			}
		}
	}
	
	protected void getWait(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Méthode appelé par le script Wait.js pour savoir si le joueur a été invité par un autre
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		String name = (String)request.getSession().getAttribute("name");
		Fighter fighter = fighters.get(name);
		if(waitFighters.contains(fighter)) {
			response.getWriter().write("<response>true</response>");
		} else {
			response.getWriter().write("<response>false</response>");
		}
	}
	
	protected void postSendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Envoie d'un message de chat
		String game = (String)request.getSession().getAttribute("game");
		Message msg = new Message();
		msg.setPseudo((String)request.getSession().getAttribute("name"));
		msg.setBody(request.getParameter("message"));
		games.get(game).getMessages().add(msg);
	}
	
	protected void getSynchronizeMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Méthode appelé par le script Game.js pour savoir s'il y a des nouveaux messages
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		int lastId;
		try {
		lastId = Integer.valueOf(request.getParameter("lastId"));
		} catch (NumberFormatException e) { return; }
		String game = (String)request.getSession().getAttribute("game");
		
		if (lastId < games.get(game).getMessages().getLastId()) {
			response.getWriter().write(games.get(game).getMessages().toXML(lastId));
		}
	}
	
	protected void getSynchronizeCards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Méthode appelé par le script Game.js pour savoir s'il y a des cartes à synchroniser
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		String name = (String)request.getSession().getAttribute("name");
		response.getWriter().write(fighters.get(name).getSynchronizeCards());
	}
	
	protected void postSendCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Un joueur joue une carte
		String game = (String)request.getSession().getAttribute("game");
		String name = (String)request.getSession().getAttribute("name");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		int id_card;
		try {
			id_card = Integer.valueOf(request.getParameter("id"));
			} catch (NumberFormatException e) { return; }
		if(games.get(game).play(fighters.get(name), id_card)) {
			// Si la carte a bien été joué on renvoie une réponse positive au client pour qu'il puisse supprimer la carte
			response.getWriter().write("<response>true</response>");
		} else {
			response.getWriter().write("<response>false</response>");
		}
	}
}