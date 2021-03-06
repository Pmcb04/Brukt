angular.module('BruktApp')
.factory('commentsFactory',['$http', function($http){
	var url = 'https://localhost:8443/Brukt/rest/comments/';
    var commentsInterface = {

		// Obtenemos todos los comentarios del sistema
    	getComments : function(){

			return $http.get(url)
				.then(function (response) {
					return response.data;
				});
    	
    	},

		// Obtenemos todos los comentarios de un producto por su id que le pasamos por parametro
    	getCommentProductID : function(id){

    		var newurl = url + "product/" + id;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos el comentario que hizo un usuario de un producto por su id pasado por parametro
    	getCommentProductIDUserUsername : function(id, username){

    		var newurl = url + "product/" + id + "/user/" + username;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos los comentarios que tiene un producto con un determinado ranting pasado por parametro
    	getCommentProductIDRating : function(id, rating){

    		var newurl = url + "product/" + id + "/rating/" + rating;
            return $http.get(newurl)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Agregamos un comentario al sistema que le pasamos por parametro
		postComment: function(comment){
        
			return $http.post(url, comment)
				.then(function (response) {
					return response.status;
				});

    	}, 

		// Borramos un comentario que le pasamos su id por parametro
        deleteComment : function(idp, username){

			var newurl = url + "product/" + idp + "/user/" + username;
			return $http.delete(newurl)
				.then(function (response) {
					return response.status;
				});
        	
        }	


    }
    return commentsInterface;
}])