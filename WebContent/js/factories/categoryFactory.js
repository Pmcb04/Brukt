angular.module('') // TODO : poner nombre al modulo
.factory('usersFactory',['$http', function($http){
	var url = 'https://localhost:8443/Brukt/rest/categories/';
    var usersInterface = {

		// Obtenemos todas las categorias del sistema
    	getCategories : function(){

			return $http.get(url)
				.then(function (response) {
					return response.data;
				});
    	
    	},

		// Obtenemos la categoria que le pasemos el ID por paramtro
    	getCategoryID : function(id){

    		url = url + "id/" + id;
            return $http.get(url)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos la categoria que le pasemos el name por paramtro
    	getCategoryName : function(name){
			
    		url = url + "name/" + name;
            return $http.get(url)
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

			url = url + category.id;
			return $http.put(url, category)
				.then(function (response) {
					return response.status;
				});
    	},

		// Borramos una categoria que le pasamos su id por parametro
        deleteCategory : function(id){

			url = url + id;
			return $http.delete(url)
				.then(function (response) {
					return response.status;
				});
        	
        }	


    }
    return usersInterface;
}])