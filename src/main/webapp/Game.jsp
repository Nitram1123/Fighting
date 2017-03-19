<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Team Fighter">
<meta name="keywords" content="Fighter game">
<title></title>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
	<section class="container">
		<header id="header" class="hero-unit">
			<div>
				<a class="sr-only sr-only-focusable" href="#content">Aller au
					contenu principal</a>
			</div>
		</header>

		<div class="row">
			<nav id="menu" role="navigation" aria-labelledby="h3menu">
				<h3 id="h3menu" role="heading" aria-level="3">Menu</h3>
				<ul class="nav nav-pills nav-stacked">


				</ul>
			</nav>

			<main id="content" class="span9" role="main">
			<div id="messages" aria-live="polite">
				<c:forEach items="${messages.items}" var="msg">
					<p id="<c:out value="${msg.id}"></c:out>">
						<c:out value="${msg.pseudo}" /> 
						: 
						<c:out value="${msg.body}" />
					</p>
				</c:forEach>
			</div>
			<form id="form-cards" role="form" >
				<fieldset>
					<legend>Liste de cartes</legend>
					<div class="form-group">
						<label for="cards" >Jouer une carte : </label>
						<select id="cards" name="cards" class="form-control">
						</select>
					</div>
				</fieldset>
				<button class="btn btn-default" type="submit">
					<span class="glyphicon glyphicon-ok-sign"></span>Jouer
				</button>
			</form>
			<form id="form-chat" role="form" >
			<fieldset>
						<legend>Chat</legend>
						<div class="form-group">
							<label for="message">Entrez un message : </label> <input type="text"
								id="message" name="message" class="form-control" />
						</div>
					</fieldset>
					<button class="btn btn-default" type="submit">
						<span class="glyphicon glyphicon-ok-sign"></span>Envoyer
					</button>
			</form>
			</main>
		</div>
		<hr>
		<footer id="footer">
			<p>© Team++</p>
		</footer>
	</section>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="js/Game.js" type="text/javascript" charset=UTF-8"></script>
</body>
</html>