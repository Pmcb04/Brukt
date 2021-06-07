angular.module('BruktApp')
.controller('headerCtrl', ['usersFactory',function(usersFactory){
    var headerViewModel = this;
    headerViewModel.user={};
    headerViewModel.functions = {
      
      where : function(route){	
        return $location.path()==route   		
      },

      readUser : function() {
            
        // Complete this function
        usersFactory.getUser()
          .then(function (response) {
            headerViewModel.user = response
            console.log("Getting user whith id: ", headerViewModel.user.id, " Response ", response);
          }, function (response) {
            console.log("Error reading user data");
          })

      }
    }
	headerViewModel.functions.readUser();
}])
