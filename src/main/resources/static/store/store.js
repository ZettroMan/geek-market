angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function (page_no) {
        console.log('fill');
         $http({
             url: contextPath + '/api/v1/products',
             method: "GET",
             params: { p: page_no == null ? 1 : page_no,
                       s: $scope.filter == null ? '' : $scope.filter.page_size,
                       title: $scope.filter == null ? '' : $scope.filter.title,
                       min_price: $scope.filter == null ? '' : $scope.filter.min_price,
                       max_price: $scope.filter == null ? '' : $scope.filter.max_price}
         }).then(function (response) {
                $scope.products = response.data;
                var range = [];
                for(var i=1;i<=$scope.products.totalPages;i++) {
                  range.push(i);
                }
                $scope.range = range;
         });
    };

    $scope.resetFilter = function () {
        console.log('clear filter');
        $scope.filter = null;
        $scope.fillTable();
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                // $scope.Products.push(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});