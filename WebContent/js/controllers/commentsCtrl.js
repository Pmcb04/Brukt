angular.module('') // TODO : poner nombre al modulo
.controller('commentsCtrl', ['commentsFactory','$location','$routeParams',
                    function(commentsFactory,$location, $routeParams){
    var commentViewModel = this;
    commentViewModel.comment={};
    commentViewModel.functions = {

        // Leemos una comentario con el identificador id que pasamos por parametro
		readCommentProductID : function(id) {

			commentsFactory.getCommentProductID(id)
				.then(function (response) {
					console.log("Reading comment with id: ", id, " Response: ", response);
					commentViewModel.Comment = response;
				}), function(response) {
					console.log("Error reading comment");
					$location.path('/');
				}		
			
		},

        // Leemos una comentario con el identificador del producto id y nombre de usuario name que pasamos por parametro
        readCommentProductUser : function(id, username) {

			commentsFactory.getCommentProductIDUserUsername(id, username)
				.then(function (response) {
					console.log("Reading comment with id", id, "and username: ", username, " Response: ", response);
					commentViewModel.Comment = response;
				}), function(response) {
					console.log("Error reading comment");
					$location.path('/');
				}		
			
		},

        // Leemos una comentario con el identificador del producto id y rating  que pasamos por parametro
        readCommentProductRating : function(id, rating) {

			commentsFactory.getCommentProductIDRating(id, rating)
				.then(function (response) {
					console.log("Reading comment with id", id, "and rating: ", rating, " Response: ", response);
					commentViewModel.Comment = response;
				}), function(response) {
					console.log("Error reading comment");
					$location.path('/');
				}		
			
		},


        // Creamos una nueva comentario que previamente hemos depositado en el controlador
		createComment : function() {

	        commentsFactory.postComment(commentViewModel.comment)
				.then(function (response) {
					console.log("Creating comment. Response: ", response)
				}), function (response) {
					console.log("Error creating the comment");
				}
			
		},

        // Borramos una comentario con el identificador id que pasamos por parametro
		deleteComment : function(id) {

			commentsFactory.deleteComment(id)
				.then(function (response) {
					console.log("Deleting comment with id ", id, " Response: ", response);
				}), function (response) {
					console.log("Error deleting comment");
				}
		}

		// TODO ¿hace falta?
        // Metodo para saber que acccion realizar segun la ruta del navegador
        // CommentSwitcher : function(){	

		// 	if(CommentViewModel.functions.where('/insertComment')){
		// 		console.log($location.path());
		// 		CommentViewModel.functions.createComment();
		// 	}
		// 	else if (CommentViewModel.functions.where('/editComment/' + CommentViewModel.Comment.id)){
		// 		console.log($location.path());
		// 		CommentViewModel.functions.updateComment();
		// 	}
		// 	else if(CommentViewModel.functions.where('/deleteComment/' + CommentViewModel.Comment.id)){
		// 		console.log($location.path());
		// 		CommentViewModel.functions.deleteComment();
		// 	}
		// 	else{
		// 		console.log($location.path());
		// 	}
			
		// 	$location.path('/');
		// }
    }

	console.log("Entering commentsCtrl with $routeParams.ID=",$routeParams.ID);

    // TODO ¿hace falta?
    /*
   	if ($routeParams.ID==undefined) CommentViewModel.functions.createComment();
   	else CommentViewModel.functions.readOrder($routeParams.ID);*/
}])