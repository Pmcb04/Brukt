angular.module('BruktApp')
.controller('favoritesCtrl', ['favoritesFactory','$location','$routeParams',
                    function(favoritesFactory,$location, $routeParams){
    var favoriteViewModel = this;
	favoriteViewModel.favorite = {};
    favoriteViewModel.favoritesUser={};
    favoriteViewModel.favoritesUserNumber={};
    favoriteViewModel.favoritesProduct={};
    favoriteViewModel.favoritesProductNumber={};
	favoriteViewModel.favoriteUserProduct={};
    favoriteViewModel.functions = {

        // Leemos los favoritos del usuario con el identificador id que pasamos por parametro
		readFavoritesUserID : function(id) {

			favoritesFactory.getFavoritesUserID(id)
				.then(function (response) {
					console.log("Reading favorites user with id: ", id, " Response: ", response);
					favoriteViewModel.favoritesUser = response;
					favoriteViewModel.favoritesUserNumber = response.length;
				}), function(response) {
					console.log("Error reading favorite");
					$location.path('/');
				}		
			
		},

        // Leemos los favoritos del producto con el identificador id que pasamos por parametro
        readFavoritesProductID : function(id) {

			favoritesFactory.getFavoritesProductID(id)
				.then(function (response) {
					console.log("Reading favorites product with id: ", id, " Response: ", response);
					favoriteViewModel.favoritesProduct = response;
					favoriteViewModel.favoritesProductNumber = response.length;
					console.log("Favorites number of product id", id , favoriteViewModel.favoritesProductNumber);
				}), function(response) {
					console.log("Error reading favorite");
					$location.path('/');
				}		
			
		},

        // Creamos un nuevo favorito que previamente hemos depositado en el controlador
		createFavorite : function(idu, idp) {

			favoriteViewModel.favorite.idp = idp;
			favoriteViewModel.favorite.idu = idu;
	        favoritesFactory.postFavorite(favoriteViewModel.favorite)
				.then(function (response) {
					console.log("Creating favorite. Response: ", response)
				}), function (response) {
					console.log("Error creating the favorite");
				}
			
		},
		
        // Borramos un favorito con el identificador id que pasamos por parametro
		deleteFavorite : function(idu, idp) {

			favoritesFactory.deleteFavorite(idu, idp)
				.then(function (response) {
					console.log("Deleting favorite with idu ", idu, " idp ", idp, "Response: ", response);
				}), function (response) {
					console.log("Error deleting favorite");
				}
		}

		// TODO ¿hace falta?
        // Metodo para saber que acccion realizar segun la ruta del navegador
        // favoriteSwitcher : function(){	

		// 	if(favoriteViewModel.functions.where('/insertfavorite')){
		// 		console.log($location.path());
		// 		favoriteViewModel.functions.createfavorite();
		// 	}
		// 	else if (favoriteViewModel.functions.where('/editfavorite/' + favoriteViewModel.favorite.id)){
		// 		console.log($location.path());
		// 		favoriteViewModel.functions.updatefavorite();
		// 	}
		// 	else if(favoriteViewModel.functions.where('/deletefavorite/' + favoriteViewModel.favorite.id)){
		// 		console.log($location.path());
		// 		favoriteViewModel.functions.deletefavorite();
		// 	}
		// 	else{
		// 		console.log($location.path());
		// 	}
			
		// 	$location.path('/');
		// }
    }

	console.log("Entering favoritesCtrl with $routeParams.ID=",$routeParams.ID);
	favoriteViewModel.functions.readFavoritesUserID($routeParams.ID);
	favoriteViewModel.functions.readFavoritesProductID($routeParams.ID);

    // TODO ¿hace falta?
    /*
   	if ($routeParams.ID==undefined) favoriteViewModel.functions.createfavorite();
   	else favoriteViewModel.functions.readOrder($routeParams.ID);*/
}])