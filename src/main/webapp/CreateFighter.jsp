<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Team Fighter">
<meta name="keywords" content="Fighter game">
<title>Créer votre personnage</title>
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
			<section class="col-md-9 col-lg-7">
				<p>
				<form role="form" action="Game" method="post">
					<fieldset>
						<legend>Créer un personnage</legend>
						<div class="form-group">
							<label for="name">Entrez un nom pour votre personnage : </label> <input type="text"
								id="name" name="name" class="form-control" />
						</div>
						<div class="form-group">
							<label for="description">Entrez une courte description : </label> <input type="text" id="description"
								name="description" class="form-control" />
						</div>
					</fieldset>
					<button class="btn btn-default" type="submit">
						<span class="glyphicon glyphicon-ok-sign"></span>Créer votre personnage
					</button>
				</form>
				</p>
				<p class="text-center">
					<a href="index">Retourner à l'accueil</a>
				</p>
			</section>
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
</body>
</html>