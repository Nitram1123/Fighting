
$(document).ready(function() {	
	// Envoie d'un message de chat
	$("#form-chat").submit(function(e){ 
		e.preventDefault(); // Bloquer l'envoie du formulaire par le navigateur
		var data_form = $(this).serialize(); // Sérialisation des données du form
		$.post("Index", data_form);
		$(this)[0].reset(); // Vider le form
	});

	// Ajout des messages à l'historique de chat à tout les 1,5 seconde
	setInterval(synchronizeMessages, 1500);
	
	function synchronizeMessages() {
		// Id du dernier message du client pour obtenir seulement les suivants
		var lastId = $('#messages :last-child')[0].id;
		$.get("Index?type=XML&lastId="+lastId,
				callbackSynchronizeMessages);
	}
	
	function callbackSynchronizeMessages(xml)  {
		//Ajout des derniers messages à l'historique de chat
		$(xml).find('msg').each(
		function () {
			var id = $(this).attr('id');
			var pseudo = $(this).find('pseudo').text();
			var body = $(this).find('body').text();
			$('<p id="'+id+'"></p>').html(pseudo+' : '+body).appendTo('#messages');
		});
	}
});