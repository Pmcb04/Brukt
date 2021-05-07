angular.module('') // TODO : poner nombre al modulo
.controller('categoriesCtrl', ['categoriesFactory','$location','$routeParams',
                    function(categoriesFactory,$location, $routeParams){
    var categoryViewModel = this;
    categoryViewModel.category={};
    categoryViewModel.functions = {

        // Leemos una categoria con el identificador id que pasamos por parametro
		readCategoryID : function(id) {

			categoriesFactory.getCategoryID(id)
				.then(function (response) {
					console.log("Reading category with id: ", id, " Response: ", response);
					categoryViewModel.category = response;
				}), function(response) {
					console.log("Error reading category");
					$location.path('/');
				}		
			
		},

        // Leemos una categoria con el nombre name que pasamos por parametro
        readCategoryName : function(name) {

			categoriesFactory.getCategoryName(name)
				.then(function (response) {
					console.log("Reading category with name: ", name, " Response: ", response);
					categoryViewModel.category = response;
				}), function(response) {
					console.log("Error reading category");
					$location.path('/');
				}		
			
		},

        // Creamos una nueva categoria que previamente hemos depositado en el controlador
		createCategory : function() {

	        categoriesFactory.postCategory(categoryViewModel.category)
				.then(function (response) {
					console.log("Creating category. Response: ", response)
				}), function (response) {
					console.log("Error creating the category");
				}
			
		},

        // Actualizamos una categoria que previamente hemos depositado en el controlador
		updateCategory : function() {

			categoriesFactory.putCategory(categoryViewModel.category)
				.then(function (response) {
					console.log("Updating category with id ", categoryViewModel.category.id, " Response: ", response);
				}), function (response) {
					console.log("Error updating category");
				}
		},	

        // Borramos una categoria con el identificador id que pasamos por parametro
		deleteCategory : function(id) {

			categoriesFactory.deleteCategory(id)
				.then(function (response) {
					console.log("Deleting category with id ", id, " Response: ", response);
				}), function (response) {
					console.log("Error deleting category");
				}
		}

		// TODO ¿hace falta?
        // Metodo para saber que acccion realizar segun la ruta del navegador
        // categorySwitcher : function(){	

		// 	if(categoryViewModel.functions.where('/insertCategory')){
		// 		console.log($location.path());
		// 		categoryViewModel.functions.createCategory();
		// 	}
		// 	else if (categoryViewModel.functions.where('/editCategory/' + categoryViewModel.category.id)){
		// 		console.log($location.path());
		// 		categoryViewModel.functions.updateCategory();
		// 	}
		// 	else if(categoryViewModel.functions.where('/deleteCategory/' + categoryViewModel.category.id)){
		// 		console.log($location.path());
		// 		categoryViewModel.functions.deleteCategory();
		// 	}
		// 	else{
		// 		console.log($location.path());
		// 	}
			
		// 	$location.path('/');
		// }
    }

	console.log("Entering categoriesCtrl with $routeParams.ID=",$routeParams.ID);

    // TODO ¿hace falta?
    /*
   	if ($routeParams.ID==undefined) categoryViewModel.functions.createCategory();
   	else categoryViewModel.functions.readOrder($routeParams.ID);*/
}])