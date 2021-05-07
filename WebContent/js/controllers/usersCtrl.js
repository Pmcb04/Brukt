angular.module('') // TODO : poner nombre al modulo
.controller('usersCtrl', ['usersFactory','$location','$routeParams',
                    function(usersFactory,$location, $routeParams){
    var userViewModel = this;
    userViewModel.user={};
    userViewModel.functions = {

        // Leemos un usuario con el identificador id que pasamos por parametro
		readUserID : function(id) {

			usersFactory.getUserID(id)
				.then(function (response) {
					console.log("Reading user with id: ", id, " Response: ", response);
					userViewModel.user = response;
				}), function(response) {
					console.log("Error reading user");
					$location.path('/');
				}		
			
		},

        // Leemos un usuario con el nombre username que pasamos por parametro
        readUserUsernameName : function(username) {

			usersFactory.getUserUsername(username)
				.then(function (response) {
					console.log("Reading user with username: ", username, " Response: ", response);
					userViewModel.user = response;
				}), function(response) {
					console.log("Error reading user");
					$location.path('/');
				}		
			
		},

        // Creamos una nueva categoria que previamente hemos depositado en el controlador
		createUser : function() {

	        usersFactory.postUser(userViewModel.user)
				.then(function (response) {
					console.log("Creating user. Response: ", response)
				}), function (response) {
					console.log("Error creating the user");
				}
			
		},

        // Actualizamos una categoria que previamente hemos depositado en el controlador
		updateUser : function() {

			usersFactory.putUser(userViewModel.user)
				.then(function (response) {
					console.log("Updating user with id ", userViewModel.user.id, " Response: ", response);
				}), function (response) {
					console.log("Error updating user");
				}
		},	

        // Borramos una categoria con el identificador id que pasamos por parametro
		deleteUser: function(id) {

			usersFactory.deleteUser(id)
				.then(function (response) {
					console.log("Deleting user with id ", id, " Response: ", response);
				}), function (response) {
					console.log("Error deleting order");
				}
		}

		// TODO ¿hace falta?
        // Metodo para saber que acccion realizar segun la ruta del navegador
        // userSwitcher : function(){	

		// 	if(userViewModel.functions.where('/insertUser')){
		// 		console.log($location.path());
		// 		userViewModel.functions.createuser();
		// 	}
		// 	else if (userViewModel.functions.where('/editUser/' + userViewModel.user.id)){
		// 		console.log($location.path());
		// 		userViewModel.functions.updateuser();
		// 	}
		// 	else if(userViewModel.functions.where('/deleteUser/' + userViewModel.user.id)){
		// 		console.log($location.path());
		// 		userViewModel.functions.deleteuser();
		// 	}
		// 	else{
		// 		console.log($location.path());
		// 	}
			
		// 	$location.path('/');
		// }
    }

	console.log("Entering usersCtrl with $routeParams.ID=",$routeParams.ID);

    // TODO mirar si hace falta
    /*
   	if ($routeParams.ID==undefined) userViewModel.functions.createuser();
   	else userViewModel.functions.readOrder($routeParams.ID);*/
}])