/**
 * @typedef {Object} Product
 * @property {number} id - id produktu
 * @property {string} name - nazwa produktu
 * @property {number} quantity - ilosc produktu do zakupienia
 * @property {Date} date - do kiedy zakupic dany produkt
 * @property {boolean} status - czy juz zakupiono (true/false)
 * @property {number} [price] - (opcjonalna) cena produktu
 * 
 */

/**@type {Product[]} */
let productList = [];

/**
 * Dodaje nowy produkt do listy
 * @param {string} name -nazwa produktu
 * @param {number} quantity -ilosc produktu do zakupienia
 * @param {string} myDate -data do kiedy nalezy zakupic produkt
 */
function newProduct(name, quantity, myDate) {
    let id = Math.floor((Math.random() * 10000 + 1));
    let date = new Date(myDate);
    let status = false;
    productList.push({ id, name, quantity, date, status });
}
/**
 * usuwa produkt z listy (jeśli go na niej znajdzie)
 * @param {number} id - id produktu do usunięcia
 */
function delProduct(id) {
    let i = productList.findIndex(Product => Product.id === id);
    if (i !== -1)
        productList.splice(i, 1);
    else
        console.log("Nie znaleziono takiego produktu");
}
/**
 * zmienia nazwe produktu
 * @param {number} id -id produktu ktorego nazwe chcemy zmienic
 * @param {string} newName -nowa nazwa produktu
 */
function editName(id, newName) {
    let i = productList.findIndex(Product => Product.id === id);
    if (i !== -1)
        productList[i].name = newName;
    else
        console.log("Nie znaleziono takiego produktu");
}

/**
 * zmienia status produktu
 * @param {number} id -id produktu ktorego status chcemy zmienic
 * @param {boolean} newStatus -nowy status produktu
 */
function editStatus(id, newStatus) {
    let i = productList.findIndex(Product => Product.id === id);
    if (i !== -1)
        productList[i].status = newStatus;
    else
        console.log("Nie znaleziono takiego produktu");
}

/**
 * zmienia ilośc produktu
 * @param {number} id -id produktu ktorego ilosc chcemy zmienic
 * @param {number} newQuantity -nowa ilosc produktu
 */
function editQuantity(id, newQuantity) {
    let i = productList.findIndex(Product => Product.id === id);
    if (i !== -1)
        productList[i].quantity = newQuantity;
    else
        console.log("Nie znaleziono takiego produktu");
}

/**
 * zmienia date produktu
 * @param {number} id -id produktu ktorego date chcemy zmienic
 * @param {string} newDate -nowa data produktu
 */
function editDate(id, newDate) {
    let i = productList.findIndex(Product => Product.id === id);
    if (i !== -1)
        productList[i].date = new Date(newDate);
    else
        console.log("Nie znaleziono takiego produktu");
}

/**
 * zamienia produkty miejscami na liscie
 * @param {number} id1 -id produktu do podmiany
 * @param {number} id2 -drugi do podmiany
 */
function swapProducts(id1, id2) {
    let i = productList.findIndex(Product => Product.id === id1);
    let j = productList.findIndex(Product => Product.id === id2);

    if (i !== -1 && j !== -1) {
        let temp = productList[i];
        productList[i] = productList[j];
        productList[j] = temp;
    }
    else
        console.log("Nie znaleziono jednego(lub dwoch) z produktow");

}
/**
 * tworzy liste produktów które mają dzisiejszą datę
 * @returns lista produktow do kupienia dzisiaj
 */
function buyToday() {
    let currentDate = new Date().toDateString();
    let shoppingList = productList.filter(function (Product) { return Product.date.toDateString() === currentDate && Product.status === false });
    return shoppingList;
}

/**
 * wprowadza cene produktu jesli zostal juz zakupiony
 * @param {number} id - id produktu ktorego cena bedzie wprowadzana
 * @param {number} price -cena do wprowadzenia
 */
function enterPrice(id, price) {
    let i = productList.findIndex(Product => Product.id === id);
    if (i !== -1) {
        product = productList[i];
        if (product.status) product.price = price;
    }
}
/**
 * zlicza wydane pieniądze danego dnia
 * @param {string} date - data zakupów
 */
function costs(date) {
    let myDate = new Date(date).toDateString();
    let noPrice = 0;
    let sum = 0;
    productList.forEach(element => {
        if (element.date.toDateString() === myDate) {
            if (element.price === undefined && element.status === true) noPrice += 1;
            else if (element.status === true) sum += element.price;
        }
    });
    console.log("Koszt zakupów " + myDate + " : " + String(sum));
    if (noPrice > 0) console.log("(" + String(noPrice) + " zakupione produkty nie mają wpisanej ceny. Uzupełnij dane dla dokładniejszego wyniku!)\n");
}
/**
 *  Formatuje wybrane produkty na liście.
 * Zmienia tylko produkty, których ID znajduje się w przekazanej liście.
 * 
 * @param {number[]} idList - lista z id do sformatowania
 * @param {(idList: number[]) => number[]} fun - funkcja do zastosowania  
 */

function formatData(idList, fun) {
    productList = productList.map(function (product) { if (idList.includes(product.id)) { return fun(product) } else { return product } });
}

console.log("=== Dodawanie produktów ===");
newProduct("Chleb", 1, "2025-04-03");
newProduct("Mleko", 2, "2025-04-02");
newProduct("Masło", 1, "2025-04-02");
newProduct("Ser", 1, "2025-04-02");
newProduct("Jajka", 10, "2025-04-02");
console.table(productList);

console.log("=== Edycja nazwy ===");
let id1 = productList[0].id;
editName(id1, "Chleb pełnoziarnisty");
console.table(productList);

console.log("=== Edycja ilości ===");
editQuantity(id1, 3);
console.table(productList);

console.log("=== Edycja daty ===");
editDate(id1, "2025-04-05");
console.table(productList);

console.log("=== Edycja statusu ===");
editStatus(id1, true);
console.table(productList);



console.log("=== Przypisanie ceny ===");
enterPrice(id1, 5.99);
enterPrice(productList[1].id, 3.50);
enterPrice(productList[3].id, 4.20);
console.table(productList);

let id2 = productList[1].id;
enterPrice(id2, 30.34);

console.log("=== Usunięcie produktu ===");
let idToDelete = productList[2].id;
delProduct(idToDelete);
console.table(productList);

console.log("=== Zamiana miejscami ===");
swapProducts(id1, id2);
console.table(productList);

newProduct("Ser", 1, "2025-04-03");
newProduct("Szynka", 1, "2025-04-03");
newProduct("Arbuz", 1, "2025-04-02");
editStatus(productList[6].id, true);
console.table(productList);


console.log("=== Lista na dziś ===");
console.table(buyToday());




console.log("=== Koszt zakupów ===");
enterPrice(productList[6].id, 6.75);
editStatus(productList[0].id, true);
enterPrice(productList[0].id, 6.75);
editStatus(productList[2].id, true);
console.table(productList);

costs("2025-04-02");

console.log("=== Formatowanie danych: przewalutowanie ceny (PLN ---> EURO) ===");
/**
 * Przewalutowuje wszzsytkie produkty z listy na Euro (dziala jezeli wczesniej byly w zlotowkach)
 * @param {Product} obiekt - Produkt ktorego cene bedziemy zmieniac
 * @returns - zwraca produkt ze zmienioną ceną
 */
function przewalutowanieEuro(obiekt) {
    if (obiekt.status !== false) {
        obiekt.price /= 4.18;
        obiekt.price = parseFloat(obiekt.price.toFixed(2));
        return obiekt;
    }
}
formatData([productList[0].id, productList[1].id], przewalutowanieEuro)
console.table(productList);