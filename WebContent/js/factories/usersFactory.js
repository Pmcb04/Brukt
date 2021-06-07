angular.module('BruktApp')
.factory('usersFactory',['$http', function($http){
	var url = 'https://localhost:8443/Brukt/rest/users/';
    var usersInterface = {

		// Obtenemos todos los usuarios del sistema
    	getUser : function(){

			return $http.get(url)
				.then(function (response) {
					return response.data;
				});
    	
    	},

		// Obtenemos el usuario que le pasemos el ID por paramtro
    	getUserID : function(id){

    		var newurl = url + "id/" + id;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},
		
		// Obtenemos el usuario que le pasemos el Username por parametro
		getUserUsername : function(username){
			
    		var newurl = url + "username/" +username;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Agregamos un usuario al sistema que le pasamos por parametro
		postUser: function(user){
        
			return $http.post(url, user)
				.then(function (response) {
					return response.status;
				});

    	}, 
 
		// Actualizamos un usuario que le pasamos por parametro
    	putUser: function(user){

			var newurl = url + user.id;
			return $http.put(newurl, user)
				.then(function (response) {
					return response.status;
				});
    	},

		// Borramos un usuario que le pasamos su id por parametro
        deleteUser : function(id){

			var newurl = url + id;
			return $http.delete(newurl)
				.then(function (response) {
					return response.status;
				});
        	
        }	


    }
    return usersInterface;
}])