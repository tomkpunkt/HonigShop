var honigShopModul = angular.module('honigShopModul', []);

honigShopController = function ($scope, $http) {
    $scope.order = {}
    $scope.messages = [];

//            $scope.order = {"_id": "Bestand", "_rev": "3-2d2fa6c2f64f424c9b2811df5e9c9d50", "Sommerhonig_500": 200, "Sommerhonig_250": 600, "Fruehjahrshonig_500": 100, "Fruehjahrshonig_250": 400};
//    $http({url: "http://localhost:8080/abfrage/bestand",
    $http({url: "http://localhost:8080/bestand.json",
        method: "GET",
        cache: false,
        headers: {'Content-Type': 'application/json'},
        timeout: 10000
    })
        .success(function (bestand) {
            $scope.bestand = bestand;
            $scope.messages.push("Bestandsdaten geladen")
        })
        .error(function () {
            $scope.messages.push("Fehler beim Daten abholen")
        })

}

honigShopModul.filter('preisEuro', function() {
    return function(input) {
        input = Number(input);
        if (isNaN(input)) {
            throw 'invalid: can\'t convert input';
        }
        return (input / 100).toFixed(2) + " €";
    };
});

