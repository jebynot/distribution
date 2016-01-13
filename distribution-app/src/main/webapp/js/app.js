var App = angular.module('App', []);
(function() {
  'use strict';

  App.controller("ARICtrl", ['$scope', '$http',
    function($scope, $http) {
      $scope.name = "User";
      $scope.message = "";

      $scope.getName = function() {
        $http.get('ari/' + $scope.name).then(function(response) {
          $scope.message = "ARI " + response.data;
        });
      };

    }
  ]);


})();