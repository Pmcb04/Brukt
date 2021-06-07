angular.module('BruktApp')
.controller('deleteCtrl', ['usersFactory', 'productsFactory', 'commentsFactory', 'favoritesFactory','$routeParams','$location',
                            function(usersFactory, productsFactory, commentsFactory, favoritesFactory, $routeParams, $location){
    var deleteViewModel = this;
    deleteViewModel.userDelete={};
    deleteViewModel.user={};
    deleteViewModel.productDelete={};
    deleteViewModel.commentDelete={};
    deleteViewModel.functions = {

		where : function(route){	
			return $location.path()==route   		
   		},

		// Leemos una producto con el identificador id que pasamos por parametro
		readProductID : function(id) {

			productsFactory.getProductID(id)
				.then(function (response) {
					console.log("Reading product with id: ", id, " Response: ", response);
					deleteViewModel.productDelete = response;
					if((deleteViewModel.productDelete.idu != deleteViewModel.user.id) && 
						deleteViewModel.functions.where('/deleteProduct/' + deleteViewModel.productDelete.id))
							$location.path('/404');
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

		// Borramos un producto con el identificador id que pasamos por parametro
		deleteProduct : function(id) {
			
			productsFactory.deleteProduct(id)
				.then(function (response) {
					console.log("Deleting product with id ", deleteViewModel.productDelete.id, " Response: ", response);
					$location.path('user/' + deleteViewModel.user.id);
				}), function (response) {
					console.log("Error deleting product");
				}
		},

		// leemos el usuario identificado
		readUser : function() {
            
			// Complete this function
			usersFactory.getUser()
			  .then(function (response) {
				deleteViewModel.user = response
				console.log("Getting user whith id: ", deleteViewModel.user.id, " Response ", response);
			  }, function (response) {
				console.log("Error reading user data");
			  })
	
		},


        // Leemos un usuario con el identificador id que pasamos por parametro
		readUserID : function(id) {

			usersFactory.getUserID(id)
				.then(function (response) {
					console.log("Reading user with id: ", id, " Response: ", response);
					deleteViewModel.userDelete = response;
					if(deleteViewModel.userDelete.id != deleteViewModel.user.id)
						$location.path('/404');
				}), function(response) {
					console.log("Error reading user");
					$location.path('/');
				}		
			
		},

		// Borramos una categoria con el identificador id que pasamos por parametro
		deleteUser: function(id) {

			usersFactory.deleteUser(id)
				.then(function (response) {
					console.log("Deleting user with id ", id, " Response: ", response);
				}), function (response) {
					console.log("Error deleting order");
				}
		},

		// Leemos una comentario con el identificador del producto id y nombre de usuario name que pasamos por parametro
		readCommentProductUser : function(id, username) {

			commentsFactory.getCommentProductIDUserUsername(id, username)
				.then(function (response) {
					console.log("Reading comment with id", id, "and username: ", username, " Response: ", response);
					deleteViewModel.commentDelete = response;
					deleteViewModel.commentDelete.compare = deleteViewModel.commentDelete.username.localeCompare(deleteViewModel.user.username);
					console.log("user= ", deleteViewModel.user);
					console.log("commentDelete= ", deleteViewModel.commentDelete);
					if(deleteViewModel.commentDelete.compare != 0)
						$location.path('/404');
				}), function(response) {
					console.log("Error reading comment");
					$location.path('/');
				}		
			
		},


		// Borramos una comentario con el identificador id que pasamos por parametro
		deleteComment : function(idp, username) {

			commentsFactory.deleteComment(idp, username)
				.then(function (response) {
					console.log("Deleting comment with id ", idp, " username", username,  " Response: ", response);
					$location.path('user/' + deleteViewModel.user.id);
				}), function (response) {
					console.log("Error deleting comment");
				}
		},


        deleteSwitcher : function(){	
			if (deleteViewModel.functions.where('/deleteProduct/' + deleteViewModel.productDelete.id)){
				console.log($location.path());
				deleteViewModel.functions.deleteProduct(deleteViewModel.productDelete.id);
				$location.path('user/' + deleteViewModel.user.id);
			}
			else if(deleteViewModel.functions.where('/deleteUser/' + deleteViewModel.userDelete.id)){
				console.log($location.path());
				deleteViewModel.functions.deleteUser(deleteViewModel.userDelete.id);
				$location.path('/');
			}
            else if(deleteViewModel.functions.where('/deleteComment/' + deleteViewModel.commentDelete.idp + "/" + deleteViewModel.commentDelete.username)){
				console.log($location.path());  
				deleteViewModel.functions.deleteComment(deleteViewModel.commentDelete.idp, deleteViewModel.commentDelete.username);
			}
			else{
				console.log($location.path());
			}
			
			
		}
    }

    console.log("Entering deleteCtrl with $routeParams.ID_USER=",$routeParams.ID_USER," $routeParams.ID_PROD" , $routeParams.ID_PROD, " $routeParams.USERNAME=", $routeParams.USERNAME);

	deleteViewModel.functions.readUser();

    if($routeParams.USERNAME == undefined){

		if($routeParams.ID_USER == undefined){
			deleteViewModel.functions.readProductID($routeParams.ID_PROD);
		}
		else{
			deleteViewModel.functions.readUserID($routeParams.ID_USER);
		}
	
		console.log($location.path());
	}
	else{
		deleteViewModel.functions.readProductID($routeParams.ID_PROD)
		deleteViewModel.functions.readCommentProductUser($routeParams.ID_PROD, $routeParams.USERNAME);
		console.log($location.path());
	}
}])
