(function () {
    'use strict';

    angular
        .module('app', ['ngRoute','ui.bootstrap'])
        .run(run);

    

    run.$inject = ['$rootScope', '$location', '$http'];
    function run($rootScope, $location, $http) {                      
    			
    }

    var app = angular.module('app');
    app.controller('forecastController', ForecastController);

    function ForecastController($scope, $rootScope, $http, $uibModal) {  
    	var vm = this; 
    	$scope.dscCidadeSelec = 'Selecione uma cidade';
    	$scope.exibeLoader = false;
    	
    	vm.carregar = function () {
	    	$http.post('rest/ws/listar')
	        .then(function(success) {
	            $scope.cities = 
	            	success.data.cidades            
	        });
    	}
    	vm.carregar();    	
    	
    	vm.carregaPrevisao = function (prmCidade) {
    		$scope.dscCidadeSelec = prmCidade;
    		$scope.retOkDia  = false;
    		$scope.retOkHora = false;
    		$scope.exibeLoader = true;
	    	$http.get('rest/ws/previsaoDia?cidade='+prmCidade)
	        .then(function(success) {	
	        	if(success.data.codigo == 0){
	        		$scope.forecast = success.data.retornoDias;
	        		$scope.retOkDia = true;
	        	}else{
	        		$scope.msgErroDia = success.data.msgErro;
	        	}
	        });
	    	$http.get('rest/ws/previsaoHora?cidade='+prmCidade)
	        .then(function(success) {	
	        	if(success.data.codigo == 0){
	        		$scope.hourly = success.data.retornoHoras;
	        		$scope.retOkHora = true;
	        	}else{
	        		$scope.msgErroHora = success.data.msgErro;
	        	}
	        });
    	}
    	
    	vm.salvar = function () {
    		$scope.cadErro = false;
    		$scope.cadSucesso = false;
    		$scope.cadVazio = false;
    		$scope.cadSalvando = true;
    		if($scope.dscCidade !== undefined && $scope.dscCidade !== ""){    	    		
	        	$http.post('rest/ws/salvar/'+$scope.dscCidade)
	            .then(function(success) {
	            	console.log(success);
	            	if (success.data.codigo == 0){
	            		$scope.dscCidade = '';                	
	                	vm.carregar();   
	                	$scope.cadSucesso = true;
	                	$scope.msgSucesso = success.data.mensagem;
	                	$scope.cadSalvando = false;
	            	} else {
	            		$scope.cadErro = true;
	                	$scope.msgErro = success.data.mensagem;
	                	$scope.cadSalvando = false;
	            	}
	            }); 
        	}else{
        		$scope.cadVazio = true;
        		$scope.cadSalvando = false;
        	}   
        };
        
        vm.limpaCampos = function () {
        	$scope.cadErro = false;
    		$scope.cadSucesso = false;
    		$scope.cadVazio = false;
    		$scope.cadSalvando = false;
    		$scope.dscCidade = '';    
    	}
    }
    
    app.directive('ngEnter', function () {
        return function (scope, element, attrs) {
            element.bind("keydown keypress", function (event) {
                if (event.which === 13) {
                    scope.$apply(function () {
                        scope.$eval(attrs.ngEnter);
                    });
                    event.preventDefault();
                }
            });
        };
    })
    	

})();

