function euro(amount){
    amount = Number(amount);
    if (isNaN(amount)){
        throw 'invalid: can\'t convert input';
    }
    return (amount/100).toFixed(2) + " €";
}