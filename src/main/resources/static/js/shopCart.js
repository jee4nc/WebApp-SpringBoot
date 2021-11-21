let shopList = []
const itemShop = {
    name: "",
    price: 0,
}
function hola(nameInput, priceInput) {
    let newProduct = {
        name:nameInput,
        price:priceInput
    }
    shopList.push(newProduct)
    console.log(shopList)
}
