angular.module('BruktApp')
.controller('productPageCtrl', ['productsFactory','favoritesFactory', 'usersFactory', 'commentsFactory', '$location','$routeParams',
                    function(productsFactory,favoritesFactory, usersFactory, commentsFactory,  $location, $routeParams){
    var productViewModel = this;
    productViewModel.product={};
    productViewModel.userLog={};
	productViewModel.product.favorites=0;
    productViewModel.userLog={};
    productViewModel.favorite={};
    productViewModel.newComment={};
    productViewModel.imageFavorite={};
    productViewModel.favoritesUser=[];
	productViewModel.favoritesProduct={};
	productViewModel.commentsProduct=[];
	productViewModel.othersProducts={};
	productViewModel.productsRelacionados=[];

	function removeItemFromArr ( arr, item ) {
		var i = arr.indexOf( item );
		arr.splice( i, 1 );
	}


    productViewModel.functions = {
		
        readUser : function() {
        
		// Complete this function
			usersFactory.getUser()
			.then(function (response) {
				productViewModel.userLog = response
				console.log("Getting user whith id: ", productViewModel.userLog.id, " Response ", response);
				if(productViewModel.userLog.id != undefined) productViewModel.functions.readFavoritesUser(productViewModel.userLog.id);
			}, function (response) {
				console.log("Error reading user data");
			})

		},
	
			
		// Leemos los favoritos del usuario con el identificador id que pasamos por parametro
		readFavoritesUser : function(id) {

			favoritesFactory.getFavoritesUserID(id)
				.then(function (response) {
					console.log("Reading favorites user with id: ", id, " Response: ", response);
					productViewModel.favoritesUser = response;
				}), function(response) {
					console.log("Error reading favorite");
					$location.path('/');
				}		
			
		},

        // Leemos una producto con el identificador id que pasamos por parametro
		readProductID : function(id) {

			productsFactory.getProductID(id)
				.then(function (response) {
					console.log("Reading product with id: ", id, " Response: ", response);
					productViewModel.product = response;
					productViewModel.functions.readUserID(productViewModel.product.idu);
					productViewModel.functions.readFavoritesProductID(productViewModel.product.id);
					productViewModel.functions.readProductsCategoryPrice();
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

		// Leemos los favoritos del producto con el identificador id que pasamos por parametro
		readFavoritesProductID : function(id) {

			favoritesFactory.getFavoritesProductID(id)
				.then(function (response) {
					console.log("Reading favorites product with id: ", id, " Response: ", response);
					productViewModel.favoritesProduct = response;
					productViewModel.product.favorites = response.length;
					productViewModel.product.favorite_user = false;

					for (let i = 0; i < productViewModel.favoritesUser.length; i++) {
                        if(productViewModel.favoritesUser[i].id === productViewModel.product.id) productViewModel.product.favorite_user = true;
                    }

					console.log("favorite_user= ", productViewModel.product.favorite_user);

					if(productViewModel.product.favorite_user) productViewModel.imageFavorite = "favorite";
					else productViewModel.imageFavorite = "favorite_border";

				}), function(response) {
					console.log("Error reading favorite");
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

					for (let i = 0; i < productViewModel.favoritesUser.length; i++) {
						if(productViewModel.favoritesUser[i].id === prod.id) prod.favorite_user = true;
					}

				}), function(response) {
					console.log("Error reading favorite");
					$location.path('/');
				}		
			
		},


        // Leemos los productos de una categoria con el identificador id o con el precio price que pasamos por parametro
		readProductsCategoryPrice : function() {

			productsFactory.getCategoryPrice(productViewModel.product.category, productViewModel.product.price)
				.then(function (response) {
					console.log("Reading products category with id: ", productViewModel.product.category, " and price ", productViewModel.product.price, " Response: ", response);
					productViewModel.productsRelacionados = response;

					// eliminamos el propio producto de la lista de productos relacionados
					for (let i = 0; i < productViewModel.productsRelacionados.length; i++) {
                        if(productViewModel.productsRelacionados[i].id === productViewModel.product.id) 
							removeItemFromArr(productViewModel.productsRelacionados, productViewModel.productsRelacionados[i]);
                    }

					// calculamos el numero de favoritos de cada producto
					productViewModel.productsRelacionados.forEach(prod => {
                        productViewModel.functions.readFavoritesProduct(prod);
                    });


				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

		// Leemos un usuario con el identificador id que pasamos por parametro
		readUserID : function(id) {

			usersFactory.getUserID(id)
				.then(function (response) {
					console.log("Reading user with id: ", id, " Response: ", response);
					productViewModel.product.user = response;
				}), function(response) {
					console.log("Error reading user");
					$location.path('/');
				}		
			
		},

		// Creamos un nuevo favorito que previamente hemos depositado en el controlador
		createFavorite : function(idu, idp) {

			productViewModel.favorite.idp = idp;
			productViewModel.favorite.idu = idu;
			favoritesFactory.postFavorite(productViewModel.favorite)
				.then(function (response) {
					console.log("Creating favorite. Response: ", response);
					productViewModel.product.favorite_user = true;
					productViewModel.imageFavorite = "favorite";
				}), function (response) {
					console.log("Error creating the favorite");
				}
			
		},
		
		// Borramos un favorito con el identificador id que pasamos por parametro
		deleteFavorite : function(idu, idp) {

			favoritesFactory.deleteFavorite(idu, idp)
				.then(function (response) {
					console.log("Deleting favorite with idu ", idu, " idp ", idp, "Response: ", response);
					productViewModel.product.favorite_user = false;
					productViewModel.imageFavorite = "favorite_border";
				}), function (response) {
					console.log("Error deleting favorite");
				}
		},

		// Leemos una comentario con el identificador id que pasamos por parametro
		readCommentProductID : function(id) {

		commentsFactory.getCommentProductID(id)
			.then(function (response) {
				console.log("Reading comment with id: ", id, " Response: ", response);
				productViewModel.commentsProduct = response;
			}), function(response) {
				console.log("Error reading comment");
				$location.path('/');
			}		
		
		},


        // Creamos una nueva comentario que previamente hemos depositado en el controlador
		createComment : function() {

			productViewModel.newComment.idp = productViewModel.product.id;
			productViewModel.newComment.date = new Date();
			productViewModel.newComment.username = productViewModel.userLog.username;

			console.log("newComment= ", productViewModel.newComment);

	        commentsFactory.postComment(productViewModel.newComment)
				.then(function (response) {
					console.log("Creating comment. Response: ", response);
					productViewModel.functions.readCommentProductID(productViewModel.product.id);
				}), function (response) {
					console.log("Error creating the comment");
				}
			
		},

		// Borramos una comentario con el identificador id que pasamos por parametro
		deleteComment : function(idp, username) {

			commentsFactory.deleteComment(idp, username)
				.then(function (response) {
					console.log("Deleting comment with id ", idp, " username", username,  " Response: ", response);
					
					for (let i = 0; i < productViewModel.commentsProduct.length; i++) {
                        if(productViewModel.commentsProduct[i].id === idp && productViewModel.commentsProduct[i].username === username) 
							removeItemFromArr(productViewModel.commentsProduct, productViewModel.commentsProduct[i]);
                    }

					productViewModel.functions.readCommentProductID($routeParams.ID_PROD);
					
				}), function (response) {
					console.log("Error deleting comment");
				}
		},

        // Leemos una comentario con el identificador del producto id y rating  que pasamos por parametro
        readCommentProductRating : function(id, rating) {

			commentsFactory.getCommentProductIDRating(id, rating)
				.then(function (response) {
					console.log("Reading comment with id", id, "and rating: ", rating, " Response: ", response);
					productViewModel.commentsProduct = response;
				}), function(response) {
					console.log("Error reading comment");
					$location.path('/');
				}		
			
		},

    }

	console.log("Entering productPageCtrl with $routeParams.ID_PROD=",$routeParams.ID_PROD, "$routeParams.CATEGORY=",$routeParams.CATEGORY);
	productViewModel.functions.readUser();
	productViewModel.functions.readProductID($routeParams.ID_PROD);
	productViewModel.functions.readCommentProductID($routeParams.ID_PROD);

}])