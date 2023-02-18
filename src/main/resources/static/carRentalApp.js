(function () {
  'use strict';
  angular
      .module('CarRentalApp',['ngMaterial', 'ngMessages'])
      .controller('CarRentalSubmitCtrl', ['$scope', '$http', function($scope, $http) {
        $scope.carRental = function () {
            console.log('carStock: ' + $scope.carStock + ' carStock for ' + $scope.ctrl.selectedItem.value);

            var carRentalPayload = {
				id : 1,
				carModel : $scope.ctrl.selectedItem.value,
                carStock : $scope.carStock
            }

            var res = $http.put('http://localhost:8081/carRental/admin/addCar',carRentalPayload);
            
            res.success(function(data, status, headers, config) {
                $scope.message = data;
            });
            
            res.error(function(data, status, headers, config) {
                alert( "failure message: " + JSON.stringify({data: data}));
            });		
        }
      }])
      .controller('CarRentalCtrl', CarRentalCtrl);

  function CarRentalCtrl ($timeout, $q, $log, $http) {
    var self = this;

    self.allCars = null;
    self.simulateQuery = false;
    self.isDisabled    = false;

    self.querySearch   = querySearch;
    self.selectedItemChange = selectedItemChange;
    self.searchTextChange   = searchTextChange;

    // ******************************
    // Internal methods
    // ******************************

    function querySearch (query) {
        if (self.allCars == null) {
            self.allCars = [];
            $http.get('http://localhost:8081/carRental/public/getCars')
            .then(function (cars) {
                for (var i=0; i<cars.data.length; i++) {
                    var car = cars.data[i];
                    var f = {value: car.id, display: angular.uppercase(car.carModel) +  ' (' + car.carStock + ')', valueForSearch: angular.lowercase(car.carModel) }
                    self.allCars.push(f);
                }

                return self.allCars;
            });
        }

        var results = query ? self.allCars.filter( createFilterFor(query) ) : self.allCars,
          deferred;
        if (self.simulateQuery) {
            deferred = $q.defer();
            $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
            return deferred.promise;
        } else {
            return results;
        }
    }

    function searchTextChange(text) {
      $log.info('Text changed to ' + text);
    }

    function selectedItemChange(item) {
      $log.info('Item changed to ' + JSON.stringify(item));
    }

    /**
     * Create filter function for a query string
     */
    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);

      return function filterFn(food) {
        return (food.valueForSearch.indexOf(lowercaseQuery) === 0);
      };

    }
  }
})();