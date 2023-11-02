import java.util.List;
//методи
//        - - методи за записване на продуктите - в името да има timestamp (точното време на записване на документа)
//        - - метод за зареждане на продуктите
//        – Методи на клиент
//        –void printAvailableProducts()
//        –void searchProductsByCategory(String category)
//        –void searchProductsByName(String name)
//        –void addToShoppingCart(int productId, int quantity)
//        –double calculateTotalPrice()
//        –void checkout() - изчиства потребителската количка и записва данните от покупката в timestamp .CSV файл - нещо като касова бележка за потребителя.
//        – Методи на служител
//        – void printAllProducts()
//        –  void sortProductsByName()
//        — void sortProductsByPrice()
//        – void sortProductsByExpirationDate()
//        – void printProductById(int productId)
//        — void printProductByName(String productName)
//        — void printProductsByPriceGreaterThan(double price)
//        –void printProductsByPriceLessThan(double price)
//        – void printProductsByQuantityGreaterThanOrEqual(int quantity)
//        – void printProductsByQuantityLessThan(int quantity)
//        – Product createProduct(int product_id, String name, double price, int quantity, String type, String color, String expires_in) - може да се добави валидация на данните.
//        –void addProduct(Product product)
//        – void changeProductPriceById(int productId, double newPrice)
//        – void changeProductQuantityById(int productId, int newQuantity)
//        –void changeProductNameById(int productId, String newName)
//        – void deleteProductById(int productId)
//        –void sortEmployeesByName()
//        –void sortEmployeesBySalary()

public class Store {
    private List<Product> products;
    private List<Product> shoppingCart;
    private List<Employee> employees;

}
