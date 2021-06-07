angular.module('BruktApp', ['ngRoute'])
.config(function($routeProvider){
	$routeProvider

		.when("/", {
			controller: "indexCtrl",
    		controllerAs: "indexVM",
    		templateUrl: "../public/Index.html",
			resolve: {
    			// produce 500 miliseconds (0,5 seconds) of delay that should be enough to allow the server
    			//does any requested update before reading the orders.
    			// Extracted from script.js used as example on https://docs.angularjs.org/api/ngRoute/service/$route
    			delay: function($q, $timeout) {
    			var delay = $q.defer();
    			$timeout(delay.resolve, 500);    			
				var delay = $q.defer();
    			$timeout(delay.resolve, 500);
    			return delay.promise;
    			}
    		}
		})

		.when("/shop/:CATEGORY/:ORDER/:ESTADO/:MONEDA/:PRICE/:SEARCH/:PREMIUM", {
    		controller: "shopCtrl",
    		controllerAs: "shopVM",
    		templateUrl: "../public/Shop.html"
    	})

		.when("/shop/:CATEGORY/:ID_PROD", {
    		controller: "productPageCtrl",
    		controllerAs: "productPageVM",
    		templateUrl: "../public/Product-details.html"
    	})




    	.when("/user/:ID_USER", {
    		controller: "userPageCtrl",
    		controllerAs: "userPageVM",
    		templateUrl: "../private/UserTemplate.html"
    	})

		.when("/addProduct", {
    		controller: "productAddEditCtrl",
    		controllerAs: "productAddEditVM",
    		templateUrl: "../private/ProductTemplate.html"
    	})

		.when("/editProduct/:ID_PROD", {
			controller: "productAddEditCtrl",
    		controllerAs: "productAddEditVM",
    		templateUrl: "../private/ProductTemplate.html"
    	})

		.when("/deleteProduct/:ID_PROD", {
    		controller: "deleteCtrl",
    		controllerAs: "deleteVM",
    		templateUrl: "../private/CheckDeleteTemplate.html"
    	})

		.when("/deleteUser/:ID_USER", {
    		controller: "deleteCtrl",
    		controllerAs: "deleteVM",
    		templateUrl: "../private/CheckDeleteTemplate.html"
    	})

		.when("/deleteComment/:ID_PROD/:USERNAME", {
    		controller: "deleteCtrl",
    		controllerAs: "deleteVM",
    		templateUrl: "../private/CheckDeleteTemplate.html"
    	})

		.when("/404", {
			controller: "errorCtrl",
			controllerAs: "errorVM",
			templateUrl: "../error/NotFoundError.html"
		})
		.otherwise({
				redirectTo: "/404"
		});
})
