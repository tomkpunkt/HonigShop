var honigShopModul = angular.module('honigShopModul', []);

honigShopController = function ($scope, $http) {
  $scope.bestellung = {
    einzelPosten: [],
    gesamtpreis: 0
  }

  $scope.messages = [];

  $scope.$watch('bestellung', function (newBestellung) {
    $scope.bestellung.gesamtpreis = 0;
    $.each(newBestellung.einzelPosten, function (i, posten) {
      $scope.bestellung.gesamtpreis += posten.getGesamtPreis();
    })
  }, true)//deep watch

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
                   $scope.bestellung.einzelPosten.push(
                       {artikelId: artikel.id,
                         anzahl: 0,
                         getGesamtPreis: function () {
                           return this.anzahl * artikel.preis;
                         }
                       }
                   )
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

        .success(function (bestellnummer) {
                   $scope.messages.push("Erfolgreich gesendet")
                   $scope.bestellnummer = bestellnummer;
                 })
        .error(function () {
                 $scope.messages.push("Fehler beim Senden")
               })
  }
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

