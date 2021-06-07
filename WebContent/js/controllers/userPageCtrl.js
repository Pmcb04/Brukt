angular.module('BruktApp')
.controller('userPageCtrl', ['usersFactory','productsFactory', 'favoritesFactory', '$location','$routeParams',
                    function(usersFactory, productsFactory, favoritesFactory, $location, $routeParams){

    var userViewModel = this;
    userViewModel.user={};
    userViewModel.userLog={};
    userViewModel.productsSold=[];
    userViewModel.productsSale=[];
    userViewModel.favoritesUser=[];

    userViewModel.functions = {


		where : function(route){	
			return $location.path()==route   		
		  },
	

		readUser : function() {
            
			// Complete this function
			usersFactory.getUser()
			  .then(function (response) {
				userViewModel.userLog = response
				console.log("Getting user whith id: ", userViewModel.userLog.id, " Response ", response);
			  }, function (response) {
				console.log("Error reading user data");
			  })
	
		  },

        // Leemos un usuario con el identificador id que pasamos por parametro
		readUserID : function(id) {

			usersFactory.getUserID(id)
				.then(function (response) {
					console.log("Reading user with id: ", id, " Response: ", response);
					userViewModel.user = response;
				}), function(response) {
					console.log("Error reading user");
					$location.path('/');
				}		
			
		},

		// Leemos los favoritos del producto con el identificador id que pasamos por parametro
		readFavoritesProduct : function(prod) {

			favoritesFactory.getFavoritesProductID(prod.id)
				.then(function (response) {
					console.log("Reading favorites product with id: ", prod.id, " Response: ", response);
					prod.favorites = response.length;
					prod.favorite_user = false;

					for (let i = 0; i < userViewModel.favoritesUser.length; i++) {
						if(userViewModel.favoritesUser[i].id === prod.id) prod.favorite_user = true;
					}

				}), function(response) {
					console.log("Error reading favorite");
					$location.path('/');
				}		
			
		},


        // Leemos los productos vendidos de un usuario con el identificador id que pasamos por parametro
		readProductsUserSold : function(id) {

			productsFactory.getUserSold(id, 1)
				.then(function (response) {
					console.log("Reading products user with id: ", id, " sold ",  " Response: ", response);
					userViewModel.productsSold = response;

					userViewModel.productsSold.forEach(prod => {
                        userViewModel.functions.readFavoritesProduct(prod);
                    });

				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},


        // Leemos los productos en venta de un usuario con el identificador id que pasamos por parametro
		readProductsUserSale : function(id) {

			productsFactory.getUserSold(id, 0)
				.then(function (response) {
					console.log("Reading products user with id: ", id, " sale ",  " Response: ", response);
					userViewModel.productsSale = response;

					userViewModel.productsSale.forEach(prod => {
                        userViewModel.functions.readFavoritesProduct(prod);
                    });

				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},


        // Leemos los favoritos del usuario con el identificador id que pasamos por parametro
		readFavoritesUserID : function(id) {

			favoritesFactory.getFavoritesUserID(id)
				.then(function (response) {
					console.log("Reading favorites user with id: ", id, " Response: ", response);
					userViewModel.favoritesUser = response;

					userViewModel.favoritesUser.forEach(prod => {
                        userViewModel.functions.readFavoritesProduct(prod);
                    });

				}), function(response) {
					console.log("Error reading favorite");
					$location.path('/');
				}		
			
		},

		// Actualizamos a el usuario logeado que previamente hemos depositado en el controlador
		updateUser : function() {

			usersFactory.putUser(userViewModel.user)
				.then(function (response) {
					console.log("Updating user with id ", userViewModel.user.id, " Response: ", response);
				}), function (response) {
					console.log("Error updating user");
				}
		}
       
    }

	console.log("Entering userPageCtrl with $routeParams.ID_USER=",$routeParams.ID_USER);
	userViewModel.functions.readUser();
	userViewModel.functions.readUserID($routeParams.ID_USER);
	userViewModel.functions.readProductsUserSold($routeParams.ID_USER);
	userViewModel.functions.readProductsUserSale($routeParams.ID_USER);
	userViewModel.functions.readFavoritesUserID($routeParams.ID_USER);

}])