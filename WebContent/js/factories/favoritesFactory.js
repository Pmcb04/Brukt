angular.module('BruktApp')
.factory('favoritesFactory',['$http', function($http){
	var url = 'https://localhost:8443/Brukt/rest/favorites/';
    var favoritesInterface = {

		// Obtenemos todos los favoritos del sistema
    	getFavorites : function(){

			return $http.get(url)
				.then(function (response) {
					return response.data;
				});
    	
    	},

		// Obtenemos los favoritos que ha dado un usuario en concreto que pasamos por parametro
    	getFavoritesUserID : function(id){

    		var newurl = url + "user/" + id;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos la lista de usuarios que ha dado favorito a un producto en concreto pasado por parametro
    	getFavoritesProductID : function(id){

    		var newurl = url + "product/" + id;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Agregamos un favorito al sistema que le pasamos por parametro
		postFavorite: function(favorite){
        
			return $http.post(url, favorite)
				.then(function (response) {
					return response.status;
				});

    	}, 

		// Borramos un favorito que le pasamos el id del usuario que le habia dado, asi como el id del producto que queremos quitar el favorito
        deleteFavorite : function(idu, idp){

			var newurl = url + "user/" + idu + "/product/" + idp;
			return $http.delete(newurl)
				.then(function (response) {
					return response.status;
				});
        	
        }	


    }
    return favoritesInterface;
}])