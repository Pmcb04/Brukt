angular.module('BruktApp')
.controller('shopCtrl', ['categoriesFactory','productsFactory', 'favoritesFactory', 'commentsFactory', 'usersFactory', '$routeParams', function(categoriesFactory, productsFactory, favoritesFactory, commentsFactory, usersFactory, $routeParams){
    var shopViewModel = this;
    shopViewModel.category={};
    shopViewModel.nameCategory={};
    shopViewModel.order={};
    shopViewModel.estado={};
    shopViewModel.moneda={};
    shopViewModel.price={};
    shopViewModel.search={};
    shopViewModel.premium={};

    shopViewModel.userLog={};

    shopViewModel.shopProds=[];
    shopViewModel.categories={};
    shopViewModel.favoritesUser=[];


    shopViewModel.functions = {
		
        where : function(route){	
			return $location.path()==route   		
   		},


        readUser : function() {
        
        // Complete this function
            usersFactory.getUser()
            .then(function (response) {
                shopViewModel.userLog = response
                console.log("Getting user whith id: ", shopViewModel.userLog.id, " Response ", response);
                if(shopViewModel.userLog.id != undefined) shopViewModel.functions.readFavoritesUser(shopViewModel.userLog.id);
            }, function (response) {
                console.log("Error reading user data");
            })

        },

        
        // Leemos los favoritos del usuario con el identificador id que pasamos por parametro
		readFavoritesUser : function(id) {

			favoritesFactory.getFavoritesUserID(id)
				.then(function (response) {
					console.log("Reading favorites user with id: ", id, " Response: ", response);
					shopViewModel.favoritesUser = response;
				}), function(response) {
					console.log("Error reading favorite");
					$location.path('/');
				}		
			
		},

        // Leemos los favoritos del producto con el identificador id que pasamos por parametro
        readFavoritesProductID : function(prod) {

            favoritesFactory.getFavoritesProductID(prod.id)
                .then(function (response) {
                    console.log("Reading favorites product with id: ", prod.id, " Response: ", response);
                    prod.favorites = response.length;
                    prod.favorite_user = false;

                    for (let i = 0; i < shopViewModel.favoritesUser.length; i++) {
                        if(shopViewModel.favoritesUser[i].id === prod.id) prod.favorite_user = true;
                    }

                }), function(response) {
                    console.log("Error reading favorite");
                    $location.path('/');
                }		
            
        },

        // Leemos una comentario con el identificador id que pasamos por parametro
		readCommentProductID : function(prod) {

			commentsFactory.getCommentProductID(prod.id)
				.then(function (response) {
					console.log("Reading comment with id: ", prod.id, " Response: ", response);
                    prod.comments = response.length;
				}), function(response) {
					console.log("Error reading comment");
					$location.path('/');
				}		
			
		},
        
        // Leemos los productos de una categoria con el identificador id que pasamos por parametro
		shopProducts : function(id) {

            if(shopViewModel.search == 'no'){


                productsFactory.getCategory(id)
				.then(function (response) {
					console.log("Reading products category with id: ", id, " Response: ", response);
					
                    // recogemos todos los productos de una determinada categoria
                    shopViewModel.shopProds = response;

                    // filtramos por estado del producto
                    if(shopViewModel.estado != 'All'){
                        if(shopViewModel.estado == 'EnVenta')
                            shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.soldout == 0});
                        else if (shopViewModel.estado == 'Vendidos')
                            shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.soldout == 1});
                    }

                    // filtramos por modeda del productos
                    if(shopViewModel.moneda != 'All'){

                        switch (shopViewModel.moneda) {
                            case "euro":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.currency == '€'});
                                break;
                            case "dolar":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.currency == '$'});
                            break;
                            case "pound":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.currency == '£'});
                            break;
                            case "yen":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.currency == '¥'});
                            break;
                        
                            default:
                                break;
                        }

                    }

                    // filtramos por precio del producto
                    if(shopViewModel.price != 'All'){

                        switch (shopViewModel.price) {
                            case "1":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.price >= 0 && prod.price <= 50});
                                break;
                            case "2":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.price >= 50 && prod.price <= 100});
                            break;
                            case "3":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.price >= 100 && prod.price <= 500});
                            break;
                            case "4":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.price >= 500});
                            break;
                        
                            default:
                                break;
                        }

                    }


                    // filtramos por productos premium
                    if(shopViewModel.premium != 'All'){

                        switch (shopViewModel.premium) {
                            case "si":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.premium === "Si"});
                                break;
                            case "no":
                                shopViewModel.shopProds = shopViewModel.shopProds.filter( function (prod){return prod.premium === "No"});
                            break;
                        
                            default:
                                break;
                        }

                    }

                    shopViewModel.shopProds.forEach(prod => {
                        shopViewModel.functions.readCommentProductID(prod);
                        shopViewModel.functions.readFavoritesProductID(prod);
                    });

                    console.log(shopViewModel.shopProds);

				}), function(response) {
					console.log("Error reading product");
					$location.path('/');
				}	

            }else{
                shopViewModel.functions.readProductSearchAll(shopViewModel.search);
            }
	
			
		},

        // Leemos los productos donde su titulo o descripcion contenga con search que pasamos por parametro
        readProductSearchAll : function(search) {

            productsFactory.getProductSearchAll(search)
                .then(function (response) {
                    console.log("Reading product with search title and search description: ", search, " Response: ", response);
                    shopViewModel.shopProds = response;
                }), function(response) {
                    console.log("Error reading product");
                    $location.path('/');
                }		
            
        },

        // Leemos todas las categorias que se encuentran disponibles
		readCategories: function () {
			categoriesFactory.getCategories()
				.then(function (response) {
					console.log("Reading categories Response: ", response);
					shopViewModel.categories = response;
				}), function(response) {
					console.log("Error reading category");
					$location.path('/');
				}		
			
		},

        // Leemos una categoria con el identificador id que pasamos por parametro
		readCategoryID : function(id) {

			categoriesFactory.getCategoryID(id)
				.then(function (response) {
					console.log("Reading category with id: ", id, " Response: ", response);
					shopViewModel.nameCategory = response.name;
				}), function(response) {
					console.log("Error reading category");
					$location.path('/');
				}		
			
		},
    
    }

    console.log("Entering shopCtrl with $routeParams.CAETGORY=",$routeParams.CATEGORY
                                    , "$routeParams.ORDER=",$routeParams.ORDER
                                    , "$routeParams.ESTADO=",$routeParams.ESTADO
                                    , "$routeParams.MONEDA=",$routeParams.MONEDA
                                    , "$routeParams.PRICE=",$routeParams.PRICE
                                    , "$routeParams.PREMIUM=",$routeParams.PREMIUM
                                    , "$routeParams.SEARCH=",$routeParams.SEARCH);

    shopViewModel.category=$routeParams.CATEGORY;
    shopViewModel.order=$routeParams.ORDER;
    shopViewModel.estado=$routeParams.ESTADO;
    shopViewModel.moneda=$routeParams.MONEDA;
    shopViewModel.price=$routeParams.PRICE;
    shopViewModel.search=$routeParams.SEARCH;
    shopViewModel.premium=$routeParams.PREMIUM;

    shopViewModel.functions.readUser();
    shopViewModel.functions.shopProducts($routeParams.CATEGORY);
    shopViewModel.functions.readCategoryID($routeParams.CATEGORY);
    shopViewModel.functions.readCategories();
    

}])