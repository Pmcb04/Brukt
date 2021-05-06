angular.module('pizzeriaApp')
.factory("ordersFactory", ['$http',function($http){
	var url = 'https://localhost:8443/sl09_inlab_E1/rest/orders/';
    var ordersInterface = {
    	getOrders: function(){
    	
			return $http.get(url)
				.then(function (response) {
					return response.data;
				});
    	
    	},
    	getOrder : function(id){

			var urlid = url + id;
			return $http.get(urlid)
				.then(function (response) {
					return response.data;
				});
    	
    	},
    	putOrder : function(order){

			var urlid = url + order.id;
			return $http.put(urlid, order)
				.then(function (response) {
					return response.status;
				});
    	},
    	postOrder:  function(order){
        
			return $http.post(url, order)
				.then(function (response) {
					return response.status;
				});

    	}, 
        deleteOrder : function(id){

			var urlid = url + id;
			return $http.delete(urlid)
				.then(function (response) {
					return response.status;
				});
        	
        }				  
    }
    return ordersInterface;
}])