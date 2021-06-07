angular.module('BruktApp')
.factory('categoriesFactory',['$http', function($http){
	var url = 'https://localhost:8443/Brukt/rest/categories/';
    var categoriesInterface = {

		// Obtenemos todas las categorias del sistema
    	getCategories : function(){

			return $http.get(url)
				.then(function (response) {
					return response.data;
				});
    	
    	},

		// Obtenemos la categoria que le pasemos el ID por paramtro
    	getCategoryID : function(id){

    		var newurl = url + "id/" + id;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos la categoria que le pasemos el name por paramtro
    	getCategoryName : function(name){
			
    		var newurl  = url + "name/" + name;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Agregamos una categoria al sistema que le pasamos por parametro
		postCategory: function(category){
        
			return $http.post(url, category)
				.then(function (response) {
					return response.status;
				});

    	}, 
 
		// Actualizamos una categoria que le pasamos por parametro
    	putCategory: function(category){

			var newurl  = url + category.id;
			return $http.put(newurl, category)
				.then(function (response) {
					return response.status;
				});
    	},

		// Borramos una categoria que le pasamos su id por parametro
        deleteCategory : function(id){

			var newurl  = url + id;
			return $http.delete(newurl)
				.then(function (response) {
					return response.status;
				});
        	
        }	


    }
    return categoriesInterface;
}])