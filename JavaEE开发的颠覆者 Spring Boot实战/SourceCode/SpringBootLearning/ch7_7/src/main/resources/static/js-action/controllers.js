/**
 * 
 */

//1定义控制器View1Controller，并注入$rootScope、$scope和$http
actionApp.controller('View1Controller', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
	//2使用$scope.$on监听$viewContentLoaded事件，可以在页面内容加载完成后进行一些操作
	$scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
	//3这段代码是这个演示的核心代码，请结合下面的Viewl的界面一起理解
    
    $scope.search = function(){ //3.1在scope内定义一个方法search，在页面上通过ng-click调用
      personName = $scope.personName; //3.2通过$scope.personName获取页面定义的ng-model=“personName”的值
      $http.get('search',{ //3.3使用$http.get向服务端地址search发送get请求
    	  params:{personName:personName} //3.4使用params增加请求参数
      }).success(function(data){ //3.5用success方法作为请求成功后的回调
    	 $scope.person=data; //3.6将服务端返回的数据data通过$scope.person赋给模型person，这样页面视图上可以通过{{person.name}}、{{person.age}}、{{person.address}}来调用，且模型person值改变后，视图是自动更新的
      });;
     
    };
}]);

actionApp.controller('View2Controller', ['$rootScope', '$scope',  function($rootScope, $scope) {
    $scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
}]);


