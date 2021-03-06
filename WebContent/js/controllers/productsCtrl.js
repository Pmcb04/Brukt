angular.module('BruktApp')
.controller('productsCtrl', ['productsFactory','$location','$routeParams',
                    function(productsFactory,$location, $routeParams){
    var productViewModel = this;
    productViewModel.product={};
	productViewModel.productsCategory={};
	productViewModel.productsRelacionados={};
	productViewModel.productsSold={};
	productViewModel.productsSoldNumber={};
	productViewModel.productsSale={};
	productViewModel.productsSaleNumber={};
    productViewModel.functions = {

        // Leemos una producto con el identificador id que pasamos por parametro
		readProductID : function(id) {

			productsFactory.getProductID(id)
				.then(function (response) {
					console.log("Reading product with id: ", id, " Response: ", response);
					productViewModel.product = response;
					if(productViewModel.product.idu != productViewModel.user.id)
						$location.path('/404/');
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

        // Leemos los productos donde su titulo contenga con search que pasamos por parametro
        readProductSearchTitle : function(search) {

			productsFactory.getProductSearchTitle(search)
				.then(function (response) {
					console.log("Reading product with search title: ", search, " Response: ", response);
					productViewModel.product = response;
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},


        // Leemos los productos donde su descripcion contenga con search que pasamos por parametro
        readProductSearchDescription : function(search) {

			productsFactory.getProductSearchDescription(search)
				.then(function (response) {
					console.log("Reading product with search description: ", description, " Response: ", response);
					productViewModel.product = response;
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

        // Leemos los productos donde su titulo o descripcion contenga con search que pasamos por parametro
        readProductSearchAll : function(search) {

			productsFactory.getProductSearchAll(search)
				.then(function (response) {
					console.log("Reading product with search title and search description: ", search, " Response: ", response);
					productViewModel.product = response;
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

        // Leemos los productos de un usuario con el identificador id que pasamos por parametro
		readProductsUser : function(id) {

			productsFactory.getProductsUser(id)
				.then(function (response) {
					console.log("Reading products user with id: ", id, " Response: ", response);
					productViewModel.product = response;
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

        // Leemos los productos vendidos de un usuario con el identificador id que pasamos por parametro
		readProductsUserSold : function(id) {

			productsFactory.getUserSold(id, 1)
				.then(function (response) {
					console.log("Reading products user with id: ", id, " sold ",  " Response: ", response);
					productViewModel.productsSold = response;
					productViewModel.productsSoldNumber = response.length;
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
					productViewModel.productsSale = response;
					productViewModel.productsSaleNumber = response.length;
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},


        // Leemos los productos de una categoria con el identificador id o con el precio price que pasamos por parametro
		readProductsCategoryPrice : function(id, price) {

			productsFactory.getCategoryPrice(id, price)
				.then(function (response) {
					console.log("Reading products category with id: ", id, " and price ", price, " Response: ", response);
					productViewModel.productsRelacionados = response;
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},


        // Leemos los productos de una categoria con el identificador id que pasamos por parametro
		readProductsCategory : function(id) {

			productsFactory.getCategory(id)
				.then(function (response) {
					console.log("Reading products category with id: ", id, " Response: ", response);
					productViewModel.productsCategory = response;
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},

        // Creamos un nuevo producto que previamente hemos depositado en el controlador
		createProduct : function() {

	        productsFactory.postProduct(productViewModel.product)
				.then(function (response) {
					console.log("Creating product. Response: ", response);
					$location.path('/');
				}), function (response) {
					console.log("Error creating the product");
				}
			
		},

        // Actualizamos un producto que previamente hemos depositado en el controlador
		updateProduct : function() {

			productsFactory.putProduct(productViewModel.product)
				.then(function (response) {
					console.log("Updating product with id ", productViewModel.product.id, " Response: ", response);
					$location.path('/');
				}), function (response) {
					console.log("Error updating product");
				}
		},	

        // Borramos un producto con el identificador id que pasamos por parametro
		deleteProduct : function() {

			productsFactory.deleteProduct(productViewModel.product.id)
				.then(function (response) {
					console.log("Deleting product with id ", productViewModel.product.id, " Response: ", response);
					$location.path('/');
				}), function (response) {
					console.log("Error deleting product");
				}
		}

		// TODO ??hace falta?
        // Metodo para saber que acccion realizar segun la ruta del navegador
        // productSwitcher : function(){	

		// 	if(productViewModel.functions.where('/insertproduct')){
		// 		console.log($location.path());
		// 		productViewModel.functions.createproduct();
		// 	}
		// 	else if (productViewModel.functions.where('/editproduct/' + productViewModel.product.id)){
		// 		console.log($location.path());
		// 		productViewModel.functions.updateproduct();
		// 	}
		// 	else if(productViewModel.functions.where('/deleteproduct/' + productViewModel.product.id)){
		// 		console.log($location.path());
		// 		productViewModel.functions.deleteproduct();
		// 	}
		// 	else{
		// 		console.log($location.path());
		// 	}
			
		// 	$location.path('/');
		// }
    }

	console.log("Entering productsCtrl with $routeParams.ID=",$routeParams.ID);
	productViewModel.functions.readProductsUserSale($routeParams.ID);
	productViewModel.functions.readProductsUserSold($routeParams.ID);
	productViewModel.functions.readProductID($routeParams.ID);
	productViewModel.functions.readProductsCategory($routeParams.NOMBRE);

    // TODO ??hace falta?
    /*
   	if ($routeParams.ID==undefined) productViewModel.functions.createproduct();
   	else productViewModel.functions.readOrder($routeParams.ID);*/
}])