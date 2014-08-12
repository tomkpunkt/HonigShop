var honigShopModul = angular.module('honigShopModul', []);

honigShopController = function ($scope, $http) {
    $scope.bestellung = {
        einzelPosten: []
    }
    $scope.messages = [];

    $http({url: "bestand",
//    $http({url: "http://localhost:8080/bestand.json",
        method: "GET",
        cache: false,
        headers: {'Content-Type': 'application/json'},
        timeout: 10000
    })
        .success(function (bestand) {
            $scope.bestand = bestand;
            $.each(bestand, function (i, artikel) {
                $scope.bestellung.einzelPosten.push({artikelId: artikel.id})
            });
            $scope.messages.push("Bestandsdaten geladen")
        })
        .error(function () {
            $scope.messages.push("Fehler beim Daten abholen")
        })
    $scope.bestellen = function () {
        $http({url: "bestellung",
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            data: JSON.stringify($scope.bestellung)})

            .success(function (bestand) {
                $scope.messages.push("Erfolgreich gesendet")
            })
            .error(function () {
                $scope.messages.push("Fehler beim Senden")
            })
    }
}

gesamtPreisController = function ($scope) {
    $scope.gesamtpreis = 0;
    $scope.change = function () {
        for (var posten in $scope.bestellung.einzelPosten) {
            $scope.gesamtpreis += posten.anzahl * posten.preisInCent;
        }
    };
}

honigShopModul.filter('preisEuro', function () {
    return function (input) {
        input = Number(input);
        if (isNaN(input)) {
            throw 'invalid: can\'t convert input';
        }
        return (input / 100).toFixed(2) + " €";
    };
});

