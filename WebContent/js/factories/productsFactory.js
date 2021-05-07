angular.module('') // TODO : poner nombre al modulo
.factory('productsFactory',['$http', function($http){
	var url = 'https://localhost:8443/Brukt/rest/products/';
    var productsInterface = {

		// Obtenemos todos los productos del sistema
    	getProducts : function(){

			return $http.get(url)
				.then(function (response) {
					return response.data;
				});
    	
    	},

		// Obtenemos el producto que le pasemos el ID por paramtro
    	getProductID: function(id){

    		url = url + "product/" + id;
            return $http.get(url)
              	.then(function(response){
        			 return response.data;
               	});
    	},
		
		// Obtenemos la lista de productos que coinciden en titulo con el pasado por parametro
		getProductSearchTitle : function(searchTitle){

    		url = url + "search/title/" +searchTitle;
            return $http.get(url)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos la lista de productos que coinciden en descripción con el pasado por parametro
		getProductSearchDescription : function(searchDescription){

			url = url + "search/description/" +searchDescription;
			return $http.get(url)
					.then(function(response){
						return response.data;
					});
		},

		// Obtenemos la lista de productos que coinciden en titulo y descripcion con el pasado por parametro
		getProductSearchAll : function(searchAll){

    		url = url + "search/all/" +searchAll;
            return $http.get(url)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos la lista de productos de un usuario en concreto pasado por parametro
		getProductsUser : function(userid){
			
    		url = url + "user/" +userid;
            return $http.get(url)
              	.then(function(response){
        			 return response.data;
               	});
    	},

		// Obtenemos la lista de productos que estan vendidos (sold = 1) o no (sold = 1) de un usuario en concreto pasado por parametro
		getUserSold : function(userid, sold){

			url = url + "user/" + userid + "/sold/" + sold ;
			return $http.get(url)
					.then(function(response){
						return response.data;
					});
		},



		// Obtenemos la lista de productos que pertenecen a una categoria en concreto o tienen el mismo precion pasado por parametro
		getCategoryPrice : function(categoryid, price){

			url = url + "category/" + categoryid + "/price/" + price ;
			return $http.get(url)
					.then(function(response){
						return response.data;
					});
		},


		// Obtenemos la lista de productos que pertenecen a una categoria en concreto pasado por parametro
		getCategory : function(categoryid){
	
			url = url + "category/" +categoryid;
			return $http.get(url)
					.then(function(response){
						return response.data;
					});
		},


		// Agregamos un producto al sistema que le pasamos por parametro
		postProduct: function(product){
        
			return $http.post(url, product)
				.then(function (response) {
					return response.status;
				});

    	}, 
 
		// Actualizamos un producto que le pasamos por parametro
    	putProduct: function(product){

			url = url + product.id;
			return $http.put(url, product)
				.then(function (response) {
					return response.status;
				});
    	},

		// Borramos un producto que le pasamos su id por parametro
        deleteProduct : function(id){

			url = url + id;
			return $http.delete(url)
				.then(function (response) {
					return response.status;
				});
        	
        }	


    }
    return productsInterface;
}])