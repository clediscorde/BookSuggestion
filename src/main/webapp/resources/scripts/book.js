var bookSuggestionApp = angular.module('bookSuggestionApp', []);

bookSuggestionApp.controller('SuggestBookController', ['$scope', '$http', function ($scope, $http) {

    var initAuthor = function () {
        $http.get('/books/authors')
            .then(function (response) {
                $scope.authors = response.data;
            }, function (err) {
                $scope.authors = response.data | "Error when trying to retrieve authors."
            });
    };

    var initGenre = function () {
        $http.get('/books/genres')
            .then(function (response) {
                $scope.genres = response.data;
            }, function (err) {
                $scope.genres = response.data | "Error when trying to retrieve authors."
            });
    };

    initAuthor();
    initGenre();

    $scope.suggestBook = function () {
        $http.get('/books/search', {
            params: {
                "author": $scope.selectAuthor,
                "genre": $scope.selectGenre,
                "numberOfPageMin": $scope.inputMinPage,
                "numberOfPageMax": $scope.inputMaxPage,
                "period": $scope.selectPeriod
            }
        }).then(function (response) {
            $scope.books = response.data;
        }, function (err) {
            $scope.books = response.data | "Error when trying to retrieve authors."
        });
    }
}]);

