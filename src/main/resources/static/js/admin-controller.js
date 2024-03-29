var honigShopAdminModul = angular.module('honigShopAdminModul', []);

var honigShopAdminController = function ($scope, $http, $interval) {
  $scope.bestellungen = []

  $scope.messages = [];

  ladeDaten()

  $scope.sekundenBisAktualisierung = 60;
  $interval(function () {
    $scope.sekundenBisAktualisierung--;
    if ($scope.sekundenBisAktualisierung == 0) {
      $scope.sekundenBisAktualisierung = 60;
      ladeDaten();
    }
  }, 1000);

  function ladeDaten() {
    $http({url: "../bestellung",
            method: "GET"})
      .success(function (response) {
                 $scope.messages.push("Erfolgreich empfangen")
                 $scope.bestellungen = response;
               })
      .error(function () {
               $scope.messages.push("Fehler beim empfangen")
             });

  }

  $scope.setzeAufBezahlt = function (bestellung) {
    $http({url: "../bestellung/" + bestellung.id + "/bezahlt",
            method: "PUT"})
      .success(function (aktualisierteBestellung) {
                 $scope.messages.push("Erfolgreich gesendet")
                 var index = $scope.bestellungen.indexOf(bestellung);
                 $scope.bestellungen[index] = aktualisierteBestellung;

               })
      .error(function () {
               $scope.messages.push("Fehler beim senden")
             });
  }

  $scope.setzeAufEmailVerschickt = function (bestellung) {
    $http({url: "../bestellung/" + bestellung.id + "/emailVerschickt",
            method: "PUT"})
      .success(function (aktualisierteBestellung) {
                 $scope.messages.push("Erfolgreich gesendet")
                 var index = $scope.bestellungen.indexOf(bestellung);
                 $scope.bestellungen[index] = aktualisierteBestellung;

               })
      .error(function () {
               $scope.messages.push("Fehler beim senden")
             });
  }
}

honigShopAdminModul.filter('preisEuro', function () {
  return function (input) {
    input = Number(input);
    if (isNaN(input)) {
      throw 'invalid: can\'t convert input';
    }
    return (input / 100).toFixed(2) + " €";
  };
});

honigShopAdminModul.filter('postenFilter', function () {
  return function (postenArrays) {
    var result = "";
    $.each(postenArrays, function (index, posten) {
      result = result + posten.anzahl + "x " + posten.artikelId + "\n";
    })
    return result;
  };
});


