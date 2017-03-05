var phonecatApp = angular.module('myApp', ['ngRoute', 'myControllers']);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/coffees', {
        templateUrl: 'partials/coffees.html',
        controller: 'CoffeesCtrl'
      }).
      when('/coffees/:coffeeId', {
        templateUrl: 'partials/reviews.html',
        controller: 'ReviewsCtrl'
      }).
      otherwise({
        redirectTo: '/coffees'
      });
  }]);
