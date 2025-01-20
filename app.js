// Define AngularJS Module
var app = angular.module('movieApp', []);

// Define Custom Service for Rental Price Calculation
app.service('RentalService', function() {
  this.calculateRentalPrice = function(year) {
    var currentYear = new Date().getFullYear();
    if (currentYear - year <= 3) {
      return 5; // Higher price for recent movies
    } else {
      return 3; // Discounted price for older movies
    }
  };
});

// Define Controller
app.controller('MovieController', ['$scope', 'RentalService', function($scope, RentalService) {
  // Movie Dataset
  $scope.movies = [
    { title: 'Inception', genre: 'Sci-Fi', year: 2010 },
    { title: 'Parasite', genre: 'Drama', year: 2019 },
    { title: 'The Dark Knight', genre: 'Action', year: 2008 },
    { title: 'Avengers: Endgame', genre: 'Action', year: 2019 },
    { title: 'Interstellar', genre: 'Sci-Fi', year: 2014 }
  ];

  // Function to Calculate Rental Price
  $scope.calculateRentalPrice = function(year) {
    return RentalService.calculateRentalPrice(year);
  };

  // Function to Add New Movie
  $scope.addMovie = function() {
    // Check if title is unique
    var isUnique = true;
    for (var i = 0; i < $scope.movies.length; i++) {
      if ($scope.movies[i].title === $scope.newMovie.title) {
        isUnique = false;
        alert("Movie title must be unique.");
        break;
      }
    }

    // Add new movie if title is unique
    if (isUnique) {
      $scope.movies.push({
        title: $scope.newMovie.title,
        genre: $scope.newMovie.genre,
        year: $scope.newMovie.year
      });

      // Reset the new movie form
      $scope.newMovie = {};
    }
  };
}]);
