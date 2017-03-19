$(document).ready(function() {
		// Vérifit s'il y a un joueur disponible ou s'il faut attendre encore 
		setInterval(wait, 2000);
		function wait() {
			$.get("Game?action=wait",
					callbackWait);
		}
		
		function callbackWait(xml) {
			// Si le serveur ne trouve plus le joueur dans la liste d'attente
			// Il a donc été invité
			// On le redirige dans sa partie
			var goToGame = false;
			$(xml).find('response').each(
					function () {
						if($(this).text() == "false") {
							goToGame = true;
						}
					});
			if(goToGame) {
				// Redirection
				window.location.href = "Game";
			}
		}
	});