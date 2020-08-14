/**
 * 
 */

var actionApp = angular.module('actionApp',['ngRoute']); //1定义模块actionApp，并依赖于路由模块ngRout

actionApp.config(['$routeProvider' , function($routeProvider) { //2配置路由，并注入$routeProvider来配置
	
	$routeProvider.when('/oper', { //3/oper为路由名称
		controller: 'View1Controller', //4controller定义的是路由的控制器名称
		templateUrl: 'views/view1.html', //5templateUrl定义的是视图的真正地址
	}).when('/directive', {
		controller: 'View2Controller',
		templateUrl: 'views/view2.html',
	});

}]);
