<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Combat à mort</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<h1>Bienvenue dans le jeu Combat à mort</h1>
<p>Combat à mort, est un jeu de cartes dont le but est de massacrer son adversaire jusqu’à ce que mort s’en suivre. Au départ, vous êtes armée avec la plus petite arme. C’est-à-dire, un couteau à beur qui fait deux points de vie en dégât. Vous avez en tout dans le jeu 72 cartes qui vous permettent d’attaquer, de changer d’arme, de lancer des sorts de magies et de boire des potions de vie où de magie. Chaque combat à mort est composé de trois manches. Au début de chacune d’entre elle, vous avez 100 points de vie et 50 points de magie.</p>
<p>Parmi les 72 cartes, il y en a 8 qui permettent de changer votre arme pour une meilleure. Ces huit armes font entre 4 et 20 points de dégâts et pour les utiliser, il y a dans le paquet 24 cartes attaqué. Ensuite, il y en a24 de magies avec 8 sorts différents qui font entre 2 et 20 points de dégâts à votre adversaire, mais qui vous coûte entre huit à 44 points de magie. Finalement, 16 cartes permettent de boire une potion qui fait remonter soit votre vie ou votre magie.</p>
<h2>Liste des cartes</h2>

<h3>Carte arme : une carte de chaque</h3>
<ul>
<li>Couteau rouillé (4 pts)</li>
<li>Un poignard courbé (6 pts)</li>
<li>Une dague affilée (8 pts)</li>
<li>Une épée noire (10 pts)</li>
<li>Une épée d’argent (12 pts)</li>
<li>Un sabre tranchant (14 pts)</li>
<li>Une hache incrustée de pierres précieuses (16 pts»)</li>
<li>Une double hache tranchante vénéneuse de la mort (20 pts)</li>
</ul>
<h3>Carte Magie : trois cartes de chaque</h3>
<ul>
<li>Une boule de terre, -2 de vie, -4 mana</li>
<li>Une boule de glace, -4 de vie, -8 mana</li>
<li>Une boule de feu, -6 de vie, -12 mana</li>
<li>Une boule d’ombre qui aspire l’énergie vitale : -10 de vie, -24 mana</li>
<li>Une flèche de lumière aveuglante -12 de vie -28 mana</li>
<li>Un éclair foudroyant -14 de vie -32 mana</li>
<li>Un cyclone glacial -16 de vie, -36 mana</li>
<li>Une énorme vague de lave -20 de vie, -44 mana</li>
</ul>
<h3>Carte potions (quatre cartes de chaque)</h3>
<ul>
<li>Une potion jaune +5 de vie</li>
<li>Une potion rouge +10 de vie</li>
<li>Une potion transparente +10 de mana</li>
<li>Une potion noire +20 de mana</li>
</ul>
<p>24 cartes Attaquer!</p>
<h2>Chaque manche se déroule ainsi :</h2>
<p>Le premier joueur mélange les cartes,  en distribue 12 à chacun et dépose le paquet sur la table.</p>
<p>En commençant par le deuxième joueur, chacun des joueurs à leur tour prend une carte sur le dessus du paquet et en joue une ou la jette dans la pile de réserve. Aussitôt qu’il y a une carte dans cette dernière, les joueurs ont l’option de prendre la dernière carte jetée ou de continuer à prendre une carte sur le dessus du paquet principal. La partie continue tant qu’un des deux joueur à pas attein zéro de vie et l’autre joueur à gagné.</p>
<p><a href="Game?action=createFighter">Jouer</a></p>
<h1>Discuter avec les autres joueurs</h1>
<div id="messages" aria-live="polite" >
	<c:forEach items="${messages.items}" var="msg" >
		<p id="<c:out value="${msg.id}"></c:out>" ><c:out value="${msg.pseudo}" /> : <c:out value="${msg.body}" /></p>
	</c:forEach>
</div>
<form id="form-chat" >
	<h1>Entrer votre message</h1>
	<input type="text" name="pseudo" />
	<input type="text" name="message" />
	<input type="submit" />
</form>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="js/Index.js" type="text/javascript" charset=UTF-8"></script>
</body>
</html>