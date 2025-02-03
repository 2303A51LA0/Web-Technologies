var app = angular.module("weatherApp", []);

app.controller("WeatherController", function($scope, $http) {
    var apiKey = "aa6c4f40f70c6c9f9f30268389b48e07";

    $scope.getWeather = function() {
        var cityName = $scope.city;
        var url = `https://api.openweathermap.org/data/2.5/weather?q=${cityName}&units=metric&appid=${apiKey}`;

        $http.get(url)
            .then(function(response) {
                $scope.weatherData = response.data;
                $scope.errorMessage = "";
            })
            .catch(function(error) {
                console.log("Error:", error);
                $scope.weatherData = null;
                $scope.errorMessage = "City not found or API issue.";
            });
    };
});
