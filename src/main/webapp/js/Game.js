
$(document).ready(function() {
	// Envoie d'un message de chat
	$("#form-chat").submit(function(e) { 
		e.preventDefault(); // Bloquer l'envoie du formulaire par le navigateur
		var data_form = $(this).serialize(); // Sérialisation des données du form
		$.post("Game?action=sendMessage", data_form);
		$(this)[0].reset(); // Vider le form
	});

	// Ajout des messages à l'historique à tout les 1,5 seconde
	setInterval(synchronizeMessages, 1500);
	
	function synchronizeMessages() {
		var lastId = $('#messages :last-child')[0].id;
		$.get("Game?action=synchronizeMessages&lastId="+lastId,
				callbackSynchronizeMessages);
	}
	
	function callbackSynchronizeMessages(xml)  {
		$(xml).find('msg').each(
		function () {
			var id = $(this).attr('id');
			var pseudo = $(this).find('pseudo').text();
			var body = $(this).find('body').text();
			$('<p id="'+id+'"></p>').html(pseudo+' : '+body).appendTo('#messages');
		});
	}
	// Synchronise la liste de carte
	setInterval(synchronizeCards, 1500);
	
	function synchronizeCards() {
		$.get("Game?action=synchronizeCards",
				callbackSynchronizeCards);
	}
	
	function callbackSynchronizeCards(xml)  {
		$(xml).find('card').each(
		function () {
			var id = $(this).attr('id');
			var description = $(this).find('description').text();
			var vitality = $(this).find('vitality').text();
			var mana = $(this).find('mana').text();
			$('<option value="'+id+'"></option>')
			.html(description+', vitality('+vitality+'), mana('+mana+')')
			.appendTo('#cards');
		});
	}
	
	// Jouer une carte
	// Enregistrement de la carte pour la supprimer de la liste si le serveur confirme qu'elle a été joué
	var g_id_card;
	$("#form-cards").submit(function(e) {
		e.preventDefault(); // Bloquer l'envoie du formulaire par le navigateur
		var value = $("#cards").val();
		g_id_card = value;
		$.post("Game", "action=sendCard&id="+value, callbackSendCard);
	});
	
	function callbackSendCard(xml) {
		$(xml).find('response').each(
				function () {
					if($(this).text() == "true") {
						$('#cards option[value=' + g_id_card + ']').remove();
					}
				});
	}
});