// Simple AngularJS model
var myApp = angular.module('app', ['ui.bootstrap'])
    .controller('HelloWorldController', function ($scope, $http ) {
        $scope.fruits = [
            {name: "Kiwi"},
            {name: "Pear"},
            {name: "Strawberry"}
        ];

        $scope.name = "World"

        // Simple AngularJS Javascript function
        $scope.hello = function( name ){
            alert("Hello name=["+name+"]");
        }
    });


