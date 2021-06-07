angular.module('BruktApp')
.controller('productAddEditCtrl', ['productsFactory','categoriesFactory', 'usersFactory', '$location','$routeParams',
                    function(productsFactory,categoriesFactory, usersFactory, $location, $routeParams){
    var productViewModel = this;
	productViewModel.product = {};
	productViewModel.user = {};
	productViewModel.categories={};
    productViewModel.functions = {

		where : function(route){	
			return $location.path()==route   		
   		},

		readUser : function() {
		
			// Complete this function
			usersFactory.getUser()
				.then(function (response) {
					productViewModel.user = response
					console.log("Getting user whith id: ", productViewModel.user.id, " Response ", response);
				}, function (response) {
					console.log("Error reading user data");
				})

		},

        // Leemos una producto con el identificador id que pasamos por parametro
		readProductID : function(id) {

			productsFactory.getProductID(id)
				.then(function (response) {
					console.log("Reading product with id: ", id, " Response: ", response);
					productViewModel.product = response;
					if(productViewModel.product.idu != productViewModel.user.id)
						$location.path('/404');
				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}		
			
		},
        // Creamos un nuevo producto que previamente hemos depositado en el controlador
		createProduct : function() {
			
			productViewModel.product.image="no_image.jpg";
			console.log("createProduct= ", productViewModel.product);
	        productsFactory.postProduct(productViewModel.product)
				.then(function (response) {
					console.log("Creating product. Response: ", response);
				}), function (response) {
					console.log("Error creating the product");
				}
			
		},

        // Actualizamos un producto que previamente hemos depositado en el controlador
		updateProduct : function() {

			productsFactory.putProduct(productViewModel.product)
				.then(function (response) {
					console.log("Updating product with id ", productViewModel.product.id, " Response: ", response);
				}), function (response) {
					console.log("Error updating product");
				}
		},	

		// Leemos todas las categorias que se encuentran disponibles
		readCategories: function () {
			categoriesFactory.getCategories()
				.then(function (response) {
					console.log("Reading categories Response: ", response);
					productViewModel.categories = response;
				}), function(response) {
					console.log("Error reading category");
					$location.path('/');
				}		
			
		},

		AddEditSwitcher : function(){	
			if (productViewModel.functions.where('/addProduct')){
				console.log($location.path());
				productViewModel.product.idu = productViewModel.user.id;
				productViewModel.product.category = productViewModel.product.category.id;
				productViewModel.product.soldout = 0;
				productViewModel.functions.createProduct();
			}
			else if(productViewModel.functions.where('/editProduct/' + productViewModel.product.id)){
				console.log($location.path());
				productViewModel.product.idu = productViewModel.user.id;
				productViewModel.product.category = productViewModel.product.category.id;
				
				if(productViewModel.product.soldout == "Vendido")
					productViewModel.product.soldout = 1;
				else
					productViewModel.product.soldout = 0;

				console.log("product update= ", productViewModel.product);

				productViewModel.functions.updateProduct();
			}
			else{
				console.log($location.path());
			}
			
			$location.path('/user/' + productViewModel.user.id);
		}
    }

	console.log("Entering productAddEditCtrl with $routeParams.ID_PROD=",$routeParams.ID_PROD);
	productViewModel.functions.readUser();
	productViewModel.functions.readCategories();
	if($routeParams.ID_PROD != undefined) productViewModel.functions.readProductID($routeParams.ID_PROD);
}])