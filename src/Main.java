import domain.Product;
import service.ProductService;

void main(){
    ProductService productService = new ProductService();

    productService.create(new Product(productService.generateUUID(),
            "SKU",
            "asas",
            new BigDecimal(2)));

    productService.listAll();

}

